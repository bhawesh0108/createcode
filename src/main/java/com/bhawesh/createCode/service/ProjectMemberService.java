package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.member.MemberResponse;
import com.bhawesh.createCode.dto.member.ProjectMemberRequest;
import com.bhawesh.createCode.dto.member.UpdateMemberRoleRequest;

import java.util.List;


public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteProjectMember(Long projectId, ProjectMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    void deleteProjectMember(Long projectId, Long memberId, Long userId);
}


