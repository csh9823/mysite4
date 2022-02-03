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
		
		
		System.out.println("[GuestbookDao.guestbookInsert()]");
		System.out.println(guestbookVo);
		
		int count = sqlSession.insert("guestbook.guestbookInsert", guestbookVo);
		
		System.out.println("["+count+"건이 생성되었습니다]");
		
		return count;
	}
	
	
	//방명록 글 삭제
	public int delete(GuestbookVo guestbookVo){
		System.out.println("guestbookDao/delete");
			
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	
	public int guestbookDelete(int no, String password) {
		System.out.println("[GuestbookDao.guestbookDelete()");
		
		Map<String, Object> guestMap = new HashMap<String, Object>();
		
		guestMap.put("no", no);
		guestMap.put("password", password);

		int count = sqlSession.delete("guestbook.guestbookDelete", guestMap);
		return count;
	}
	
	
	//방명록 글 저장(selectKey)
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("dao/insertSelecKey");
		System.out.println(guestbookVo);
		sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
		return sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
		
	}
	
	//방명록 글1개 가져오기
	   public GuestbookVo selectGuest(int no) {
	      System.out.println("guestbookDao/selectGuest");
	      
	      return sqlSession.selectOne("guestbook.selectByNo", no);
	   }
}
