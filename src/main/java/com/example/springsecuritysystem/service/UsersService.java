package com.example.springsecuritysystem.service;




import com.example.springsecuritysystem.entity.system.user.Users;
import com.example.springsecuritysystem.model.AddUserModel;

import java.util.Optional;

/**
 * @author Touhid Hossain
 */
public interface UsersService {
    Users addNewUser(AddUserModel addUserModel);

    Optional<Users> getUserById(long id);

    Optional<Users> getUserByUserName(String userName);

    Optional<Users> getUserByEmail(String email);

    Optional<Users> getUserByTerritory(long teritory);

}
