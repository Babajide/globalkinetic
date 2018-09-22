package co.za.randstack.controller.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import co.za.randstack.model.dto.UserDto;
import co.za.randstack.model.entity.User;
import lombok.extern.slf4j.Slf4j;


/**
 * Separation of concerns: This Mapper is used to transform domain entity to data object to UI
 */
@Slf4j
public class UserMapper {
    public static User toDomainObject(UserDto userDto) {
        
        return new User(userDto.getPhoneNumber(), userDto.getUserName(), userDto.getPassword());
    }
    
    public static UserDto toDto(User user) {
        log.debug("transforming Domain Object to DTO ");
        return new UserDto(user.getUserName(), user.getPhoneNumber());
    }
    
    public static List<UserDto> toDtos(Collection<User> list) {
        if(list == null){
            return new ArrayList<>();
        }
        log.debug("transforming Domain Objects to DTOs ");
        return list.stream().map(UserMapper::toDto).collect(Collectors.toList());
        
    }
}
