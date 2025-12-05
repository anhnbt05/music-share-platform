package com.example.musicshareserver.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PaginationDTO {

    @Schema(description = "Yêu cầu phân trang")
    public static class PageRequest {
        @Schema(description = "Số trang", example = "1")
        private Integer page = 1;

        @Schema(description = "Số lượng mỗi trang", example = "20")
        private Integer size = 20;

        @Schema(description = "Sắp xếp theo", example = "createdAt")
        private String sortBy = "createdAt";

        @Schema(description = "Thứ tự sắp xếp", example = "DESC")
        private String sortDirection = "DESC";

        public PageRequest() {}

        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        public Integer getSize() { return size; }
        public void setSize(Integer size) { this.size = size; }
        public String getSortBy() { return sortBy; }
        public void setSortBy(String sortBy) { this.sortBy = sortBy; }
        public String getSortDirection() { return sortDirection; }
        public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
    }

    @Schema(description = "Phản hồi phân trang")
    public static class PageResponse<T> {
        @Schema(description = "Dữ liệu")
        private java.util.List<T> content;

        @Schema(description = "Tổng số phần tử")
        private Long totalElements;

        @Schema(description = "Tổng số trang")
        private Integer totalPages;

        @Schema(description = "Trang hiện tại")
        private Integer currentPage;

        @Schema(description = "Kích thước trang")
        private Integer pageSize;

        @Schema(description = "Trang đầu tiên")
        private Boolean first;

        @Schema(description = "Trang cuối cùng")
        private Boolean last;

        public PageResponse() {}

        public PageResponse(java.util.List<T> content, Long totalElements, Integer totalPages,
                            Integer currentPage, Integer pageSize) {
            this.content = content;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.first = currentPage == 1;
            this.last = currentPage.equals(totalPages);
        }

        // Getters and Setters
        public java.util.List<T> getContent() { return content; }
        public void setContent(java.util.List<T> content) { this.content = content; }
        public Long getTotalElements() { return totalElements; }
        public void setTotalElements(Long totalElements) { this.totalElements = totalElements; }
        public Integer getTotalPages() { return totalPages; }
        public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
        public Integer getCurrentPage() { return currentPage; }
        public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }
        public Integer getPageSize() { return pageSize; }
        public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
        public Boolean getFirst() { return first; }
        public void setFirst(Boolean first) { this.first = first; }
        public Boolean getLast() { return last; }
        public void setLast(Boolean last) { this.last = last; }
    }

    @Schema(description = "Phản hồi thành công")
    public static class SuccessResponse {
        @Schema(description = "Trạng thái", example = "success")
        private String status = "success";

        @Schema(description = "Thông báo")
        private String message;

        @Schema(description = "Dữ liệu")
        private Object data;

        @Schema(description = "Mã lỗi")
        private String code;

        public SuccessResponse() {}

        public SuccessResponse(String message) {
            this.message = message;
        }

        public SuccessResponse(String message, Object data) {
            this.message = message;
            this.data = data;
        }

        // Getters and Setters
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Object getData() { return data; }
        public void setData(Object data) { this.data = data; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    @Schema(description = "Phản hồi lỗi")
    public static class ErrorResponse {
        @Schema(description = "Trạng thái", example = "error")
        private String status = "error";

        @Schema(description = "Thông báo lỗi")
        private String message;

        @Schema(description = "Mã lỗi")
        private String code;

        @Schema(description = "Thời gian")
        private String timestamp;

        @Schema(description = "Đường dẫn")
        private String path;

        public ErrorResponse() {
            this.timestamp = java.time.LocalDateTime.now().toString();
        }

        public ErrorResponse(String message) {
            this();
            this.message = message;
        }

        public ErrorResponse(String message, String code) {
            this();
            this.message = message;
            this.code = code;
        }

        // Getters and Setters
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
    }
}