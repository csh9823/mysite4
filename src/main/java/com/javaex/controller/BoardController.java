package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.vo.GuestbookVo;

@Controller
public class BoardController {
	
	@Autowired //다오를 쓰지 않고 서비스에 넘기고 서비스에서 다오를 실행해야됨
	private BoardController boardController;
	
	@RequestMapping(value="/board/addlist", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("BoardController > list");
		
		List<BoardVo> BoardList = boardController.getList();
		
		model.addAttribute("boardList", boardList);
		
		return "";
	}
}
