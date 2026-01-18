package com.example.musicshareserver.dto.request.music;

import lombok.Data;

@Data
public class SearchMusicDto {
    private String query;
    private String type; // TRACK, ARTIST, ALBUM
    private String genre;
    private int page = 1;
    private int limit = 10;
}
