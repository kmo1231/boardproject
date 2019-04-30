package com.miok.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.miok.vo.BoardVO;

@Repository
public interface BoardDAO {
	public List<BoardVO> selectBoardList();
	public void insertBoard(BoardVO boardVO);
	public void updateBoard(BoardVO boardVO);
	public BoardVO selectBoardOne(int brdno);
	public void deleteBoardOne(int brdno);
}
