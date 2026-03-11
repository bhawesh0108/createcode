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
        return ResponseEntity.ok(projectService.getAllUserProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id){
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request){
        return ResponseEntity.ok(projectService.updateProject(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        return ResponseEntity.ok(projectService.softDelete(id));
    }

}
