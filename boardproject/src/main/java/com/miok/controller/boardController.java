package com.miok.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miok.common.PageVO;
import com.miok.service.BoardService;
import com.miok.vo.BoardVO;


@Controller
public class boardController {

	@Autowired
	private BoardService boardService;

	// 리스트
	@RequestMapping(value = "/boardList")
	public String boardList(PageVO pageVO, Model model) {
		pageVO.pageCalculate(boardService.selectBoardCount());
		
		List<BoardVO> boardList = boardService.selectBoardList(pageVO);

		model.addAttribute("boardList", boardList);
		return "/boardList";
	}

	// 글쓰기
	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "/boardForm";
	}
	
	// 글쓰기 저장
	@RequestMapping(value = "/boardSave")
   	public String boardSave(@ModelAttribute BoardVO boardInfo) throws Exception {
    	
    	boardService.insertBoard(boardInfo);
    	
        return "redirect:/boardList";
    }
	
	// 글수정
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(HttpServletRequest request, Model model) {
		int brdno = Integer.parseInt(request.getParameter("brdno"));
		BoardVO boardInfo = boardService.selectBoardOne(brdno);
		model.addAttribute("boardInfo", boardInfo);
		return "boardUpdate";
	}

	// 글 수정 저장
	@RequestMapping(value = "/boardUpdateSave")
	public String board1UpdateSave(@ModelAttribute BoardVO boardInfo) {
		boardService.updateBoard(boardInfo);
		return "redirect:/boardList";
	}

	// 글읽기
	@RequestMapping(value = "/boardView")
	public String boardView(HttpServletRequest request, Model model) {
		int brdno = Integer.parseInt(request.getParameter("brdno"));

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
