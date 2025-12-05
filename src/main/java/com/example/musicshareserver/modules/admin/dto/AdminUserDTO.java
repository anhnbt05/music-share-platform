// File: com/example/musicshareserver/modules/admin/dto/AdminUserDTO.java
package com.example.musicshareserver.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class AdminUserDTO {

    @Schema(description = "Yêu cầu cập nhật vai trò người dùng")
    public static class UpdateRoleRequest {
        @Schema(description = "Vai trò mới", example = "ARTIST", required = true)
        @NotBlank(message = "Vai trò là bắt buộc")
        private String role;

        public UpdateRoleRequest() {}
        public UpdateRoleRequest(String role) { this.role = role; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    @Schema(description = "Yêu cầu tìm kiếm tài khoản")
    public static class SearchAccountRequest {
        @Schema(description = "Từ khóa tìm kiếm (tên hoặc email)", example = "john")
        private String query;

        @Schema(description = "Trạng thái tài khoản", example = "ACTIVE")
        private String status;

        @Schema(description = "Vai trò", example = "USER")
        private String role;

        public SearchAccountRequest() {}

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    @Schema(description = "Phản hồi chi tiết tài khoản cho admin")
    public static class UserDetailResponse {
        @Schema(description = "ID tài khoản")
        private Long id;

        @Schema(description = "Email")
        private String email;

        @Schema(description = "Họ tên")
        private String name;

        @Schema(description = "Vai trò")
        private String role;

        @Schema(description = "Ngày tạo")
        private LocalDateTime createdAt;

        @Schema(description = "Đã bị xóa")
        private Boolean isDeleted;

        @Schema(description = "Thông tin hồ sơ nghệ sĩ (nếu có)")
        private ArtistProfileResponse artistProfile;

        @Schema(description = "Tổng số bài hát đã upload")
        private Integer totalUploads;

        @Schema(description = "Tổng số lượt like đã thực hiện")
        private Integer totalVotes;

        @Schema(description = "Ngày đăng nhập gần nhất")
        private LocalDateTime lastLogin;

        public UserDetailResponse() {}

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public Boolean getIsDeleted() { return isDeleted; }
        public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
        public ArtistProfileResponse getArtistProfile() { return artistProfile; }
        public void setArtistProfile(ArtistProfileResponse artistProfile) { this.artistProfile = artistProfile; }
        public Integer getTotalUploads() { return totalUploads; }
        public void setTotalUploads(Integer totalUploads) { this.totalUploads = totalUploads; }
        public Integer getTotalVotes() { return totalVotes; }
        public void setTotalVotes(Integer totalVotes) { this.totalVotes = totalVotes; }
        public LocalDateTime getLastLogin() { return lastLogin; }
        public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    }

    @Schema(description = "Thông tin hồ sơ nghệ sĩ")
    public static class ArtistProfileResponse {
        @Schema(description = "ID hồ sơ nghệ sĩ")
        private Long id;

        @Schema(description = "Nghệ danh")
        private String stageName;

        @Schema(description = "Tiểu sử")
        private String bio;

        @Schema(description = "URL ảnh đại diện")
        private String photoUrl;

        @Schema(description = "Liên kết mạng xã hội")
        private String socialLinks;

        @Schema(description = "Trạng thái")
        private String status;

        @Schema(description = "Ngày cập nhật")
        private LocalDateTime updatedAt;

        public ArtistProfileResponse() {}

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        public String getSocialLinks() { return socialLinks; }
        public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    }
}