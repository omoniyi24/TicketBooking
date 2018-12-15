package com.ticket.booking.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Data
@Component
public class Register {

    private Long id;
    private String username;
    private String password;

}
