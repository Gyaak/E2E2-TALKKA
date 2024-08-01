package com.talkka.server.auth.service;

import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.talkka.server.user.dao.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service extends DefaultOAuth2UserService {
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> tmpMap = oAuth2User.getAttribute("response");
		assert tmpMap != null;
		Set<String> keySet = tmpMap.keySet();
		for (String key : keySet) {
			System.out.println(key + " : " + tmpMap.get(key));
		}
		System.out.println("This is OAuth2Service ");
		return null;
	}
}
