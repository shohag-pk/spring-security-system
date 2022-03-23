package com.example.springsecuritysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserModel {
    @Size(min = 1, max = 100, message = "Invalid username.")
    private String userName;
    @Size(min = 1, max = 50, message = "Invalid password length.")
    private String password;
    @Size(min = 1, max = 50, message = "Invalid confirm password length.")
    private String confirmPassword;
    @Size(min = 1, max = 50, message = "Invalid name length.")
    private String name;
    @Size(min = 1, max = 15, message = "Invalid contact number length.")
    private String mobile;
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Invalid email id")
    private String email;
    @NotEmpty(message = "Please select role")
    private Set<Integer> roles;
    private Set<Integer> permissions;
    @Min(value = 1, message = "Department can not be empty.")
    @Max(value = Long.MAX_VALUE, message = "Invalid department.")
    private long department;
    @Min(value = 1, message = "Designation can not be empty.")
    @Max(value = Long.MAX_VALUE, message = "Invalid Designation.")
    private long designation;
    @Min(value = 1, message = "Territory can not be empty.")
    @Max(value = Long.MAX_VALUE, message = "Invalid Territory.")
    private long territory;
}
