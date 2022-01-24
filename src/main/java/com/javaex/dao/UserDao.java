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
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		System.out.println(userVo);
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		
		
		return authUser;
	}

}
