package com.talkka.server.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/hello")
	public String hello() {
		System.out.println("hello index!!");
		return "hello";
	}

	// @GetMapping("/login/naver")
	// public String loginWithNaver(@RequestParam String code) {
	// 	System.out.println("code = " + code);
	// 	return "success";
	// }
	//
	// @GetMapping("/loginSuccess")
	// public String loginSuccess(OAuth2AuthenticationToken authentication) {
	// 	// Process the authenticated user's details here
	// 	return "loginSuccess";
	// }
}
