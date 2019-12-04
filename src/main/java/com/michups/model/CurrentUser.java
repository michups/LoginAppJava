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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String quote;

    private Boolean authorize;
    private Boolean admin;

}
