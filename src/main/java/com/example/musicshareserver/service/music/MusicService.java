package com.example.musicshareserver.service.music;

import com.example.musicshareserver.dto.request.music.SearchMusicDto;
import com.example.musicshareserver.dto.request.music.ShareMusicDto;
import com.example.musicshareserver.dto.response.PageResponse;
import com.example.musicshareserver.entity.Music;

import java.util.List;
import java.util.Map;

public interface MusicService {
    PageResponse<Music> searchMusic(SearchMusicDto dto);

    Music getMusicDetail(Long trackId);

    Map<String, String> streamMusic(Long trackId);

    Map<String, Object> shareMusic(Long trackId, ShareMusicDto dto);

    PageResponse<Music> getMusicByGenre(String genre, int page, int limit);

    List<Music> getAllMusic();
}
