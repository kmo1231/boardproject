package com.miok.service;

import java.util.List;

import com.miok.common.PageVO;
import com.miok.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoardList(PageVO pageVO);
	public void insertBoard(BoardVO boardVO);
	public void updateBoard(BoardVO boardVO);
	public BoardVO selectBoardOne(int brdno);
	public void deleteBoardOne(int brdno);
	public int selectBoardCount();
	public void updateBoardHit(int brdno);
}
