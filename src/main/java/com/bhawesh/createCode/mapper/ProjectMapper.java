package com.bhawesh.createCode.mapper;

import com.bhawesh.createCode.dto.project.ProjectResponse;
import com.bhawesh.createCode.dto.project.ProjectSummaryResponse;
import com.bhawesh.createCode.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);
}
