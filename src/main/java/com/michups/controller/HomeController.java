package com.michups.controller;

import com.michups.model.LoginCredencials;
import com.michups.model.User;
import com.michups.model.UserStatus;
import com.michups.response.*;
import com.michups.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

@Controller("/")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class HomeController {

    @GetMapping("/asd")
    public String home() {
        return "home";
    }

    @Autowired
    private UserStatus userStatus;

    @Autowired
    private IUserService userService;

    @CrossOrigin
    @PostMapping(path = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StringResponse> authorizeUser(@RequestBody LoginCredencials loginCredencials) {

        System.out.println(loginCredencials.toString());

        User user = userService.findByUsername(loginCredencials.getUsername());

        StringResponse stringResponse;

        if (user.getPassword().equals(loginCredencials.getPassword())) {
            setUserStatusForUser(user.getAdmin(), true);
            stringResponse = new StringResponse(Response.SUCCESS.getValue(), "account logged in");
        } else {
            stringResponse = new StringResponse(Response.FAILURE.getValue(), "wrong credentials");
        }

        return ResponseEntity.ok(stringResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/data", produces = "application/json")
    public ResponseEntity<DataResponse> getData() {

        DataResponse dataResponse;

        if (userStatus.getAdmin() != null && userStatus.getAuthorize() != null &&
                userStatus.getAdmin() && userStatus.getAuthorize()) {
            dataResponse = new DataResponse("succes", "Top secret mesage only for admin");
        } else {
            dataResponse = new DataResponse("failure", "You are not admin!");
        }
        return ResponseEntity.ok(dataResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/isloggedin", produces = "application/json")
    public ResponseEntity<StatusResponse> getStatus() {

        StatusResponse statusResponse = new StatusResponse();
        if (userStatus.getAuthorize() != null &&
                userStatus.getAuthorize()) {
            statusResponse.setStatus(true);
        } else {
            statusResponse.setStatus(false);
        }
        return ResponseEntity.ok(statusResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<SuccessResponse> logout() {
        userStatus = new UserStatus();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setSuccess(true);
        return ResponseEntity.ok(successResponse);
    }

    @CrossOrigin
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

        userService.createUser(newUser);

        return ResponseEntity.ok(new StatusResponse(true, "successful login"));
    }

    private void setUserStatusForUser(Boolean isAdmin, Boolean isAuthorize) {
        userStatus.setAdmin(isAdmin);
        userStatus.setAuthorize(isAuthorize);
    }

}
