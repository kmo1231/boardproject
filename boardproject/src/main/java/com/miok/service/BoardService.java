package com.miok.service;

import java.util.List;

import com.miok.vo.BoardReplyVO;
import com.miok.vo.BoardVO;
import com.miok.vo.FileVO;
import com.miok.vo.SearchVO;

public interface BoardService {
	public List<BoardVO> selectBoardList(SearchVO searchVO);
	public void insertBoard(BoardVO boardVO, List<FileVO> filelist, String[] fileno);
	public BoardVO selectBoardOne(String brdno);
	public void deleteBoardOne(String brdno);
	public int selectBoardCount(SearchVO searchVO);
	public void updateBoardHit(String brdno);
	public List<FileVO> selectBoardFileList(String brdno);
	
	public List<BoardReplyVO> selectBoardReplyList(String brdno);
	public void insertBoardReply(BoardReplyVO replyInfo);
	public boolean deleteBoardReply(String reno);
}
