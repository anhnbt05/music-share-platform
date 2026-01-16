package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.admin.*;
import com.example.musicshareserver.dto.response.ApiResponse;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.service.admin.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin management APIs")
public class AdminController {

    private final AdminService adminService;

    /* ================= USER ================= */

    @Operation(summary = "Gán role cho user")
    @PostMapping("/users/assign-role")
    public ResponseEntity<ApiResponse<Void>> assignRole(
            @Valid @RequestBody AssignRoleRequest request
    ) {
        adminService.assignRole(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Gán role thành công", null));
    }

    @Operation(summary = "Tìm kiếm tài khoản")
    @PostMapping("/users/search")
    public ResponseEntity<ApiResponse<Page<User>>> searchAccounts(
            @Valid @RequestBody SearchAccountRequest request
    ) {
        Page<User> result = adminService.searchAccounts(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách tài khoản thành công", result));
    }

    @Operation(summary = "Xóa (soft delete) tài khoản")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(
            @PathVariable Long userId,
            @Valid @RequestBody DeleteAccountRequest request
    ) {
        adminService.deleteAccount(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa tài khoản thành công", null));
    }

    @Operation(summary = "Chi tiết tài khoản")
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserDetails(
            @PathVariable Long userId
    ) {
        User user = adminService.getUserDetails(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy chi tiết tài khoản thành công", user));
    }

    /* ================= ARTIST APPLICATION ================= */

    @Operation(summary = "Danh sách đơn đăng ký artist")
    @GetMapping("/artist-applications")
    public ResponseEntity<ApiResponse<Page<ArtistApplication>>> getArtistApplications(
            @RequestParam int page,
            @RequestParam int limit,
            @RequestParam String status
    ) {
        Page<ArtistApplication> result =
                adminService.getArtistApplications(page, limit, status);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy danh sách đơn đăng ký thành công", result)
        );
    }

    @Operation(summary = "Xử lý đơn đăng ký artist")
    @PostMapping("/artist-applications/{id}/process")
    public ResponseEntity<ApiResponse<Void>> processArtistApplication(
            @PathVariable Long id,
            @Valid @RequestBody ProcessArtistApplicationRequest request
    ) {
        adminService.processArtistApplication(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xử lý đơn đăng ký thành công", null)
        );
    }

    /* ================= REPORT ================= */

    @Operation(summary = "Danh sách báo cáo")
    @GetMapping("/reports")
    public ResponseEntity<ApiResponse<Page<Report>>> getReports(
            @RequestParam int page,
            @RequestParam int limit,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type
    ) {
        Page<Report> result =
                adminService.getReports(page, limit, status, type);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy danh sách báo cáo thành công", result)
        );
    }

    @Operation(summary = "Giải quyết báo cáo")
    @PostMapping("/reports/{id}/resolve")
    public ResponseEntity<ApiResponse<Void>> resolveReport(
            @PathVariable Long id,
            @Valid @RequestBody ResolveReportRequest request
    ) {
        adminService.resolveReport(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Giải quyết báo cáo thành công", null)
        );
    }

    /* ================= MUSIC ================= */

    @Operation(summary = "Xóa bài hát")
    @DeleteMapping("/music/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMusic(
            @PathVariable Long id
    ) {
        adminService.deleteMusic(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa bài hát thành công", null)
        );
    }
}
