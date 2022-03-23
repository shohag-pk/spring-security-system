package com.example.springsecuritysystem.controller;

import com.example.springsecuritysystem.entity.User;
import com.example.springsecuritysystem.event.RegistrationCompleteEvent;
import com.example.springsecuritysystem.model.UserModel;
import com.example.springsecuritysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

  /*  public RegistrationController(UserService userService,
                                  ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }*/

    @PostMapping("/register")
    private String registerUser(@RequestBody UserModel userModel , final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,
                applicationUrl(request)
        ));
        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

}
