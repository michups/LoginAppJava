package com.michups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus implements Serializable {

    private Boolean authorize;
    private Boolean admin;

}
