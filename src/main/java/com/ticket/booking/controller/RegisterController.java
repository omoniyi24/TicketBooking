package com.ticket.booking.controller;

import com.ticket.booking.model.Register;
import com.ticket.booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return  modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ModelAndView createUser(@RequestBody @Validated Register register){
        registerService.createUser(register);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return  modelAndView;
    }
}
