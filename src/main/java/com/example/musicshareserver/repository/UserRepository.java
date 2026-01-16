package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    @Query("""
        SELECT u FROM User u
        WHERE u.isDeleted = false
          AND (
            LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(u.name) LIKE LOWER(CONCAT('%', :query, '%'))
          )
    """)
    Page<User> search(@Param("query") String query, Pageable pageable);
}
