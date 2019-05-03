package com.miok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miok.common.PageVO;
import com.miok.dao.BoardDAO;
import com.miok.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> selectBoardList(PageVO pageVO) {
		return boardDAO.selectBoardList(pageVO);
	}

	@Override
	public void insertBoard(BoardVO boardVO) {
		boardDAO.insertBoard(boardVO);
	}

	@Override
	public void updateBoard(BoardVO boardVO) {
		boardDAO.updateBoard(boardVO);
	}

	@Override
	public BoardVO selectBoardOne(int brdno) {
		return boardDAO.selectBoardOne(brdno);
	}

	@Override
	public void deleteBoardOne(int brdno) {
		boardDAO.deleteBoardOne(brdno);
	}

	@Override
	public int selectBoardCount() {
		return boardDAO.selectBoardCount();
	}
	
	
}
