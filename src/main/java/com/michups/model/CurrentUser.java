package com.michups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component
//@Scope(value=WebApplicationContext.SCOPE_SESSION)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Component(value="registerBean")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class CurrentUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String quote;

    private Boolean authorize;
    private Boolean admin;

//
//
//    public void setUserStatusForUser(Boolean isAdmin, Boolean isAuthorize) {
//        setAdmin(isAdmin);
//        setAuthorize(isAuthorize);
//    }
//    private void setUserData(String username, String quote) {
//        setUsername(username);
//        setQuote(quote);
//    }
}
