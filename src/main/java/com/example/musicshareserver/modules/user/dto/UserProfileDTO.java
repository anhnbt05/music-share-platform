// File: com/example/musicshareserver/modules/user/dto/UserProfileDTO.java
package com.example.musicshareserver.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UserProfileDTO {

    @Schema(description = "Yêu cầu cập nhật thông tin người dùng")
    public static class UpdateProfileRequest {
        @Schema(description = "Họ tên", example = "Nguyễn Văn B")
        private String name;

        @Schema(description = "Email mới", example = "newemail@example.com")
        @Email(message = "Email phải hợp lệ")
        private String email;

        public UpdateProfileRequest() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    @Schema(description = "Yêu cầu đổi mật khẩu")
    public static class ChangePasswordRequest {
        @Schema(description = "Mật khẩu hiện tại", required = true)
        @NotBlank(message = "Mật khẩu hiện tại là bắt buộc")
        private String currentPassword;

        @Schema(description = "Mật khẩu mới", required = true)
        @NotBlank(message = "Mật khẩu mới là bắt buộc")
        private String newPassword;

        @Schema(description = "Xác nhận mật khẩu mới", required = true)
        @NotBlank(message = "Xác nhận mật khẩu là bắt buộc")
        private String confirmPassword;

        public ChangePasswordRequest() {}

        public String getCurrentPassword() { return currentPassword; }
        public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
        public String getConfirmPassword() { return confirmPassword; }
        public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    }

    @Schema(description = "Phản hồi thông tin người dùng chi tiết")
    public static class UserProfileResponse {
        @Schema(description = "ID người dùng")
        private Long id;

        @Schema(description = "Email")
        private String email;

        @Schema(description = "Họ tên")
        private String name;

        @Schema(description = "Vai trò")
        private String role;

        @Schema(description = "Ngày tạo tài khoản")
        private LocalDateTime createdAt;

        @Schema(description = "Thông tin nghệ sĩ (nếu có)")
        private ArtistProfileInfo artistProfile;

        @Schema(description = "Số playlist đã tạo")
        private Integer playlistCount;

        @Schema(description = "Số nghệ sĩ đang theo dõi")
        private Integer followingCount;

        @Schema(description = "Số lượt like đã thực hiện")
        private Integer totalLikes;

        public UserProfileResponse() {}

        @Schema(description = "Thông tin hồ sơ nghệ sĩ")
        public static class ArtistProfileInfo {
            private Long id;
            private String stageName;
            private String bio;
            private String photoUrl;
            private String status;

            public ArtistProfileInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getStageName() { return stageName; }
            public void setStageName(String stageName) { this.stageName = stageName; }
            public String getBio() { return bio; }
            public void setBio(String bio) { this.bio = bio; }
            public String getPhotoUrl() { return photoUrl; }
            public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
            public String getStatus() { return status; }
            public void setStatus(String status) { this.status = status; }
        }

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
        public ArtistProfileInfo getArtistProfile() { return artistProfile; }
        public void setArtistProfile(ArtistProfileInfo artistProfile) { this.artistProfile = artistProfile; }
        public Integer getPlaylistCount() { return playlistCount; }
        public void setPlaylistCount(Integer playlistCount) { this.playlistCount = playlistCount; }
        public Integer getFollowingCount() { return followingCount; }
        public void setFollowingCount(Integer followingCount) { this.followingCount = followingCount; }
        public Integer getTotalLikes() { return totalLikes; }
        public void setTotalLikes(Integer totalLikes) { this.totalLikes = totalLikes; }
    }
}