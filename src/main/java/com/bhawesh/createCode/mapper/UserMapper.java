package com.bhawesh.createCode.mapper;

import com.bhawesh.createCode.dto.auth.SignUpRequest;
import com.bhawesh.createCode.dto.auth.UserProfileResponse;
import com.bhawesh.createCode.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignUpRequest request);

    @Mapping(target="name", source="name")
    @Mapping(target="username", source="username")
    UserProfileResponse toUserProfileResponseFromUser(User user);

}
