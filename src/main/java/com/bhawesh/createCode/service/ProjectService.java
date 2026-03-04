package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.project.ProjectRequest;
import com.bhawesh.createCode.dto.project.ProjectResponse;
import com.bhawesh.createCode.dto.project.ProjectSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {
    List<ProjectSummaryResponse> getAllUserProjects(Long userId);

    ProjectResponse getProject(Long userId, Long id);

    ProjectResponse createProject(Long userId, ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request, Long userId);

    Void softDelete(Long id, Long userId);
}
