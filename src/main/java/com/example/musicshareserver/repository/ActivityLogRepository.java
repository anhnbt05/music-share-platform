package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository
        extends JpaRepository<ActivityLog, Integer> {

    List<ActivityLog> findByUserId(Integer userId);
}
