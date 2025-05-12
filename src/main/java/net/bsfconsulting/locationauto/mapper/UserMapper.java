package net.bsfconsulting.locationauto.mapper;

import net.bsfconsulting.locationauto.dto.UserDto;
import net.bsfconsulting.locationauto.entity.User;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}