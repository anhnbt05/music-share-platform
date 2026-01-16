package com.example.musicshareserver.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request tìm kiếm tài khoản người dùng")
public class SearchAccountRequest {

    @Schema(
            description = "Từ khóa tìm kiếm theo email hoặc tên",
            example = "user@gmail.com"
    )
    private String query;

    @Schema(
            description = "Trang hiện tại",
            example = "1",
            defaultValue = "1"
    )
    private Integer page = 1;

    @Schema(
            description = "Số lượng bản ghi mỗi trang",
            example = "10",
            defaultValue = "10"
    )
    private Integer limit = 10;
}
