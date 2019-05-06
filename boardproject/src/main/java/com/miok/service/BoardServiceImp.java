package com.miok.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.miok.common.FileVO;
import com.miok.common.PageVO;
import com.miok.common.SearchVO;
import com.miok.dao.BoardDAO;
import com.miok.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Override
	public List<BoardVO> selectBoardList(SearchVO searchVO) {
		return boardDAO.selectBoardList(searchVO);
	}

	@Override
	public void insertBoard(BoardVO boardVO, List<FileVO> filelist, String[] fileno) {
		
		/* 트랜젝션적용
		 * 첨부파일오류시 게시물 저장도 적용 안되도록 rollback
		 */
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		try {
			if(boardVO.getBrdno() == null || "".equals(boardVO.getBrdno())){
				boardDAO.insertBoard(boardVO);
			}else {
				boardDAO.updateBoard(boardVO);
			}
			
			if(fileno != null) {
				HashMap delFile = new HashMap();
				delFile.put("fileno", fileno);
				boardDAO.deleteBoardFile(delFile);
			}
			
			for(FileVO fileVO : filelist) {
				fileVO.setParentPK(boardVO.getBrdno());
				boardDAO.insertBoardFile(fileVO);
			}
			txManager.commit(status);
		}catch (Exception e) {
			txManager.rollback(status);
			throw e;
		}

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
	public int selectBoardCount(SearchVO searchVO) {
		return boardDAO.selectBoardCount(searchVO);
	}

	@Override
	public void updateBoardHit(int brdno) {
		boardDAO.updateBoardHit(brdno);
	}
	
	@Override
	public List<FileVO> selectBoardFileList(String brdno) {
		return boardDAO.selectBoardFileList(brdno);
	}
}
