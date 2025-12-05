// File: com/example/musicshareserver/modules/user/dto/ArtistApplicationDTO.java
package com.example.musicshareserver.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ArtistApplicationDTO {

    @Schema(description = "Yêu cầu đăng ký làm nghệ sĩ")
    public static class ApplyArtistRequest {
        @Schema(description = "Nghệ danh", required = true)
        @NotBlank(message = "Nghệ danh là bắt buộc")
        private String stageName;

        @Schema(description = "Tiểu sử")
        private String bio;

        @Schema(description = "URL ảnh đại diện")
        private String photoUrl;

        @Schema(description = "Liên kết mạng xã hội (JSON hoặc text)")
        private String socialLinks;

        @Schema(description = "Tài liệu đính kèm (URL)")
        private String documentUrl;

        public ApplyArtistRequest() {}

        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        public String getSocialLinks() { return socialLinks; }
        public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
        public String getDocumentUrl() { return documentUrl; }
        public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }
    }

    @Schema(description = "Phản hồi trạng thái đăng ký nghệ sĩ")
    public static class ApplicationStatusResponse {
        @Schema(description = "ID đơn đăng ký")
        private Long id;

        @Schema(description = "Nghệ danh")
        private String stageName;

        @Schema(description = "Trạng thái")
        private String status; // PENDING, APPROVED, REJECTED

        @Schema(description = "Ngày nộp đơn")
        private LocalDateTime submittedAt;

        @Schema(description = "Ngày xử lý")
        private LocalDateTime processedAt;

        @Schema(description = "Lý do từ chối (nếu có)")
        private String rejectionReason;

        @Schema(description = "Ghi chú của admin")
        private String adminNotes;

        public ApplicationStatusResponse() {}

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getSubmittedAt() { return submittedAt; }
        public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
        public LocalDateTime getProcessedAt() { return processedAt; }
        public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
        public String getRejectionReason() { return rejectionReason; }
        public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
        public String getAdminNotes() { return adminNotes; }
        public void setAdminNotes(String adminNotes) { this.adminNotes = adminNotes; }
    }
}