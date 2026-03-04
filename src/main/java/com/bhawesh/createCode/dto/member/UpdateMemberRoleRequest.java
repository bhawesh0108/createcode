package com.bhawesh.createCode.dto.member;

import com.bhawesh.createCode.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
       @NotNull ProjectRole role
) {
}
