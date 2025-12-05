// File: com/example/musicshareserver/modules/admin/dto/ArtistApplicationDTO.java
package com.example.musicshareserver.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class ArtistApplicationDTO {

    @Schema(description = "Yêu cầu duyệt đơn đăng ký nghệ sĩ")
    public static class ApproveRequest {
        @Schema(description = "Ghi chú (tùy chọn)")
        private String notes;

        public ApproveRequest() {}

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }

    @Schema(description = "Yêu cầu từ chối đơn đăng ký nghệ sĩ")
    public static class RejectRequest {
        @Schema(description = "Lý do từ chối", required = true)
        @NotBlank(message = "Lý do từ chối là bắt buộc")
        private String reason;

        public RejectRequest() {}
        public RejectRequest(String reason) { this.reason = reason; }

        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    @Schema(description = "Phản hồi đơn đăng ký nghệ sĩ")
    public static class ApplicationResponse {
        @Schema(description = "ID đơn đăng ký")
        private Long id;

        @Schema(description = "Thông tin người dùng")
        private UserInfo user;

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

        @Schema(description = "Ngày tạo đơn")
        private LocalDateTime createdAt;

        @Schema(description = "Lý do từ chối (nếu có)")
        private String rejectionReason;

        @Schema(description = "Tài liệu đính kèm")
        private List<String> attachments;

        public ApplicationResponse() {}

        @Schema(description = "Thông tin người dùng đăng ký")
        public static class UserInfo {
            private Long id;
            private String email;
            private String name;
            private LocalDateTime joinedDate;

            public UserInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public LocalDateTime getJoinedDate() { return joinedDate; }
            public void setJoinedDate(LocalDateTime joinedDate) { this.joinedDate = joinedDate; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public UserInfo getUser() { return user; }
        public void setUser(UserInfo user) { this.user = user; }
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
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public String getRejectionReason() { return rejectionReason; }
        public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
        public List<String> getAttachments() { return attachments; }
        public void setAttachments(List<String> attachments) { this.attachments = attachments; }
    }

    @Schema(description = "Yêu cầu tìm kiếm đơn đăng ký")
    public static class SearchApplicationsRequest {
        @Schema(description = "Trạng thái", example = "PENDING")
        private String status;

        @Schema(description = "Từ khóa tìm kiếm (email hoặc tên người dùng)")
        private String query;

        @Schema(description = "Ngày bắt đầu")
        private LocalDateTime startDate;

        @Schema(description = "Ngày kết thúc")
        private LocalDateTime endDate;

        public SearchApplicationsRequest() {}

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public LocalDateTime getStartDate() { return startDate; }
        public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
        public LocalDateTime getEndDate() { return endDate; }
        public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    }
}