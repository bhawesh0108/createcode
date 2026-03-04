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
import com.bhawesh.createCode.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<ProjectSummaryResponse> getAllUserProjects(Long userId) {

        List<Project> projects = projectRepository.findAllAccessibleProjectsByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projects);

    }

    @Override
    public ProjectResponse getProject(Long userId, Long projectId) {

        Project project  = getAccessibleUserProject(userId,projectId);
        return projectMapper.toProjectResponse(project);

    }

    @Override
    public ProjectResponse createProject(Long userId, ProjectRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder().name(request.name()).isPublic(false).build();
        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(userId, project.getId());
        ProjectMember projectMember = ProjectMember.builder().projectRole(ProjectRole.OWNER).project(project).user(user).id(projectMemberId).acceptedAt(Instant.now()).invitedAt(Instant.now()).build();
        projectMemberRepository.save(projectMember);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project  = getAccessibleUserProject(userId,id);
//        if(!Objects.equals(project.getOwner().getId(), userId)){
//             throw new RuntimeException("User is not allowed to update this project");
//        }
        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public Void softDelete(Long id, Long userId) {
        return null;
    }

    private  Project getAccessibleUserProject(Long userId,Long projectId){
        return projectRepository.getAccessibleUserProject(userId,projectId).orElseThrow(()->new ResourceNotFoundException("Project",projectId));
    }
}
