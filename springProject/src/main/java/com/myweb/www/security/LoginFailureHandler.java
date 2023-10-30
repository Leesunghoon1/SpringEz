package com.myweb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Getter
@Setter
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private String authEmail;
	
	private String errorMassage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		setAuthEmail(request.getParameter("email"));
		//실패한 이메일을 담음
		
		//exception 발생시 메시지 저장
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			//비밀번호가 안맞거나, 아이디가 안맞거나
			setErrorMassage(exception.getMessage().toString());
		}
		log.info(">>>>> errMsg >>> + " + errorMassage);
		request.setAttribute("email", getAuthEmail());
		request.setAttribute("errMsg", getErrorMassage());
		request.getRequestDispatcher("/member/login?error").forward(request, response);
	}
	

}
