package com.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardCommentDTO;
import com.board.dto.BoardDTO;
import com.board.dto.BoardMemberDTO;
import com.board.dto.FileDTO;
import com.board.service.BoardService;
import com.board.service.MemberService;
import com.board.vo.PaggingVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private BoardService boardService;
	private MemberService memberService;

	public MainController(BoardService boardService, MemberService memberService) {
		this.boardService = boardService;
		this.memberService = memberService;
	}
	
	@GetMapping(value = {"/", "/main"})
	public ModelAndView main(ModelAndView view,
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "20") int pageContentEa) {
		//해당 페이지 게시글 목록 읽어옴
		List<BoardDTO> boardList = 
				boardService.selectBoardNewList(pageNo,pageContentEa);
		//페이징 정보도 읽어옴
		//	전체 페이지 개수 읽어옴
		int totalCount = boardService.selectBoardTotalCount();
		PaggingVO vo = new PaggingVO(totalCount, pageNo, pageContentEa);
		
		//request 영역에 저장
		view.addObject("list", boardList);
		view.addObject("pagging", vo);
		
		view.setViewName("main");
		return view;
	}
	
	@GetMapping("/login/view")
	public String loginView() {
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView login(ModelAndView view, HttpSession session,
			String id, String passwd, HttpServletResponse response) throws IOException {
		
		BoardMemberDTO dto = memberService.login(id, passwd);
		
		if(dto == null) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<script>"
					+ "alert('로그인 실패 \\n아이디와 비밀번호 확인하세요');"
					+ "history.back();"
					+ "</script>");
			return null;
		}else {
			session.setAttribute("user", dto);
			view.setViewName("redirect:/main");
		}
		
		return view;
	}
	
	//게시글 한건 조회하는 메서드
	@GetMapping("/board/{bno}")
	public ModelAndView boardView(ModelAndView view,
			@PathVariable int bno) {
		//글번호에 해당하는 게시글 조회
		BoardDTO dto = boardService.selectBoard(bno);
		//해당 게시글의 댓글 조회 
		List<BoardCommentDTO> commentList = boardService.selectBoardCommentList(bno);
		//해당 게시글의 첨부파일 목록을 조회
		List<FileDTO> fileList = boardService.selectBoardFileList(bno);
		//request 영역에 저장
		view.addObject("board", dto);
		view.addObject("commentList", commentList);
		view.addObject("fileList", fileList );
		//board_view.html로 이동해서 게시글을 출력
		view.setViewName("board_view");
		return view;
	}
	
	@GetMapping("/boardLike/{bno}")
	public ResponseEntity<String> boardLike(@PathVariable int bno,
			HttpSession session){
		Map<String, Object> map = new HashMap<String,Object>();
		if(session.getAttribute("user") == null) {
			map.put("code", 2);
			map.put("msg", "로그인 하셔야 이용하실수 있습니다.");
		}else {
			BoardMemberDTO dto = (BoardMemberDTO) session.getAttribute("user");
			try {
				boardService.insertBoardLike(bno,dto.getBoardMemberId());
				map.put("msg","해당 게시글에 좋아요 하셨습니다.");
			}catch (Exception e) {
				boardService.deleteBoardLike(bno,dto.getBoardMemberId());
				map.put("msg","해당 게시글에 좋아요를 취소하셨습니다.");
			}
			int count = boardService.selectBoardLikeCount(bno);
			map.put("count", count);
		}
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
}







