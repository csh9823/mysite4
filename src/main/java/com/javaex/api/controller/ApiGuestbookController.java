package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println(" ApiGuestbookControlle.addList()");
		return "aguest/addList";
	}
	
	@ResponseBody //바디에 붙여라
	@RequestMapping("/List")
	public List<GuestbookVo> List() { //리스트로 보내줘야됨
		
		System.out.println("ApiGuestbookControlle.List()");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("ApiGuestbookControlle.write()");
		
		//저장하고 저장된 값 리턴
		GuestbookVo gVo =  guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		
		System.out.println("ApiGuestbookControlle.write2()");
		System.out.println(guestbookVo);
		//저장하고 저장된 값 리턴
		GuestbookVo gVo =  guestbookService.addGuestResultVo(guestbookVo);
		System.out.println("저장값 리턴 보기");
		System.out.println(gVo);
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookControlle.remove()");
		
		System.out.println("ApiGuestbookControlle.guestbookVo");
		System.out.println(guestbookVo);
		
		String result = guestbookService.remove(guestbookVo);
		//성공 "success" 실패 "fail"
		System.out.println(result);
		return result;
	}
}
