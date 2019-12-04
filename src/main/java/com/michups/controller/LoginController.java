package com.michups.controller;

import com.michups.model.LoginCredencials;
import com.michups.model.User;
import com.michups.model.CurrentUser;
import com.michups.response.*;
import com.michups.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@Controller
@RequestMapping("/auth")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)

public class LoginController {

    @Autowired
    private IUserService userService;

    CurrentUser currentUser;

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StringResponse> authorizeUser(@RequestBody LoginCredencials loginCredencials,
                                                        HttpServletRequest request) {

        currentUser = (CurrentUser) request.getAttribute("currentUser");

        if (currentUser == null) {
            currentUser = new CurrentUser();
        }


        User user = userService.findByUsername(loginCredencials.getUsername());

        StringResponse stringResponse;

        if (user.getPassword().equals(loginCredencials.getPassword())) {

            currentUser = new CurrentUser();
            currentUser.setAdmin(user.getAdmin());
            currentUser.setAuthorize(true);
            currentUser.setUsername(user.getUsername());
            currentUser.setQuote(user.getQuote());
            stringResponse = new StringResponse(Response.SUCCESS.getValue(), "account logged in");

            request.getSession().setAttribute("currentUser", currentUser);

        } else {

            stringResponse = new StringResponse(Response.FAILURE.getValue(), "wrong credentials");

        }

        return ResponseEntity.ok(stringResponse);
    }

    @GetMapping(path = "/isloggedin", produces = "application/json")
    public ResponseEntity<StatusResponse> getStatus( HttpServletRequest request) {

        currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");

        StatusResponse statusResponse = new StatusResponse();
        if (currentUser != null && currentUser.getAuthorize() != null &&
                currentUser.getAuthorize()) {
            statusResponse.setStatus(true);
        } else {
            statusResponse.setStatus(false);
        }
        return ResponseEntity.ok(statusResponse);
    }

    @GetMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<SuccessResponse> logout( HttpServletRequest request) {
        request.getSession().invalidate();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setSuccess(true);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StatusResponse> addMember(@RequestBody User newUser) {

        if (newUser == null || StringUtils.isEmpty(newUser.getPassword())
                || StringUtils.isEmpty(newUser.getUsername())) {
            return ResponseEntity.ok(new StatusResponse(false, "Missing credentials"));
        }

        User user = userService.findByUsername(newUser.getUsername());

        if (user != null && newUser.getUsername().equals(user.getUsername())) {
            return ResponseEntity.ok(new StatusResponse(false, "Username already exist"));
        }


        newUser.setAdmin(false);
        newUser.setQuote("");

        userService.save(newUser);

        return ResponseEntity.ok(new StatusResponse(true, "successful login"));
    }


}
