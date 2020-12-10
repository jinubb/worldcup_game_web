package com.wc.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wc.common.ResponseContainer;
import com.wc.login.entity.User;
import com.wc.login.model.LoginResponse;
import com.wc.login.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wc/login")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService service;
	
	@ApiOperation(value = "로그인")
	@PostMapping("/")
	public ResponseContainer<LoginResponse> loginUser(
			@RequestParam(required = true) String email, 
			@RequestParam(required = true) String password) {
		ResponseContainer<LoginResponse> response = ResponseContainer.emptyResponse();		
		try {
			response.setPayload(service.loginUser(email, password));
		} catch(Exception e) {
			logger.error("login:\n{}",e);
			response.setError(e);
		}
		return response;
	}
	
	@ApiOperation(value = "회원가입")
	@PostMapping("/reg")
	public ResponseContainer<User> registerUser(
			@RequestParam(required = true) String email, 
			@RequestParam(required = true) String password,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false, defaultValue = "false") boolean isAdmin) {
		ResponseContainer<User> response = ResponseContainer.emptyResponse();		
		try {
			response.setPayload(service.registerUser(email, password, name, phone, isAdmin));
		} catch(Exception e) {
			logger.error("register:\n{}",e);
			response.setError(e);
		}
		return response;
	}
}
