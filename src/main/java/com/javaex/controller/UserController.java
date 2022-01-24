package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaec.service.UserService;
import com.javaex.vo.UserVo;

@Controller //컨트롤러가 다오를 직접쓰면 안됨
public class UserController {
	
	@Autowired //다오를 쓰지 않고 서비스에 넘기고 서비스에서 다오를 실행해야됨
	private UserService Userservice;

	//로그인폼
	@RequestMapping(value="/user/loginForm", method={RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/user/login", method={RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		
		
		System.out.println(userVo);
		
		UserVo authUser = Userservice.login(userVo);
		
		System.out.println(authUser);
		if(authUser != null) {
			//세션에 저장
			System.out.println("세션 성공");
			session.setAttribute("authUser", authUser); //  ("authUser" = jstl 꺼내쓸때 쓰는 이름 , authUser)세션에 값 넣어주기
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/user/longinForm?result=fail";
		}
		 
		
	}
	
	@RequestMapping(value="/user/logout", method={RequestMethod.GET, RequestMethod.POST})
	public String loginout(HttpSession session) {
		
		System.out.println("로그아웃");
		session.removeAttribute("authUser"); //같이써줘야됨
		session.invalidate(); //같이 써줘야됨 
		
		return "redirect:/";
		
	}
	
}
