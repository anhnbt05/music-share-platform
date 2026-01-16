package com.example.musicshareserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID>
        extends JpaRepository<T, ID> {

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.isDeleted = true WHERE e.id = :id")
    void softDelete(@Param("id") ID id);
}
