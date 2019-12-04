package com.michups.controller;

import com.michups.model.User;
import com.michups.model.CurrentUser;
import com.michups.response.*;
import com.michups.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@Controller
@RequestMapping("/data")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserDataController {

    private CurrentUser currentUser;

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/user", produces = "application/json")
    public ResponseEntity<UserDataResponse> getData(HttpServletRequest request) {

        currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        UserDataResponse userDataResponse;

        if (currentUser!=null && currentUser.getAuthorize() != null && currentUser.getAuthorize()) {
            System.out.println(currentUser);
            userDataResponse = new UserDataResponse(currentUser);
        } else {
            userDataResponse = new UserDataResponse();
        }
        return ResponseEntity.ok(userDataResponse);
    }

    @PostMapping(path = "/quote", produces = "application/json")
    public ResponseEntity<SuccessResponse> setQuote(@RequestBody Map<String, String> quote,
                                                    HttpServletRequest request) {

        String quoteStr = quote.get("value");

        System.out.println(quoteStr);

        currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");

        User userForUpdate = userService.findByUsername(currentUser.getUsername());

        userForUpdate.setQuote(quoteStr);
        userForUpdate = userService.save(userForUpdate);

        currentUser.setQuote(userForUpdate.getQuote());

        return ResponseEntity.ok(new SuccessResponse(true));
    }

    @GetMapping(path = "/datasecret", produces = "application/json")
    public ResponseEntity<DataResponse> getSecretData(HttpServletRequest request) {

        currentUser = (CurrentUser) request.getSession().getAttribute("currentUser");
        DataResponse dataResponse;

        if (currentUser!=null && currentUser.getAdmin() != null && currentUser.getAuthorize() != null &&
                currentUser.getAdmin() && currentUser.getAuthorize()) {
            dataResponse = new DataResponse("succes", "Top secret mesage only for admin");
        } else {
            dataResponse = new DataResponse("failure", "You are not admin!");
        }
        return ResponseEntity.ok(dataResponse);
    }


}
