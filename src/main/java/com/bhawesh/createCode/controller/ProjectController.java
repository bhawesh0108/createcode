package com.bhawesh.createCode.controller;

import com.bhawesh.createCode.dto.project.ProjectRequest;
import com.bhawesh.createCode.dto.project.ProjectResponse;
import com.bhawesh.createCode.dto.project.ProjectSummaryResponse;
import com.bhawesh.createCode.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getAllUserProjects(){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getAllUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getProject(userId,id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(userId,request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(id,request,userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.softDelete(id,userId));
    }


}
