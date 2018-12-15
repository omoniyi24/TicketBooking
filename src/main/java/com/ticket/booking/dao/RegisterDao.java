package com.ticket.booking.dao;

import com.ticket.booking.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class RegisterDao {

    private SimpleJdbcCall createUser;

    @Autowired
    public void dataSource(DataSource dataSource){
        createUser = new SimpleJdbcCall().returningResultSet();
    }

    public void createUser(Register register) {

    }
}
