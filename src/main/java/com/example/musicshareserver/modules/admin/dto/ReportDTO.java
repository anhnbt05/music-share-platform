// File: com/example/musicshareserver/modules/admin/dto/ReportDTO.java
package com.example.musicshareserver.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ReportDTO {

    @Schema(description = "Phản hồi báo cáo")
    public static class ReportResponse {
        @Schema(description = "ID báo cáo")
        private Long id;

        @Schema(description = "Người báo cáo")
        private ReporterInfo reporter;

        @Schema(description = "Loại báo cáo")
        private String reportType;

        @Schema(description = "Lý do báo cáo")
        private String reason;

        @Schema(description = "Mô tả chi tiết")
        private String description;

        @Schema(description = "Ngày báo cáo")
        private LocalDateTime reportDate;

        @Schema(description = "Trạng thái")
        private String status;

        @Schema(description = "Nội dung bị báo cáo")
        private ReportedContent reportedContent;

        @Schema(description = "Ghi chú xử lý")
        private String resolutionNotes;

        @Schema(description = "Ngày xử lý")
        private LocalDateTime resolvedAt;

        public ReportResponse() {}

        @Schema(description = "Thông tin người báo cáo")
        public static class ReporterInfo {
            private Long id;
            private String email;
            private String name;

            public ReporterInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
        }

        @Schema(description = "Nội dung bị báo cáo")
        public static class ReportedContent {
            private String type; // MUSIC, ARTIST, USER, COMMENT, PLAYLIST
            private Long id;
            private String title;
            private String description;
            private String ownerName;

            public ReportedContent() {}

            public String getType() { return type; }
            public void setType(String type) { this.type = type; }
            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public String getDescription() { return description; }
            public void setDescription(String description) { this.description = description; }
            public String getOwnerName() { return ownerName; }
            public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public ReporterInfo getReporter() { return reporter; }
        public void setReporter(ReporterInfo reporter) { this.reporter = reporter; }
        public String getReportType() { return reportType; }
        public void setReportType(String reportType) { this.reportType = reportType; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public LocalDateTime getReportDate() { return reportDate; }
        public void setReportDate(LocalDateTime reportDate) { this.reportDate = reportDate; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public ReportedContent getReportedContent() { return reportedContent; }
        public void setReportedContent(ReportedContent reportedContent) { this.reportedContent = reportedContent; }
        public String getResolutionNotes() { return resolutionNotes; }
        public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
        public LocalDateTime getResolvedAt() { return resolvedAt; }
        public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    }

    @Schema(description = "Yêu cầu xử lý báo cáo")
    public static class ResolveReportRequest {
        @Schema(description = "Ghi chú xử lý", required = true)
        @NotBlank(message = "Ghi chú xử lý là bắt buộc")
        private String resolutionNotes;

        @Schema(description = "Hành động thực hiện", example = "DELETE_CONTENT, WARN_USER, IGNORE")
        private String action;

        public ResolveReportRequest() {}

        public String getResolutionNotes() { return resolutionNotes; }
        public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
    }

    @Schema(description = "Yêu cầu tìm kiếm báo cáo")
    public static class SearchReportsRequest {
        @Schema(description = "Trạng thái", example = "PENDING")
        private String status;

        @Schema(description = "Loại báo cáo", example = "MUSIC")
        private String reportType;

        @Schema(description = "Ngày bắt đầu")
        private LocalDateTime startDate;

        @Schema(description = "Ngày kết thúc")
        private LocalDateTime endDate;

        public SearchReportsRequest() {}

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getReportType() { return reportType; }
        public void setReportType(String reportType) { this.reportType = reportType; }
        public LocalDateTime getStartDate() { return startDate; }
        public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
        public LocalDateTime getEndDate() { return endDate; }
        public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    }
}