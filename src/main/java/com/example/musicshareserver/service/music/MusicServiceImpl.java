package com.example.musicshareserver.service.music;

import com.example.musicshareserver.dto.request.music.SearchMusicDto;
import com.example.musicshareserver.dto.request.music.ShareMusicDto;
import com.example.musicshareserver.dto.response.PageResponse;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.exception.BadRequestException;
import com.example.musicshareserver.exception.NotFoundException;
import com.example.musicshareserver.repository.MusicRepository;
import com.example.musicshareserver.repository.MusicStatRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final MusicStatRepository musicStatRepository;

    @Value("${app.url.fe:http://localhost:3000}")
    private String frontendUrl;

    @Override
    public PageResponse<Music> searchMusic(SearchMusicDto dto) {
        int page = dto.getPage() > 0 ? dto.getPage() : 1;
        int limit = dto.getLimit() > 0 ? dto.getLimit() : 10;
        
        Specification<Music> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.isNull(root.get("deletedAt")));

            if (dto.getGenre() != null && !dto.getGenre().isEmpty()) {
                predicates.add(cb.equal(root.get("genre"), dto.getGenre()));
            }

            if (StringUtils.hasText(dto.getQuery())) {
                String search = "%" + dto.getQuery().toLowerCase() + "%";
                Predicate searchPredicate = null;

                if ("TRACK".equalsIgnoreCase(dto.getType())) {
                    searchPredicate = cb.like(cb.lower(root.get("title")), search);
                } else if ("ARTIST".equalsIgnoreCase(dto.getType())) {
                    Join<Music, ArtistProfile> artist = root.join("artistProfile", JoinType.LEFT);
                    Join<ArtistProfile, User> user = artist.join("user", JoinType.LEFT);
                    searchPredicate = cb.or(
                            cb.like(cb.lower(artist.get("stageName")), search),
                            cb.like(cb.lower(user.get("name")), search)
                    );
                } else if ("ALBUM".equalsIgnoreCase(dto.getType())) {
                    Join<Music, AlbumTrack> albumTracks = root.join("albumTracks", JoinType.LEFT);
                    Join<AlbumTrack, Album> albums = albumTracks.join("album", JoinType.LEFT);
                    searchPredicate = cb.like(cb.lower(albums.get("title")), search);
                } else {
                    // OR condition for all
                    Predicate titleMatch = cb.like(cb.lower(root.get("title")), search);
                    Predicate genreMatch = cb.like(cb.lower(root.get("genre")), search);

                    Join<Music, ArtistProfile> artist = root.join("artistProfile", JoinType.LEFT);
                    Join<ArtistProfile, User> user = artist.join("user", JoinType.LEFT);
                    Predicate artistMatch = cb.or(
                            cb.like(cb.lower(artist.get("stageName")), search),
                            cb.like(cb.lower(user.get("name")), search)
                    );
                    
                    Join<Music, AlbumTrack> albumTracks = root.join("albumTracks", JoinType.LEFT);
                    Join<AlbumTrack, Album> albums = albumTracks.join("album", JoinType.LEFT);
                    Predicate albumMatch = cb.like(cb.lower(albums.get("title")), search);

                    searchPredicate = cb.or(titleMatch, genreMatch, artistMatch, albumMatch);
                }

                if (searchPredicate != null) {
                    predicates.add(searchPredicate);
                }
            }
            
            query.distinct(true);
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "voteCount"));
        Page<Music> result = musicRepository.findAll(spec, pageable);

        return PageResponse.<Music>builder()
                .data(result.getContent())
                .meta(new PageResponse.Meta(page, limit, result.getTotalElements(), result.getTotalPages()))
                .build();
    }

    @Override
    public Music getMusicDetail(Long trackId) {
        return musicRepository.findByIdAndArtistProfileId(trackId, null) 
                .filter(m -> m.getDeletedAt() == null)
                .orElseThrow(() -> new NotFoundException("Bài hát không tồn tại"));
    }
    
    private Music findMusicById(Long id) {
        return musicRepository.findById(id)
                .filter(m -> m.getDeletedAt() == null)
                .orElseThrow(() -> new NotFoundException("Bài hát không tồn tại"));
    }

    @Override
    @Transactional
    public Map<String, String> streamMusic(Long trackId) {
        Music music = findMusicById(trackId);
        musicStatRepository.incrementListens(trackId);
        return Map.of("url", music.getFileUrl());
    }

    @Override
    @Transactional
    public Map<String, Object> shareMusic(Long trackId, ShareMusicDto dto) {
        Music music = findMusicById(trackId);
        musicStatRepository.incrementShares(trackId);

        String shareUrl = frontendUrl + "/music/" + trackId;
        String platformUrl = shareUrl;
        
        if (dto.getPlatform() != null) {
            String encodedUrl = URLEncoder.encode(shareUrl, StandardCharsets.UTF_8);
            String encodedTitle = URLEncoder.encode(music.getTitle(), StandardCharsets.UTF_8);
            
            switch (dto.getPlatform().toUpperCase()) {
                case "FACEBOOK":
                    platformUrl = "https://www.facebook.com/sharer/sharer.php?u=" + encodedUrl;
                    break;
                case "TWITTER":
                    platformUrl = "https://twitter.com/intent/tweet?url=" + encodedUrl + "&text=" + encodedTitle;
                    break;
                case "WHATSAPP":
                    platformUrl = "https://wa.me/?text=" + encodedTitle + " - " + encodedUrl; 
                    platformUrl = "https://wa.me/?text=" + URLEncoder.encode(music.getTitle() + " - " + shareUrl, StandardCharsets.UTF_8);
                    break;
                case "COPY":
                default:
                    break;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Chia sẻ thành công");
        response.put("shareUrl", shareUrl);
        if (!"COPY".equalsIgnoreCase(dto.getPlatform())) {
            response.put("platformUrl", platformUrl);
        }
        
        Map<String, Object> trackInfo = new HashMap<>();
        trackInfo.put("id", music.getId());
        trackInfo.put("title", music.getTitle());
        trackInfo.put("artist", music.getArtistProfile() != null ? music.getArtistProfile().getStageName() : null);
        
        response.put("track", trackInfo);
        
        return response;
    }

    @Override
    public PageResponse<Music> getMusicByGenre(String genre, int page, int limit) {
        if (page < 1) page = 1;
        if (limit < 1) limit = 10;
        
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        
        Specification<Music> spec = (root, query, cb) -> {
            return cb.and(
                    cb.equal(root.get("genre"), genre),
                    cb.isNull(root.get("deletedAt"))
            );
        };
        
        Page<Music> result = musicRepository.findAll(spec, pageable);
        
        return PageResponse.<Music>builder()
                .data(result.getContent())
                .meta(new PageResponse.Meta(page, limit, result.getTotalElements(), result.getTotalPages()))
                .build();
    }

    @Override
    public List<Music> getAllMusic() {
        Specification<Music> spec = (root, query, cb) -> cb.isNull(root.get("deletedAt"));
        return musicRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "voteCount"));
    }
}
