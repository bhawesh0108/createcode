package com.bhawesh.createCode.controller;

import com.bhawesh.createCode.dto.member.MemberResponse;
import com.bhawesh.createCode.dto.member.ProjectMemberRequest;
import com.bhawesh.createCode.dto.member.UpdateMemberRoleRequest;
import com.bhawesh.createCode.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllProjectMembers(@PathVariable Long projectId){
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteProjectMember(@PathVariable Long projectId, @RequestBody @Valid ProjectMemberRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteProjectMember(projectId,request)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId,@PathVariable Long memberId,@RequestBody @Valid UpdateMemberRoleRequest request){
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long projectId,@PathVariable Long memberId){
        projectMemberService.deleteProjectMember(projectId,memberId);
        return ResponseEntity.noContent().build();
    }

}
