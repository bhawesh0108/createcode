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
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId,userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteProjectMember(@PathVariable Long projectId, @RequestBody @Valid ProjectMemberRequest request){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteProjectMember(projectId,request,userId)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId,@PathVariable Long memberId,@RequestBody @Valid UpdateMemberRoleRequest request){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request,userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long projectId,@PathVariable Long memberId){
        Long userId = 1L;
        projectMemberService.deleteProjectMember(projectId,memberId,userId);
        return ResponseEntity.noContent().build();
    }

}
