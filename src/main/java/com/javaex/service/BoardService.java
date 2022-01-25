package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getList();
	}

	public BoardVo getBoard(int num) {
		System.out.println("[BoardService.getBoard()]");
		boardDao.hitPlus(num);
		return boardDao.getBoard(num);
	}

	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		boardDao.boardInsert(boardVo);
	}

	public void boardDelete(int num) {
		System.out.println("[BoardService.boardDelete()]");
		boardDao.boardDelete(num);
	}
	
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardService.boardUpdate()]");
		boardDao.boardUpdate(boardVo);
	}
}
