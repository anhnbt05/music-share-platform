package com.example.musicshareserver.service.artist;

import com.example.musicshareserver.common.dto.UploadOptions;
import com.example.musicshareserver.common.dto.UploadResult;
import com.example.musicshareserver.dto.request.artist.*;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.exception.ApiException;
import com.example.musicshareserver.repository.*;
import com.example.musicshareserver.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistProfileRepository artistProfileRepository;
    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;
    private final AlbumTrackRepository albumTrackRepository;
    private final MusicStatRepository musicStatRepository;
    private final StorageService storageService;

    private ArtistProfile getArtistProfileByUserId(Long userId) {
        return artistProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException("Bạn không phải là artist"));
    }

    @Override
    @Transactional
    public Map<String, Object> uploadMusic(Long userId, UploadMusicRequest dto, MultipartFile file) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        if (file == null || file.isEmpty()) {
            throw new ApiException("Vui lòng chọn file âm nhạc");
        }

        List<String> allowedMimeTypes = Arrays.asList("audio/mpeg", "audio/wav", "audio/mp4", "audio/ogg");
        if (!allowedMimeTypes.contains(file.getContentType())) {
            throw new ApiException("Định dạng file không được hỗ trợ. Chỉ chấp nhận MP3, WAV, M4A, OGG");
        }

        if (file.getSize() > 50 * 1024 * 1024) {
            throw new ApiException("File quá lớn. Kích thước tối đa 50MB");
        }

        UploadOptions options = new UploadOptions();
        options.setBucket("music");
        options.setFolder("tracks");
        options.setAllowedMimeTypes(allowedMimeTypes);
        options.setMaxFileSize(50L * 1024 * 1024);

        UploadResult uploadResult = storageService.uploadFile(file, options);

        Music music = new Music();
        music.setArtistProfile(artistProfile);
        music.setTitle(dto.getTitle());
        music.setGenre(dto.getGenre());
        music.setDescription(dto.getDescription());
        music.setFileUrl(uploadResult.getUrl());
        music.setVoteCount(0);
        music.setCreatedAt(LocalDateTime.now());
        
        music = musicRepository.save(music);

        MusicStat musicStat = new MusicStat();
        musicStat.setMusic(music);
        musicStat.setListens(0);
        musicStat.setShares(0);
        musicStat.setUpdatedAt(LocalDateTime.now());
        musicStatRepository.save(musicStat);

        return Map.of(
                "message", "Upload bài hát thành công",
                "music", music
        );
    }

    @Override
    public List<Music> getArtistMusic(Long userId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);
        return musicRepository.findByArtistProfileIdAndDeletedAtIsNull(artistProfile.getId());
    }

    @Override
    @Transactional
    public Map<String, Object> updateMusic(Long userId, Long trackId, UpdateMusicRequest dto) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Music track = musicRepository.findByIdAndArtistProfileId(trackId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Bài hát không tồn tại hoặc không thuộc về bạn"));

        if (dto.getTitle() != null) track.setTitle(dto.getTitle());
        if (dto.getGenre() != null) track.setGenre(dto.getGenre());
        if (dto.getDescription() != null) track.setDescription(dto.getDescription());

        track = musicRepository.save(track);

        return Map.of(
                "message", "Cập nhật bài hát thành công",
                "track", track
        );
    }

    @Override
    @Transactional
    public Map<String, String> deleteMusic(Long userId, Long trackId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Music track = musicRepository.findByIdAndArtistProfileId(trackId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Bài hát không tồn tại hoặc không thuộc về bạn"));

        storageService.deleteFile("music", track.getFileUrl());

        track.setDeletedAt(LocalDateTime.now());
        musicRepository.save(track);

        return Map.of("message", "Xóa bài hát thành công");
    }

    @Override
    @Transactional
    public Map<String, Object> createAlbum(Long userId, CreateAlbumRequest dto) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Album album = new Album();
        album.setArtistProfile(artistProfile);
        album.setTitle(dto.getTitle());
        album.setDescription(dto.getDescription());
        album.setCreatedAt(LocalDateTime.now());

        album = albumRepository.save(album);

        return Map.of(
                "message", "Tạo album thành công",
                "album", album
        );
    }

    @Override
    public List<Album> getArtistAlbums(Long userId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);
        return albumRepository.findByArtistProfileIdOrderByCreatedAtDesc(artistProfile.getId());
    }

    @Override
    public Album getAlbumDetail(Long userId, Long albumId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        return albumRepository.findByIdAndArtistProfileId(albumId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Album không tồn tại"));
    }

    @Override
    @Transactional
    public Map<String, Object> updateAlbum(Long userId, Long albumId, UpdateAlbumRequest dto) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Album album = albumRepository.findByIdAndArtistProfileId(albumId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Album không tồn tại"));

        if (dto.getTitle() != null) album.setTitle(dto.getTitle());
        if (dto.getDescription() != null) album.setDescription(dto.getDescription());
        if (dto.getCoverUrl() != null) album.setCoverUrl(dto.getCoverUrl());

        album = albumRepository.save(album);

        return Map.of(
                "message", "Cập nhật album thành công",
                "album", album
        );
    }

    @Override
    @Transactional
    public Map<String, String> deleteAlbum(Long userId, Long albumId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Album album = albumRepository.findByIdAndArtistProfileId(albumId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Album không tồn tại"));

        albumTrackRepository.deleteByAlbumId(albumId);
        albumRepository.delete(album);

        return Map.of("message", "Xóa album thành công");
    }

    @Override
    @Transactional
    public Map<String, Object> addTracksToAlbum(Long userId, Long albumId, AddTracksToAlbumRequest dto) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        Album album = albumRepository.findByIdAndArtistProfileId(albumId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Album không tồn tại"));

        List<Music> tracks = musicRepository.findAllById(dto.getTrackIds());

        long invalidTracks = tracks.stream()
                .filter(track -> !track.getArtistProfile().getId().equals(artistProfile.getId()))
                .count();

        if (invalidTracks > 0 || tracks.size() != dto.getTrackIds().size()) {
            throw new ApiException("Một số bài hát không thuộc về bạn");
        }

        Long existingCount = albumTrackRepository.countByAlbumId(albumId);
        List<AlbumTrack> albumTracks = new ArrayList<>();

        for (int i = 0; i < dto.getTrackIds().size(); i++) {
            Long trackId = dto.getTrackIds().get(i);
            Integer trackOrder = (dto.getTrackOrder() != null && i < dto.getTrackOrder().size())
                    ? dto.getTrackOrder().get(i)
                    : existingCount.intValue() + i + 1;

            Optional<AlbumTrack> exists = albumTrackRepository.findByAlbumIdAndMusicId(albumId, trackId);
            if (exists.isPresent()) {
                throw new ApiException("Bài hát ID " + trackId + " đã có trong album");
            }

            Music music = tracks.stream()
                    .filter(t -> t.getId().equals(trackId))
                    .findFirst()
                    .orElseThrow();

            AlbumTrack albumTrack = new AlbumTrack();
            albumTrack.setAlbum(album);
            albumTrack.setMusic(music);
            albumTrack.setTrackOrder(trackOrder);

            albumTrack = albumTrackRepository.save(albumTrack);
            albumTracks.add(albumTrack);
        }

        return Map.of(
                "message", "Thêm bài hát vào album thành công",
                "albumTracks", albumTracks
        );
    }

    @Override
    @Transactional
    public Map<String, String> removeTrackFromAlbum(Long userId, Long albumId, Long trackId) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        albumRepository.findByIdAndArtistProfileId(albumId, artistProfile.getId())
                .orElseThrow(() -> new ApiException("Album không tồn tại"));

        albumTrackRepository.deleteByAlbumIdAndMusicId(albumId, trackId);

        List<AlbumTrack> remainingTracks = albumTrackRepository.findByAlbumIdOrderByTrackOrderAsc(albumId);

        for (int i = 0; i < remainingTracks.size(); i++) {
            AlbumTrack track = remainingTracks.get(i);
            track.setTrackOrder(i + 1);
            albumTrackRepository.save(track);
        }

        return Map.of("message", "Xóa bài hát khỏi album thành công");
    }

    @Override
    public Map<String, Object> getAnalytics(Long userId, AnalyticsFilterRequest filter) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        List<MusicStat> stats = musicStatRepository.findByMusicArtistProfileId(artistProfile.getId());

        if (filter.getTrackId() != null) {
            stats = stats.stream()
                    .filter(stat -> stat.getMusic().getId().equals(filter.getTrackId()))
                    .toList();
        }

        // Note: Date filtering would need additional query support
        // For now, returning all stats

        int totalListens = stats.stream().mapToInt(MusicStat::getListens).sum();
        int totalShares = stats.stream().mapToInt(MusicStat::getShares).sum();

        List<MusicStat> topTracks = stats.stream()
                .sorted(Comparator.comparingInt(MusicStat::getListens).reversed())
                .limit(5)
                .toList();

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalTracks", stats.size());
        summary.put("totalListens", totalListens);
        summary.put("totalShares", totalShares);
        summary.put("averageListensPerTrack", stats.isEmpty() ? 0.0 : (double) totalListens / stats.size());

        return Map.of(
                "summary", summary,
                "topTracks", topTracks,
                "detailedStats", stats
        );
    }

    @Override
    @Transactional
    public Map<String, Object> updateProfile(Long userId, UpdateProfileRequest dto) {
        ArtistProfile artistProfile = getArtistProfileByUserId(userId);

        if (dto.getBio() != null) artistProfile.setBio(dto.getBio());
        if (dto.getPhotoUrl() != null) artistProfile.setPhotoUrl(dto.getPhotoUrl());
        if (dto.getSocialLinks() != null) artistProfile.setSocialLinks(dto.getSocialLinks());
        if (dto.getStageName() != null) artistProfile.setStageName(dto.getStageName());
        artistProfile.setUpdatedAt(LocalDateTime.now());

        artistProfile = artistProfileRepository.save(artistProfile);

        return Map.of(
                "message", "Cập nhật profile thành công",
                "profile", artistProfile
        );
    }

    @Override
    public ArtistProfile getArtistProfile(Long userId) {
        return getArtistProfileByUserId(userId);
    }
}
