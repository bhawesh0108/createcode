package com.bhawesh.createCode.security;

import com.bhawesh.createCode.enums.Permission;
import com.bhawesh.createCode.repository.ProjectMemberRepository;
import com.bhawesh.createCode.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpressions {

    private final AuthServiceImpl authService;
    private final ProjectMemberRepository projectMemberRepository;

    public boolean hasPermission(Long projectId,Permission permission){
        Long userId = authService.getCurrentLoginUserId();
        return projectMemberRepository.findProjectRoleByUserIdAndProjectId(userId,projectId)
                .map(role->role.getPermission().contains(permission))
                .orElse(false);
    }

    public boolean canViewProject(Long projectId){
        return hasPermission(projectId,Permission.VIEW);
    }

    public boolean canEditProject(Long projectId){
        return hasPermission(projectId,Permission.EDIT);
    }

    public boolean canDeleteProject(Long projectId){
        return hasPermission(projectId,Permission.DELETE);
    }

    public boolean canViewMembers(Long projectId){
        return hasPermission(projectId,Permission.VIEW_MEMBERS);
    }

    public boolean canManageMembers(Long projectId){
        return hasPermission(projectId,Permission.MANAGE_MEMBERS);
    }

}
