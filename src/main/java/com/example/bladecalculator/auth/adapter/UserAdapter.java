package com.example.bladecalculator.auth.adapter;

import com.example.bladecalculator.auth.PrincipalDetails;
import com.example.bladecalculator.auth.oauth.OAuth2UserInfo;
import com.example.bladecalculator.entity.User;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAdapter extends PrincipalDetails {

    @Serial
    private static final long serialVersionUID = 2788309450942900737L;
    private User user;
    private OAuth2UserInfo oAuth2UserInfo;

    public UserAdapter(User user) {
        super(user);
        this.user = user;
    }

    public UserAdapter(User user, OAuth2UserInfo oAuth2UserInfo) {
        super(user, oAuth2UserInfo);
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }
}
