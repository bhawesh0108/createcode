package com.bhawesh.createCode.repository;

import com.bhawesh.createCode.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("select p from Project p where p.deletedAt IS NULL AND EXISTS (SELECT 1 FROM ProjectMember pm WHERE pm.id.userId = :userId AND pm.id.projectId = p.id) ORDER BY p.updatedAt DESC")
    List<Project> findAllAccessibleProjectsByUser(@Param("userId") Long userId);

    @Query("select p from Project p where p.deletedAt is NULL AND EXISTS (SELECT 1 FROM ProjectMember pm WHERE pm.id.userId = :userId AND pm.id.projectId = :projectId) AND p.id = :projectId")
    Optional<Project> getAccessibleUserProject(@Param("userId") Long userId,@Param("projectId") Long projectId);
}
