package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.project.ProjectRequest;
import com.bhawesh.createCode.dto.project.ProjectResponse;
import com.bhawesh.createCode.dto.project.ProjectSummaryResponse;
import com.bhawesh.createCode.entity.Project;
import com.bhawesh.createCode.entity.ProjectMember;
import com.bhawesh.createCode.entity.ProjectMemberId;
import com.bhawesh.createCode.entity.User;
import com.bhawesh.createCode.enums.ProjectRole;
import com.bhawesh.createCode.error.ResourceNotFoundException;
import com.bhawesh.createCode.mapper.ProjectMapper;
import com.bhawesh.createCode.repository.ProjectMemberRepository;
import com.bhawesh.createCode.repository.ProjectRepository;
import com.bhawesh.createCode.repository.UserRepository;
import com.bhawesh.createCode.security.JwtUserPrincipal;
import com.bhawesh.createCode.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Autowired
    AuthServiceImpl authService;

    @Override
    public List<ProjectSummaryResponse> getAllUserProjects() {
       Long userId = authService.getCurrentLoginUserId();
       List<Project> projects = projectRepository.findAllAccessibleProjectsByUser(userId);
       return projectMapper.toProjectSummaryResponseList(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getProject(Long projectId) {
        Long userId = authService.getCurrentLoginUserId();
        Project project  = getAccessibleUserProject(userId,projectId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authService.getCurrentLoginUserId();
        User user = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder().name(request.name()).isPublic(false).build();
        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(userId, project.getId());
        ProjectMember projectMember = ProjectMember.builder().projectRole(ProjectRole.OWNER).project(project).user(user).id(projectMemberId).acceptedAt(Instant.now()).invitedAt(Instant.now()).build();
        projectMemberRepository.save(projectMember);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authService.getCurrentLoginUserId();
        Project project  = getAccessibleUserProject(userId,projectId);
        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public Void softDelete(Long projectId) {
        Long userId = authService.getCurrentLoginUserId();
        return null;
    }

    private  Project getAccessibleUserProject(Long userId,Long projectId){
        return projectRepository.getAccessibleUserProject(userId,projectId).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}
