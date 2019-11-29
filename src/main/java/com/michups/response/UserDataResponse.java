package com.michups.response;

import com.michups.model.CurrentUser;
import com.michups.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {

    private String username;

    private Boolean status;

    private String quote ;

    public UserDataResponse(CurrentUser user) {
        this.username = user.getUsername();
        this.status = user.getAuthorize();
        this.quote = user.getQuote();
    }
}
