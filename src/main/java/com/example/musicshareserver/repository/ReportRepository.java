package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository
        extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {

    default Page<Report> filter(String status, String type, Pageable pageable) {
        return findAll((root, query, cb) -> {
            var predicates = cb.conjunction();

            if (status != null) {
                predicates.getExpressions().add(
                        cb.equal(root.get("status"), status)
                );
            }

            if (type != null) {
                predicates.getExpressions().add(
                        cb.equal(root.get("reportType"), type)
                );
            }

            return predicates;
        }, pageable);
    }
}
