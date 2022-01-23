package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		System.out.println("[GuestbookDao.getList()]");
		
		return sqlSession.selectList("guestbook.getList");
	}
	
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestbookInsert()");
		
		int count = sqlSession.insert("guestbook.guestbookInsert", guestbookVo);
		
		System.out.println("["+count+"건이 생성되었습니다]");
		
		return count;
	}

	public int guestbookDelete(int no, String password) {
		System.out.println("[GuestbookDao.guestbookDelete()");
		
		Map<String, Object> guestMap = new HashMap<String, Object>();
		
		guestMap.put("no", no);
		guestMap.put("password", password);

		int count = sqlSession.delete("guestbook.guestbookDelete", guestMap);
		return count;
	}
}
