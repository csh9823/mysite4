package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//유저정보가져오기(로그인시 사용)
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserDao.getUser()]");
		
		UserVo authUser = sqlSession.selectOne("user.getUser", userVo);
		return authUser;
	}

}
