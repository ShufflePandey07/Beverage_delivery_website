package com.beverage.beverage.Service.impl;

import com.beverage.beverage.Repo.UserRepo;
import com.beverage.beverage.Service.UserService;
import com.beverage.beverage.config.PasswordEncoderUtil;
import com.beverage.beverage.dto.UserDto;
import com.beverage.beverage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public void save(UserDto userDto) {
        User user=userRepo.findByEmail(userDto.getEmail()).orElse(new User());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
    }

    @Override
    public Optional<User> getActiveUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByEmail(authentication.getName());
    }

    @Override
    public void edit(UserDto userDto) {

        User user = userRepo.findByEmail(userDto.getEmail()).get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        userRepo.save(user);
    }

    @Override
    public void delete() {
        userRepo.delete(getActiveUser().get());
    }
}
