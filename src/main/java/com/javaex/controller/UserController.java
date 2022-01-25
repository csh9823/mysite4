package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller //컨트롤러가 다오를 직접쓰면 안됨
public class UserController {
	
	@Autowired //다오를 쓰지 않고 서비스에 넘기고 서비스에서 다오를 실행해야됨
	private UserService userService;

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
		
		UserVo authUser = userService.login(userVo);
		
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
	
	//로그아웃
	@RequestMapping(value="/user/logout", method={RequestMethod.GET, RequestMethod.POST})
	public String loginout(HttpSession session) {
		
		System.out.println("로그아웃");
		session.removeAttribute("authUser"); //같이써줘야됨
		session.invalidate(); //같이 써줘야됨 
		
		return "redirect:/";
		
	}
	
	//수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("[UserComtroller.modifyForm()]");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "/user/modifyForm";
	}
	
	//수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.modify()]");
		userService.modify(userVo);
		return "redirect:/";
	}
	
	//회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserComtroller.joinForm()]");

		return "/user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.join()]");
		userService.join(userVo);
		return "joinOk";
	}
	
	
}
