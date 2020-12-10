package com.wc.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wc.common.UserSession;
import com.wc.login.entity.User;
import com.wc.login.model.LoginResponse;
import com.wc.login.repository.UserRepository;
import com.wc.login.util.UserType;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepo;
	
	private static final int tokenExpiredTime = 1800; // 토큰 유효시간 1800sec
	
	private String createToken(UserSession newUserSession, int expiredTime) {
		HttpSession newSession = createSession(); //새로운 세션 생성
		newSession.setAttribute("loginInfo", newUserSession);
		newSession.setMaxInactiveInterval(expiredTime);
		return newSession.getId();
	}
	
	private UserSession createUserSession(User loginUser) {
		UserSession newUserSession = new UserSession();
		newUserSession.setUserType(loginUser.getType());
		newUserSession.setId(loginUser.getId());
		newUserSession.setName(loginUser.getName());
		newUserSession.setEmail(loginUser.getEmail());
		newUserSession.setPhone(loginUser.getPhone());
		return newUserSession;
	}
	
	private HttpSession createSession() {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    HttpSession newSession = servletRequestAttribute.getRequest().getSession(true);
	    return newSession;
	}
	
	//로그인
	public LoginResponse loginUser(String email, String password) {
		LoginResponse res = new LoginResponse();
		User loginUser = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("등록되지 않은 Email입니다."));
		res.setAuthToken(createToken(createUserSession(loginUser), tokenExpiredTime));
		res.setLoginUser(loginUser);
		return res;
	}

	//회원가입
	public User registerUser(String email, String password, String name, String phone, boolean isAdmin) {
		if(userRepo.findByEmail(email).isPresent()) {
			throw new RuntimeException("이미 등록된 이메일입니다.");
		}
		
		String userType = isAdmin ? UserType.ADMIN.getCode() : UserType.USER.getCode();
		User newUser = toUser(email, password, name, phone, userType);
		return userRepo.save(newUser);
	}

	private User toUser(String email, String password, String name, String phone, String userType) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setPhone(phone);
		user.setType(userType);
		return user;
	}
	
	

}
