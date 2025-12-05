// File: com/example/musicshareserver/modules/admin/dto/ActivityLogDTO.java
package com.example.musicshareserver.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public class ActivityLogDTO {

    @Schema(description = "Phản hồi nhật ký hoạt động")
    public static class ActivityLogResponse {
        @Schema(description = "ID nhật ký")
        private Long id;

        @Schema(description = "Người dùng")
        private UserInfo user;

        @Schema(description = "Loại hành động")
        private String actionType;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "Thời gian")
        private LocalDateTime timestamp;

        @Schema(description = "Đối tượng liên quan")
        private RelatedObject relatedObject;

        @Schema(description = "Thông tin bổ sung")
        private Map<String, Object> metadata;

        public ActivityLogResponse() {}

        @Schema(description = "Thông tin người dùng")
        public static class UserInfo {
            private Long id;
            private String email;
            private String name;
            private String role;

            public UserInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public String getRole() { return role; }
            public void setRole(String role) { this.role = role; }
        }

        @Schema(description = "Đối tượng liên quan")
        public static class RelatedObject {
            private String type; // MUSIC, USER, ALBUM, PLAYLIST, COMMENT
            private Long id;
            private String name;

            public RelatedObject() {}

            public String getType() { return type; }
            public void setType(String type) { this.type = type; }
            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public UserInfo getUser() { return user; }
        public void setUser(UserInfo user) { this.user = user; }
        public String getActionType() { return actionType; }
        public void setActionType(String actionType) { this.actionType = actionType; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public RelatedObject getRelatedObject() { return relatedObject; }
        public void setRelatedObject(RelatedObject relatedObject) { this.relatedObject = relatedObject; }
        public Map<String, Object> getMetadata() { return metadata; }
        public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    }

    @Schema(description = "Yêu cầu tìm kiếm nhật ký hoạt động")
    public static class SearchActivityLogsRequest {
        @Schema(description = "ID người dùng")
        private Long userId;

        @Schema(description = "Loại hành động")
        private String actionType;

        @Schema(description = "Loại đối tượng")
        private String objectType;

        @Schema(description = "Ngày bắt đầu")
        private LocalDateTime startDate;

        @Schema(description = "Ngày kết thúc")
        private LocalDateTime endDate;

        public SearchActivityLogsRequest() {}

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getActionType() { return actionType; }
        public void setActionType(String actionType) { this.actionType = actionType; }
        public String getObjectType() { return objectType; }
        public void setObjectType(String objectType) { this.objectType = objectType; }
        public LocalDateTime getStartDate() { return startDate; }
        public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
        public LocalDateTime getEndDate() { return endDate; }
        public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    }
}