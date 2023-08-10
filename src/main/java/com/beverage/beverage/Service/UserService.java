package com.beverage.beverage.Service;

import com.beverage.beverage.dto.UserDto;
import com.beverage.beverage.entity.User;

import java.util.Optional;

public interface UserService {

    void save(UserDto userDto);

    Optional<User> getActiveUser();

    void edit(UserDto userDto);

    void delete();
}
