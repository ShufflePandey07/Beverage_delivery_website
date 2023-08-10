package com.beverage.beverage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
