package com.example.musicshareserver.service.user;

import com.example.musicshareserver.dto.request.user.*;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.exception.ApiException;
import com.example.musicshareserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistTrackRepository playlistTrackRepository;
    private final MusicRepository musicRepository;
    private final VoteRepository voteRepository;
    private final FollowRepository followRepository;
    private final ArtistProfileRepository artistProfileRepository;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ArtistApplicationRepository artistApplicationRepository;

    @Override
    @Transactional
    public Map<String, Object> createPlaylist(Long userId, CreatePlaylistRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        Playlist playlist = new Playlist();
        playlist.setName(dto.getName());
        playlist.setUser(user);
        playlist.setCreationDate(LocalDateTime.now());

        playlist = playlistRepository.save(playlist);

        return Map.of(
                "message", "Tạo playlist thành công",
                "playlist", playlist
        );
    }

    @Override
    public List<Playlist> getUserPlaylists(Long userId) {
        // Note: The NestJS implementation includes nested relations (tracks, music, artists)
        // JPA will lazy load these, but for performance in a real app created properly, avoid N+1.
        // For direct migration of logic:
        return playlistRepository.findByUserIdOrderByCreationDateDesc(userId);
    }

    @Override
    public Playlist getPlaylistDetail(Long userId, Long playlistId) {
        return playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new ApiException("Playlist không tồn tại"));
    }

    @Override
    @Transactional
    public Map<String, Object> updatePlaylist(Long userId, Long playlistId, UpdatePlaylistRequest dto) {
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new ApiException("Playlist không tồn tại"));

        playlist.setName(dto.getName());
        Playlist updatedPlaylist = playlistRepository.save(playlist);

        return Map.of(
                "message", "Cập nhật playlist thành công",
                "playlist", updatedPlaylist
        );
    }

    @Override
    @Transactional
    public Map<String, String> deletePlaylist(Long userId, Long playlistId, DeletePlaylistRequest dto) {
        if (!dto.isConfirm()) {
            throw new ApiException("Vui lòng xác nhận xóa playlist");
        }

        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new ApiException("Playlist không tồn tại"));

        playlistTrackRepository.deleteByPlaylistId(playlistId);
        playlistRepository.delete(playlist);

        return Map.of("message", "Xóa playlist thành công");
    }

    @Override
    @Transactional
    public Map<String, Object> addTrackToPlaylist(Long userId, Long playlistId, AddTrackToPlaylistRequest dto) {
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new ApiException("Playlist không tồn tại"));

        Music track = musicRepository.findById(dto.getTrackId())
                .orElseThrow(() -> new ApiException("Bài hát không tồn tại"));

        if (playlistTrackRepository.findByPlaylistIdAndMusicId(playlistId, dto.getTrackId()).isPresent()) {
            throw new ApiException("Bài hát đã có trong playlist");
        }

        long trackCount = playlistTrackRepository.countByPlaylistId(playlistId);

        PlaylistTrack playlistTrack = new PlaylistTrack();
        playlistTrack.setPlaylist(playlist);
        playlistTrack.setMusic(track);
        playlistTrack.setAddedAt(LocalDateTime.now());
        playlistTrack.setTrackOrder((int) trackCount + 1);

        playlistTrack = playlistTrackRepository.save(playlistTrack);

        return Map.of(
                "message", "Thêm bài hát vào playlist thành công",
                "playlistTrack", playlistTrack
        );
    }

    @Override
    @Transactional
    public Map<String, String> removeTrackFromPlaylist(Long userId, Long playlistId, Long trackId, RemoveTrackFromPlaylistRequest dto) {
        if (!dto.isConfirm()) {
            throw new ApiException("Vui lòng xác nhận xóa bài hát khỏi playlist");
        }

        playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new ApiException("Playlist không tồn tại"));

        playlistTrackRepository.deleteByPlaylistIdAndMusicId(playlistId, trackId);

        List<PlaylistTrack> remainingTracks = playlistTrackRepository.findByPlaylistIdOrderByTrackOrderAsc(playlistId);
        for (int i = 0; i < remainingTracks.size(); i++) {
            PlaylistTrack pt = remainingTracks.get(i);
            pt.setTrackOrder(i + 1);
            playlistTrackRepository.save(pt);
        }

        return Map.of("message", "Xóa bài hát khỏi playlist thành công");
    }

    @Override
    @Transactional
    public Map<String, Object> voteTrack(Long userId, Long trackId, VoteRequest dto) {
        Music track = musicRepository.findById(trackId)
                .orElseThrow(() -> new ApiException("Bài hát không tồn tại"));

        if (voteRepository.findByUserIdAndMusicId(userId, trackId).isPresent()) {
            throw new ApiException("Bạn đã vote bài hát này");
        }

        User user = userRepository.findById(userId).orElseThrow();

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setMusic(track);
        vote.setVoteType(dto.getVoteType());
        vote.setCreatedAt(LocalDateTime.now());

        vote = voteRepository.save(vote);

        track.setVoteCount(track.getVoteCount() + 1);
        musicRepository.save(track);

        return Map.of(
                "message", "Vote thành công",
                "vote", vote
        );
    }

    @Override
    @Transactional
    public Map<String, String> unvoteTrack(Long userId, Long trackId) {
        Vote vote = voteRepository.findByUserIdAndMusicId(userId, trackId)
                .orElseThrow(() -> new ApiException("Bạn chưa vote bài hát này"));

        voteRepository.delete(vote);

        Music track = musicRepository.findById(trackId).orElseThrow();
        track.setVoteCount(track.getVoteCount() - 1);
        musicRepository.save(track);

        return Map.of("message", "Hủy vote thành công");
    }

    @Override
    @Transactional
    public Map<String, Object> followArtist(Long userId, Long artistId) {
        ArtistProfile artistProfile = artistProfileRepository.findById(artistId)
                .orElseThrow(() -> new ApiException("Artist không tồn tại"));

        if (followRepository.findByFollowerIdAndArtistProfileId(userId, artistId).isPresent()) {
            throw new ApiException("Bạn đã theo dõi artist này");
        }

        User user = userRepository.findById(userId).orElseThrow();

        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setArtistProfile(artistProfile);
        follow.setCreatedAt(LocalDateTime.now());

        follow = followRepository.save(follow);

        return Map.of(
                "message", "Theo dõi artist thành công",
                "follow", follow
        );
    }

    @Override
    @Transactional
    public Map<String, String> unfollowArtist(Long userId, Long artistId) {
        Follow follow = followRepository.findByFollowerIdAndArtistProfileId(userId, artistId)
                .orElseThrow(() -> new ApiException("Bạn chưa theo dõi artist này"));

        followRepository.delete(follow);

        return Map.of("message", "Bỏ theo dõi artist thành công");
    }

    @Override
    public List<ArtistProfile> getFollowingArtists(Long userId) {
        List<Follow> follows = followRepository.findByFollowerIdOrderByCreatedAtDesc(userId);
        return follows.stream()
                .map(Follow::getArtistProfile)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Map<String, Object> createReport(Long userId, CreateReportRequest dto) {
        boolean targetExists = false;
        switch (dto.getTargetType()) {
            case "MUSIC":
                targetExists = musicRepository.existsById(dto.getTargetId());
                break;
            case "USER":
                targetExists = userRepository.findByIdAndIsDeletedFalse(dto.getTargetId()).isPresent();
                break;
            case "COMMENT":
                targetExists = false; // Implement comment check if needed
                break;
            case "PLAYLIST":
                targetExists = playlistRepository.existsById(dto.getTargetId());
                break;
        }

        if (!targetExists) {
            throw new ApiException("Đối tượng báo cáo không tồn tại");
        }

        User reporter = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        Report report = new Report();
        report.setReporterUser(reporter);
        report.setReportType(dto.getTargetType());
        report.setReason(dto.getReason());
        report.setReportDate(LocalDateTime.now());
        report.setStatus("PENDING");

        if ("MUSIC".equals(dto.getTargetType())) {
            Music music = musicRepository.findById(dto.getTargetId()).orElse(null);
            report.setReportedMusic(music);
        }
        if ("USER".equals(dto.getTargetType())) {
            User targetUser = userRepository.findById(dto.getTargetId()).orElse(null);
            report.setReportedUser(targetUser);
        }
        if ("PLAYLIST".equals(dto.getTargetType())) {
            Playlist playlist = playlistRepository.findById(dto.getTargetId()).orElse(null);
            report.setReportedPlaylist(playlist);
        }

        report = reportRepository.save(report);

        return Map.of(
                "message", "Báo cáo đã được gửi thành công",
                "report", report
        );
    }

    @Override
    @Transactional
    public Map<String, Object> applyArtist(Long userId, ApplyArtistRequest dto) {
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        if (user.getArtistProfile() != null) {
            throw new ApiException("Bạn đã là artist");
        }

        if (artistApplicationRepository.findByUserIdAndStatusIn(userId, Arrays.asList("PENDING", "APPROVED")).isPresent()) {
            throw new ApiException("Bạn đã gửi đơn ứng tuyển trước đó, vui lòng chờ xét duyệt");
        }

        ArtistApplication application = new ArtistApplication();
        application.setUser(user);
        application.setStageName(dto.getStageName());
        application.setBio(dto.getBio());
        application.setPhotoUrl(dto.getPhotoUrl());
        application.setSocialLinks(dto.getSocialLinks());
        application.setStatus("PENDING");
        application.setCreatedAt(LocalDateTime.now());

        application = artistApplicationRepository.save(application);

        return Map.of(
                "message", "Nộp đơn ứng tuyển làm artist thành công",
                "application", application
        );
    }
}
