package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.member.MemberResponse;
import com.bhawesh.createCode.dto.member.ProjectMemberRequest;
import com.bhawesh.createCode.dto.member.UpdateMemberRoleRequest;
import com.bhawesh.createCode.entity.Project;
import com.bhawesh.createCode.entity.ProjectMember;
import com.bhawesh.createCode.entity.ProjectMemberId;
import com.bhawesh.createCode.entity.User;
import com.bhawesh.createCode.error.ResourceNotFoundException;
import com.bhawesh.createCode.mapper.ProjectMemberMapper;
import com.bhawesh.createCode.repository.ProjectMemberRepository;
import com.bhawesh.createCode.repository.ProjectRepository;
import com.bhawesh.createCode.repository.UserRepository;
import com.bhawesh.createCode.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectMemberMapper projectMemberMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthServiceImpl authService;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {
        List<MemberResponse> projectMembersResponseList = new ArrayList<>();
        List<ProjectMember> projectMembers = projectMemberRepository.findByIdProjectId(projectId);
        projectMembersResponseList.addAll(projectMembers.stream().map(projectMember->projectMemberMapper.toMemberResponsefromProjectMember(projectMember)).toList());
        return projectMembersResponseList;
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteProjectMember(Long projectId, ProjectMemberRequest request) {
        Long userId = authService.getCurrentLoginUserId();
        Project project = getAccessibleUserProject(userId,projectId);
        User invitee = userRepository.findByUsername(request.username()).orElseThrow(()->new ResourceNotFoundException(request.username()));

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("User cant invite himself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(invitee.getId(),projectId);
        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Project member already exists");
        }

        ProjectMember projectMember = ProjectMember.builder().id(projectMemberId).user(invitee).project(project).projectRole(request.role()).invitedAt(Instant.now()).build();
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toMemberResponsefromProjectMember(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        ProjectMemberId projectMemberId = new ProjectMemberId(memberId,projectId);
        ProjectMember projectMember =  projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toMemberResponsefromProjectMember(projectMember);
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void deleteProjectMember(Long projectId, Long memberId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(memberId,projectId);
        projectMemberRepository.deleteById(projectMemberId);
    }

    private Project getAccessibleUserProject(Long userId, Long projectId){
        return projectRepository.getAccessibleUserProject(userId,projectId).orElseThrow();
    }
}
