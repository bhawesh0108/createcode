package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.member.MemberResponse;
import com.bhawesh.createCode.dto.member.ProjectMemberRequest;
import com.bhawesh.createCode.dto.member.UpdateMemberRoleRequest;

import java.util.List;


public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteProjectMember(Long projectId, ProjectMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void deleteProjectMember(Long projectId, Long memberId);
}


