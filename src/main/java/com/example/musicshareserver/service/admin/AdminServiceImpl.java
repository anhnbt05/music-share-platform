package com.example.musicshareserver.service.admin;

import com.example.musicshareserver.dto.request.admin.*;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.exception.ApiException;
import com.example.musicshareserver.repository.*;

import com.example.musicshareserver.service.admin.AdminService;
import com.example.musicshareserver.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ArtistProfileRepository artistProfileRepository;
    private final ArtistApplicationRepository artistApplicationRepository;
    private final ReportRepository reportRepository;
    private final MusicRepository musicRepository;
    private final StorageService storageService;

    /* ================= USER ================= */

    @Override
    public void assignRole(AssignRoleRequest request) {
        User user = userRepository.findByIdAndIsDeletedFalse(request.getUserId())
                .orElseThrow(() -> new ApiException("Người dùng không tồn tại"));

        if ("USER".equals(user.getRole()) && "ARTIST".equals(request.getNewRole())) {
            artistProfileRepository.findByUserId(user.getId())
                    .orElseGet(() -> artistProfileRepository.save(
                            ArtistProfile.builder()
                                    .user(user)
                                    .stageName(user.getName())
                                    .status("ACTIVE")
                                    .updatedAt(LocalDateTime.now())
                                    .build()
                    ));
        }

        if ("ARTIST".equals(user.getRole()) && "USER".equals(request.getNewRole())) {
            artistProfileRepository.findByUserId(user.getId())
                    .ifPresent(profile -> {
                        profile.setStatus("INACTIVE");
                        profile.setUpdatedAt(LocalDateTime.now());
                    });
        }

        user.setRole(request.getNewRole());
        userRepository.save(user);
    }

    @Override
    public Page<User> searchAccounts(SearchAccountRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPage() - 1,
                request.getLimit(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        return userRepository.search(request.getQuery(), pageable);
    }

    @Override
    public void deleteAccount(Long userId, DeleteAccountRequest request) {
        if (!Boolean.TRUE.equals(request.getConfirm())) {
            throw new ApiException("Vui lòng xác nhận xóa tài khoản");
        }

        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ApiException("Người dùng không tồn tại"));

        user.setIsDeleted(true);
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public User getUserDetails(Long userId) {
        return userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ApiException("Người dùng không tồn tại"));
    }

    /* ================= ARTIST APPLICATION ================= */

    @Override
    public Page<ArtistApplication> getArtistApplications(int page, int limit, String status) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        return artistApplicationRepository.findByStatus(status, pageable);
    }

    @Override
    public void processArtistApplication(Long applicationId, ProcessArtistApplicationRequest request) {
        ArtistApplication application = artistApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApiException("Đơn đăng ký không tồn tại"));

        if (!"PENDING".equals(application.getStatus())) {
            throw new ApiException("Đơn đăng ký đã được xử lý");
        }

        if ("APPROVE".equals(request.getAction())) {
            User user = application.getUser();
            user.setRole("ARTIST");

            artistProfileRepository.save(
                    ArtistProfile.builder()
                            .user(user)
                            .stageName(application.getStageName())
                            .bio(application.getBio())
                            .photoUrl(application.getPhotoUrl())
                            .status("ACTIVE")
                            .updatedAt(LocalDateTime.now())
                            .build()
            );

            application.setStatus("APPROVED");
        } else {
            application.setStatus("REJECTED");
            application.setRejectionReason(request.getRejectionReason());
        }
    }

    /* ================= REPORT ================= */

    @Override
    public Page<Report> getReports(int page, int limit, String status, String type) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("reportDate").descending());
        return reportRepository.filter(status, type, pageable);
    }

    @Override
    public void resolveReport(Long reportId, ResolveReportRequest request) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new ApiException("Báo cáo không tồn tại"));

        report.setStatus("RESOLVED");
        report.setResolutionNotes(request.getResolutionNotes());
    }

    /* ================= MUSIC ================= */

    @Override
    public void deleteMusic(Long musicId) {
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new ApiException("Bài hát không tồn tại"));

        storageService.deleteFile("music", music.getFileUrl());
        music.setDeletedAt(LocalDateTime.now());
    }
}
