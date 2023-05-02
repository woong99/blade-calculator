package com.example.bladecalculator.auth.oauth;

import com.example.bladecalculator.auth.adapter.UserAdapter;
import com.example.bladecalculator.entity.Authority;
import com.example.bladecalculator.entity.User;
import com.example.bladecalculator.repository.user.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

//        if (provider.equals("naver")) {
//            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
//        }
        oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;

        User user = userRepository.findByUserId(username);
        if (user == null) {
            user = User.builder()
                    .userId(username)
                    .authority(Authority.USER)
                    .lastLoginAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
        } else {
            userRepository.updateLastLoginAt(user.getUserId());
        }
        return new UserAdapter(user, oAuth2UserInfo);
    }
}
