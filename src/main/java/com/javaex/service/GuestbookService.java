package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookdao;

	public List<GuestbookVo> getList() {
		System.out.println("GuestbookService.getList()");
		return guestbookdao.getList();
	}

	public void guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("guestbookService.guestbookInsert()");
		System.out.println(guestbookVo);
		guestbookdao.guestbookInsert(guestbookVo);
	}

	public void guestbookDelete(int no, String password) {
		System.out.println("guestbookService.guestbookInsert()");
		guestbookdao.guestbookDelete(no, password);
	}
	
	
	//방명록 글 저장 --> 저장 글 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		
		System.out.println("guestbookService/addGuestResultVo");
		//저장하기
		int count =guestbookdao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		//GuestbookVo gvo = guestbookdao.selectGuest(no);
		return guestbookdao.selectGuest(no);
	}
	
	//ajax 방명록 글 삭제
	public String remove(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/ajax remove()");
		
		int count = guestbookdao.delete(guestbookVo);
		
		if(count>0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
