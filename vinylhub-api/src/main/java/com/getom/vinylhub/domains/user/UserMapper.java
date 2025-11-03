package com.getom.vinylhub.domains.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResource toResource(User user);

    List<UserResource> toResourceList(List<User> users);

    User createEntityFromDto(CreateUserRequest createUserRequest);

    void updateEntityFromDto(@MappingTarget User user, UpdateUserRequest request);
}
