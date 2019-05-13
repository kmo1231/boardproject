package com.miok.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miok.common.FileUtil;
import com.miok.service.BoardGroupSvc;
import com.miok.service.BoardService;
import com.miok.vo.BoardGroupVO;
import com.miok.vo.BoardReplyVO;
import com.miok.vo.BoardVO;
import com.miok.vo.FileVO;
import com.miok.vo.PageVO;
import com.miok.vo.SearchVO;

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
	
	ver.4
	- 파일 첨부 기능 추가
	
	ver.5
	- 댓글 기능 추가
	
	ver.6
	- 무한댓글 기능 추가
	
	ver.7
	- jquery 일부적용 및 댓글추가, 삭제부분 ajax로 구현
	
	ver.8
	- dynatree 이용한 멀티게시판
*/

@Controller
public class boardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardGroupSvc boardGroupSvc;

	// 리스트
	@RequestMapping(value = "/boardList")
	public String boardList(SearchVO searchVO, Model model) {
		
		BoardGroupVO bgInfo = boardGroupSvc.selectBoardGroupOneUsed(searchVO.getBgno());
		if(bgInfo == null) {
			return "/boardgroup/BoardGroupFail";
		}
		
		if(searchVO.getBgno() == null) {
			searchVO.setBgno("1");
		}
		
		searchVO.pageCalculate(boardService.selectBoardCount(searchVO));
		
		List<BoardVO> boardList = boardService.selectBoardList(searchVO);

		model.addAttribute("boardList", boardList);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("bgInfo", bgInfo);
		
		return "/board/boardList";
	}

	// 글쓰기
	@RequestMapping(value = "/boardForm")
	public String boardForm(HttpServletRequest request, Model model) {
		String bgno = request.getParameter("bgno");
		String brdno = request.getParameter("brdno");
		
		//수정일 경우
		if(brdno != null) {
			BoardVO boardInfo = boardService.selectBoardOne(brdno);
			List<FileVO> filelist = boardService.selectBoardFileList(brdno);
			bgno = boardInfo.getBgno();
			
			model.addAttribute("boardInfo", boardInfo);
			model.addAttribute("filelist", filelist);
		}
		
		BoardGroupVO bgInfo = boardGroupSvc.selectBoardGroupOneUsed(bgno);
		if(bgInfo == null) {
			return "/boardgroup/BoardGroupFail";
		}
		
		model.addAttribute("bgno", bgno);
		model.addAttribute("bgInfo", bgInfo);
		
		return "/board/boardForm";
	}
	
	// 글쓰기 저장
	@RequestMapping(value = "/boardSave")
   	public String boardSave(HttpServletRequest request, BoardVO boardInfo) {
   		String[] fileno = request.getParameterValues("fileno");
   		
   		FileUtil fs = new FileUtil();
   		List<FileVO> filelist = fs.saveAllFiles(boardInfo.getUploadfile());
		
		boardService.insertBoard(boardInfo, filelist, fileno);

   		return "redirect:/boardList?bgno="+boardInfo.getBgno();
    }
	
	// 글읽기
	@RequestMapping(value = "/boardView")
	public String boardView(HttpServletRequest request, Model model) {
		String brdno = request.getParameter("brdno");
		
		boardService.updateBoardHit(brdno);
		BoardVO boardInfo = boardService.selectBoardOne(brdno);
		List<FileVO> filelist = boardService.selectBoardFileList(brdno);
		
		List<BoardReplyVO> replylist = boardService.selectBoardReplyList(brdno);
		
		BoardGroupVO bgInfo = boardGroupSvc.selectBoardGroupOneUsed(boardInfo.getBgno());
		if(bgInfo == null) {
			return "/boardgroup/BoardGroupFail";
		}
		
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("filelist", filelist);
		model.addAttribute("replylist", replylist);
		model.addAttribute("bgInfo", bgInfo);
		
		return "/board/boardView";
	}

	// 글삭제
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(HttpServletRequest request) {
		String brdno = request.getParameter("brdno");
		String bgno = request.getParameter("bgno");
		
		boardService.deleteBoardOne(brdno);

		return "redirect:/boardList?bgno="+bgno;
	}
	
	// 댓글저장
	@RequestMapping(value = "/boardReplySave")
	public String boardReplySave(HttpServletRequest request, BoardReplyVO replyInfo) {
		boardService.insertBoardReply(replyInfo);
		
		return "redirect:/boardView?brdno="+replyInfo.getBrdno();
	}
	
	// 댓글저장(ajax_서버에서 반환될 때 JSP를 이용하여 정리된 값을 만들어넘김)
	@RequestMapping(value = "/boardReplySaveAjaxJSP")
    public String board7ReplySaveAjaxJSP(BoardReplyVO ReplyInfo, Model model) {
        
        boardService.insertBoardReply(ReplyInfo);

        model.addAttribute("replyInfo", ReplyInfo);
        
        return "BoardViewAjaxReply";
    }
	
	// 댓글삭제
	@RequestMapping(value = "/boardReplyDelete")
	public String boardReplyDelete(BoardReplyVO replyInfo) {
		if(!boardService.deleteBoardReply(replyInfo.getReno())) {
			return "BoardFailure";
		}
		
		return "redirect:/boardView?brdno="+replyInfo.getBrdno();
	}
	
	// 댓글삭제(ajax)
	@RequestMapping(value="/boardReplyDeleteAjax")
	public void boardReplyDeleteAjax(HttpServletResponse response, BoardReplyVO replyInfo) {
		ObjectMapper mapper=new ObjectMapper();
		response.setContentType("application/json:charset=UTF-8");
		
		try {
			if(!boardService.deleteBoardReply(replyInfo.getReno())) {
				response.getWriter().print(mapper.writeValueAsString("Fail"));
			} else {
				response.getWriter().print(mapper.writeValueAsString("OK"));
			}
		} catch (IOException e) {
			System.out.println("오류: 댓글 삭제에 문제가 발생했습니다.");
		}
	}
}
