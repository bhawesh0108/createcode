package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.project.FileContentResponse;
import com.bhawesh.createCode.dto.project.FileNode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
