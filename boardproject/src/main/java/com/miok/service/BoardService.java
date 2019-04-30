package com.miok.service;

import java.util.List;

import com.miok.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoardList();
	public void insertBoard(BoardVO boardVO);
	public void updateBoard(BoardVO boardVO);
	public BoardVO selectBoardOne(int brdno);
	public void deleteBoardOne(int brdno);
}
