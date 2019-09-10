package com.bookbusonile.main.controller;

import com.bookbusonile.main.model.User;
import com.bookbusonile.main.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); //resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Long userId = user.getId();
        modelAndView.addObject("userName",
                "Welcome " +  " " + user.getUsername());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        LOGGER.debug("<<<Registering User>>>" + user.getUsername());
        User userExists = userService.findUserByUsername(user.getUsername());
        if(userExists != null){
            LOGGER.info("<<<Username " + user.getUsername() + " already exist>>>");

            bindingResult
                    .rejectValue("username", "error.user",
                            "Username Already Exist!!!");
            modelAndView.addObject("message", "User with that Email Address already Exist");
            modelAndView.setViewName("register");
        }
        if(bindingResult.hasErrors()){
//            modelAndView.addObject("message", "Invalid Form Data!!!");
            modelAndView.setViewName("register");
        }else{
            if(user.getPassword().equals(user.getCpassword())){

                userService.saveUser(user);
                LOGGER.info("<<<Username " + user.getUsername() + " was registered succesfully>>>");
                modelAndView.addObject("user", new User());
                modelAndView.addObject("message", "User has been registered successfully!!!");
                modelAndView.setViewName("register");
            }else{
                LOGGER.info("<<<Passwords does not match>>>");
                modelAndView.addObject("user", new User());
                modelAndView.addObject("message", "Confirm Password is not equal to Password");
                modelAndView.setViewName("register");
            }
        }



        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


}
