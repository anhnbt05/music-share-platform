// File: com/example/musicshareserver/modules/user/dto/ReportDTO.java
package com.example.musicshareserver.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReportDTO {

    @Schema(description = "Yêu cầu báo cáo nội dung")
    public static class CreateReportRequest {
        @Schema(description = "Loại nội dung báo cáo", required = true,
                example = "MUSIC, ARTIST, USER, COMMENT, PLAYLIST")
        @NotBlank(message = "Loại nội dung là bắt buộc")
        private String targetType;

        @Schema(description = "ID của nội dung báo cáo", required = true)
        @NotNull(message = "ID nội dung là bắt buộc")
        private Long targetId;

        @Schema(description = "Lý do báo cáo", required = true,
                example = "SPAM, INAPPROPRIATE, COPYRIGHT, HARASSMENT, OTHER")
        @NotBlank(message = "Lý do báo cáo là bắt buộc")
        private String reason;

        @Schema(description = "Mô tả chi tiết")
        private String description;

        public CreateReportRequest() {}

        public String getTargetType() { return targetType; }
        public void setTargetType(String targetType) { this.targetType = targetType; }
        public Long getTargetId() { return targetId; }
        public void setTargetId(Long targetId) { this.targetId = targetId; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    @Schema(description = "Phản hồi sau khi báo cáo")
    public static class ReportResponse {
        @Schema(description = "ID báo cáo")
        private Long id;

        @Schema(description = "Loại nội dung")
        private String targetType;

        @Schema(description = "Lý do báo cáo")
        private String reason;

        @Schema(description = "Trạng thái")
        private String status;

        @Schema(description = "Thông báo")
        private String message;

        public ReportResponse() {}

        public ReportResponse(Long id, String targetType, String reason, String status, String message) {
            this.id = id;
            this.targetType = targetType;
            this.reason = reason;
            this.status = status;
            this.message = message;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTargetType() { return targetType; }
        public void setTargetType(String targetType) { this.targetType = targetType; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    @Schema(description = "Phản hồi lịch sử báo cáo")
    public static class ReportHistoryResponse {
        @Schema(description = "ID báo cáo")
        private Long id;

        @Schema(description = "Loại nội dung")
        private String targetType;

        @Schema(description = "Tên nội dung")
        private String targetName;

        @Schema(description = "Lý do")
        private String reason;

        @Schema(description = "Trạng thái")
        private String status;

        @Schema(description = "Ngày báo cáo")
        private String reportDate;

        public ReportHistoryResponse() {}

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTargetType() { return targetType; }
        public void setTargetType(String targetType) { this.targetType = targetType; }
        public String getTargetName() { return targetName; }
        public void setTargetName(String targetName) { this.targetName = targetName; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getReportDate() { return reportDate; }
        public void setReportDate(String reportDate) { this.reportDate = reportDate; }
    }
}