package com.example.musicshareserver.service.user;

import com.example.musicshareserver.dto.request.user.*;
import com.example.musicshareserver.entity.ArtistApplication;
import com.example.musicshareserver.entity.ArtistProfile;
import com.example.musicshareserver.entity.Playlist;
import com.example.musicshareserver.entity.PlaylistTrack;
import com.example.musicshareserver.entity.Report;
import com.example.musicshareserver.entity.Vote;
import com.example.musicshareserver.entity.Follow;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> createPlaylist(Long userId, CreatePlaylistRequest dto);

    List<Playlist> getUserPlaylists(Long userId);

    Playlist getPlaylistDetail(Long userId, Long playlistId);

    Map<String, Object> updatePlaylist(Long userId, Long playlistId, UpdatePlaylistRequest dto);

    Map<String, String> deletePlaylist(Long userId, Long playlistId, DeletePlaylistRequest dto);

    Map<String, Object> addTrackToPlaylist(Long userId, Long playlistId, AddTrackToPlaylistRequest dto);

    Map<String, String> removeTrackFromPlaylist(Long userId, Long playlistId, Long trackId, RemoveTrackFromPlaylistRequest dto);

    Map<String, Object> voteTrack(Long userId, Long trackId, VoteRequest dto);

    Map<String, String> unvoteTrack(Long userId, Long trackId);

    Map<String, Object> followArtist(Long userId, Long artistId);

    Map<String, String> unfollowArtist(Long userId, Long artistId);

    List<ArtistProfile> getFollowingArtists(Long userId);

    Map<String, Object> createReport(Long userId, CreateReportRequest dto);

    Map<String, Object> applyArtist(Long userId, ApplyArtistRequest dto);
}
