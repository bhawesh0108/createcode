package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.project.ProjectRequest;
import com.bhawesh.createCode.dto.project.ProjectResponse;
import com.bhawesh.createCode.dto.project.ProjectSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {
    List<ProjectSummaryResponse> getAllUserProjects();

    ProjectResponse getProject(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    Void softDelete(Long id);
}
