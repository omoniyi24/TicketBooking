package com.ticket.booking.service;

import com.ticket.booking.dao.RegisterDao;
import com.ticket.booking.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterDao registerDao;

    public void createUser(Register register) {
        registerDao.createUser(register);
    }
}
