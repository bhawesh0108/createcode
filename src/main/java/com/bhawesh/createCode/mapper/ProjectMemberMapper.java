package com.bhawesh.createCode.mapper;

import com.bhawesh.createCode.dto.member.MemberResponse;
import com.bhawesh.createCode.entity.Project;
import com.bhawesh.createCode.entity.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

//    @Mapping(target= "userId",source="owner.id")
//    @Mapping(target= "name",source = "owner.name")
//    @Mapping(target = "email",source = "owner.email")
    @Mapping(target = "role",constant = "OWNER")
    MemberResponse toProjectMemberResponsefromOwnerProject(Project project);

    @Mapping(target= "userId",source="user.id")
    @Mapping(target= "name",source = "user.name")
    @Mapping(target = "username",source = "user.username")
    @Mapping(target = "role",source = "projectRole")
    @Mapping(target = "invitedAt" , source = "invitedAt")
    MemberResponse toMemberResponsefromProjectMember(ProjectMember projectMember);
}
