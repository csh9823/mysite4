package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//리스트(리스트만 출력할때)
	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getList();
	}
	
	
	//리스트(리스트+페이징)
	public Map<String, Object> getList2(int crtPage){
		
		System.out.println("boardService/getList2");
		/////////////////////////////////////////////
		//리스트 가져오기
		//////////////////////////////////////////////
		
		//페이지당 글개수
		int ListCnt = 10;
		
		//현재 페이지 처리 파라미터가 없을경우
		crtPage = (crtPage>0) ? crtPage : (crtPage=1); //if문 줄여쓴거 밑에거랑 동일
		
		/*
		if(crtPage <=0) {
			crtPage =1;
		}
		*/
		//시작글 번호     1-->1       6-->51
		int startRnum =(crtPage-1)*ListCnt + 1;
		
		//끝글 번호
		int endRnum = (startRnum + ListCnt) -1;
		
		List<BoardVo> boardList = boardDao.getList2(startRnum,endRnum);
		
		///////////////////////////////////////
		//페이징 버튼
		///////////////////////////////////////
		
		//전체 글 갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		
		System.out.println("totalCnt = " + totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호**
		//1  1~5   0.2(올림) =1
		//2  1~5   0.4 =1
		//3  1~5   0.6 =1
		//5  1~5   1
		//6  6~10  1.2 = 2
		//10 6~10  
		//11 11~15
		int endPageBtnNo = (int)( Math.ceil(crtPage/(double)pageBtnCount ) )*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNO = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo * ListCnt < totalCnt) {
			next = true;
		}else {//다음 화살표가 안보이면 마지막 버튼 값을 다시 계산한다.
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)ListCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNO !=1) {
			prev = true;
		}
		
		//////////////////////////////////////
		//페이징 버튼
		//////////////////////////////////////
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNO", startPageBtnNO);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		//System.out.println("--------------------------------");
		//System.out.println(pMap);
		//System.out.println("--------------------------------");
		return pMap;
	}
	
	
	public BoardVo getBoard(int num) {
		System.out.println("[BoardService.getBoard()]");
		boardDao.hitPlus(num);
		return boardDao.getBoard(num);
	}
	
	//글쓰기
	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		
		//페이징 데이터 추가 123개
		/*
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째 글 제목입니다.");
			boardVo.setContent(i + "번째 글 내용 입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			
		}
		*/
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
