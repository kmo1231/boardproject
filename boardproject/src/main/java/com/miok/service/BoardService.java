package com.miok.service;

import java.util.List;

import com.miok.common.FileVO;
import com.miok.common.SearchVO;
import com.miok.vo.BoardReplyVO;
import com.miok.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoardList(SearchVO searchVO);
	public void insertBoard(BoardVO boardVO, List<FileVO> filelist, String[] fileno);
	public BoardVO selectBoardOne(int brdno);
	public void deleteBoardOne(int brdno);
	public int selectBoardCount(SearchVO searchVO);
	public void updateBoardHit(int brdno);
	public List<FileVO> selectBoardFileList(String brdno);
	
	public List<BoardReplyVO> selectBoardReplyList(String brdno);
	public void insertBoardReply(BoardReplyVO replyInfo);
	public boolean deleteBoardReply(String reno);
}
