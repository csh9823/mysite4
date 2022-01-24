package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;

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
		
		UserVo authUser = userDao.selectUser(userVo);
		
		System.out.println(authUser);
		if(authUser != null) {
			//세션에 저장
			System.out.println("세션 성공");
			session.setAttribute("authUser", authUser); //  ("authUser" = jstl 꺼내쓸때 쓰는 이름 , authUser)세션에 값 넣어주기
			return "redirect:/";
		}else {
			return "redirect:/";
		}
		 
		
	}
	
	
}
