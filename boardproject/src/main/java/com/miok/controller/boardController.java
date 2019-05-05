package com.miok.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miok.common.PageVO;
import com.miok.common.SearchVO;
import com.miok.service.BoardService;
import com.miok.vo.BoardVO;

/*
	ver.1
	- List: 모든 게시물 출력
	- Form: 사용자 입력 내용 저장
	- Update: 사용자 입력 내용 수정
	- Read:   사용자 입력 내용 보기
	- Delete: 지정된 게시물 삭제
	
	ver.2
	- List: 페이징, 새로운 번호 부여
	- Form: 입력/수정을 하나로
	- Read: 조회수
	- Delete: 삭제에서 숨기기로
	
	ver.3
	- List: 검색, 제목 한 줄 표시 >> 페이징 공통
	- Form: 필수입력, 수정/저장 서비스 합침
	- Read: 스크립트 실행 방지
*/

@Controller
public class boardController {

	@Autowired
	private BoardService boardService;

	// 리스트
	@RequestMapping(value = "/boardList")
	public String boardList(SearchVO searchVO, Model model) {
		searchVO.pageCalculate(boardService.selectBoardCount(searchVO));
		
		List<BoardVO> boardList = boardService.selectBoardList(searchVO);

		model.addAttribute("boardList", boardList);
		model.addAttribute("searchVO", searchVO);
		return "/boardList";
	}

	// 글쓰기
	@RequestMapping(value = "/boardForm")
	public String boardForm(HttpServletRequest request, Model model) {
		String brdno = request.getParameter("brdno");
		if(brdno != null) {
			BoardVO boardInfo = boardService.selectBoardOne(Integer.parseInt(brdno));
			model.addAttribute("boardInfo", boardInfo);
		}
		
		return "/boardForm";
	}
	
	// 글쓰기 저장
	@RequestMapping(value = "/boardSave")
   	public String boardSave(BoardVO boardInfo) throws Exception{
   		boardService.insertBoard(boardInfo);

   		return "redirect:/boardList";
    }
	
	// 글읽기
	@RequestMapping(value = "/boardView")
	public String boardView(HttpServletRequest request, Model model) {
		int brdno = Integer.parseInt(request.getParameter("brdno"));
		
		boardService.updateBoardHit(brdno);
		BoardVO boardInfo = boardService.selectBoardOne(brdno);

		model.addAttribute("boardInfo", boardInfo);

		return "/boardView";
	}

	// 글삭제
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(HttpServletRequest request) {
		int brdno = Integer.parseInt(request.getParameter("brdno"));

		boardService.deleteBoardOne(brdno);

		return "redirect:/boardList";
	}
}
