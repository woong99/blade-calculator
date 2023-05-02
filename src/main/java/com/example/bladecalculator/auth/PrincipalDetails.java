package com.example.bladecalculator.auth;

import com.example.bladecalculator.auth.oauth.OAuth2UserInfo;
import com.example.bladecalculator.entity.User;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {

    @Serial
    private static final long serialVersionUID = -5631399292075098869L;

    private final User user;
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public String getName() {
        return oAuth2UserInfo.getProviderId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> user.getAuthority().getAuthority());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }
}
