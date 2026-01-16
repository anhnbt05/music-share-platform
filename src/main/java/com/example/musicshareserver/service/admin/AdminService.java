package com.example.musicshareserver.service.admin;

import com.example.musicshareserver.dto.request.admin.*;
import com.example.musicshareserver.entity.*;
import org.springframework.data.domain.Page;

public interface AdminService {

    void assignRole(AssignRoleRequest request);

    Page<User> searchAccounts(SearchAccountRequest request);

    void deleteAccount(Long userId, DeleteAccountRequest request);

    User getUserDetails(Long userId);

    Page<ArtistApplication> getArtistApplications(int page, int limit, String status);

    void processArtistApplication(Long applicationId, ProcessArtistApplicationRequest request);

    Page<Report> getReports(int page, int limit, String status, String type);

    void resolveReport(Long reportId, ResolveReportRequest request);

    void deleteMusic(Long musicId);
}
