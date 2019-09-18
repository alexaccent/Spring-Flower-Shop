package com.accenture.flowershop.frontend.rest;

import com.accenture.flowershop.backend.dao.CustomerDao;
import com.accenture.flowershop.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("/checked")
public class CheckRestServices {

    @Autowired
    private CustomerDao userDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public boolean checkUser(@WebParam(name = "login") String login) {

        User userLogin = userDao.getOne(login);

        if (userLogin != null) {
            return true;
        } else {
            return false;
        }
    }
}
