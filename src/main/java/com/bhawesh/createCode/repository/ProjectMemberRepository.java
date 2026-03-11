package com.bhawesh.createCode.repository;

import com.bhawesh.createCode.entity.ProjectMember;
import com.bhawesh.createCode.entity.ProjectMemberId;
import com.bhawesh.createCode.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    @Query("SELECT pm.projectRole FROM ProjectMember pm WHERE pm.id.userId = :userId AND pm.id.projectId = :projectId")
    Optional<ProjectRole> findProjectRoleByUserIdAndProjectId(Long userId,Long projectId);
}
