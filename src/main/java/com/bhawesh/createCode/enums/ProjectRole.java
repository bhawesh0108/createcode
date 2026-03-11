package com.bhawesh.createCode.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static com.bhawesh.createCode.enums.Permission.*;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    EDITOR(Set.of(VIEW,EDIT,DELETE,VIEW_MEMBERS)),
    VIEWER(Set.of(VIEW,VIEW_MEMBERS)),
    OWNER(Set.of(VIEW,EDIT,DELETE,VIEW_MEMBERS,MANAGE_MEMBERS));

    private final Set<Permission> permission;

}
