package com.beverage.beverage.controller;

import com.beverage.beverage.Service.UserService;
import com.beverage.beverage.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/user/login";
    }

    @PostMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "redirect:/user/login";
    }

    @PostMapping("/edit")
    public String edit(UserDto userDto){
        userService.edit(userDto);
        return "redirect:/user/login";
    }

    @GetMapping("/view")
    public String edit(Model model){
        model.addAttribute("user",userService.getActiveUser().get());
        return "viewProfile";
    }

    @PostMapping("/delete")
    public String delete(){
        userService.delete();
        return "redirect:/user/login";
    }

    @PostMapping("/checkAdmin")
    public String checkAdmin(@RequestParam String password,@RequestParam String name){
        String adminName="admin";
        String adminPassword="admin";
        if(password.equals(adminPassword) && name.equals(adminName)){
            return "redirect:/beverage/list";
        }
        else{
            return "redirect:/dashboard?error";
    }

    }


}
