package com.bhawesh.createCode.dto.member;

import com.bhawesh.createCode.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectMemberRequest(
     @Email @NotBlank String username,
     @NotNull ProjectRole role
) {
}
