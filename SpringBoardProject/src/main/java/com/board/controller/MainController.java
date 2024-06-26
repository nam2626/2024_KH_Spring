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
			map.put("code", 1);
		}
		int count = boardService.selectBoardLikeCount(bno);
		map.put("count", count);
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	@GetMapping("/boardHate/{bno}")
	public ResponseEntity<String> boardHate(@PathVariable int bno,
			HttpSession session){
		Map<String, Object> map = new HashMap<String,Object>();
		if(session.getAttribute("user") == null) {
			map.put("code", 2);
			map.put("msg", "로그인 하셔야 이용하실수 있습니다.");
		}else {
			BoardMemberDTO dto = (BoardMemberDTO) session.getAttribute("user");
			try {
				boardService.insertBoardHate(bno,dto.getBoardMemberId());
				map.put("msg","해당 게시글에 싫어요 하셨습니다.");
			}catch (Exception e) {
				boardService.deleteBoardHate(bno,dto.getBoardMemberId());
				map.put("msg","해당 게시글에 싫어요를 취소하셨습니다.");
			}
			map.put("code", 1);
		}
		int count = boardService.selectBoardHateCount(bno);
		map.put("count", count);
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@GetMapping("/commentLike/{cno}")
	public ResponseEntity<String> commentLike(@PathVariable int cno,
			HttpSession session){
		Map<String, Object> map = new HashMap<String,Object>();
		if(session.getAttribute("user") == null) {
			map.put("code", 2);
			map.put("msg", "로그인 하셔야 이용하실수 있습니다.");
		}else {
			BoardMemberDTO dto = (BoardMemberDTO) session.getAttribute("user");
			try {
				boardService.insertBoardCommentLike(cno,dto.getBoardMemberId());
				map.put("msg","해당 댓글에 좋아요 하셨습니다.");
			}catch (Exception e) {
				boardService.deleteBoardCommentLike(cno,dto.getBoardMemberId());
				map.put("msg","해당 댓글에 좋아요를 취소하셨습니다.");
			}
			map.put("code", 1);
		}
			
		return new ResponseEntity(map,HttpStatus.OK);
	}
	@GetMapping("/commentHate/{cno}")
	public ResponseEntity<String> commentHate(@PathVariable int cno,
			HttpSession session){
		Map<String, Object> map = new HashMap<String,Object>();
		if(session.getAttribute("user") == null) {
			map.put("code", 2);
			map.put("msg", "로그인 하셔야 이용하실수 있습니다.");
		}else {
			BoardMemberDTO dto = (BoardMemberDTO) session.getAttribute("user");
			try {
				boardService.insertBoardCommentHate(cno,dto.getBoardMemberId());
				map.put("msg","해당 댓글에 싫어요 하셨습니다.");
			}catch (Exception e) {
				boardService.deleteBoardCommentHate(cno,dto.getBoardMemberId());
				map.put("msg","해당 댓글에 싫어요를 취소하셨습니다.");
			}
			map.put("code", 1);
		}
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@PostMapping("/comment/add")
	public String commentAdd(BoardCommentDTO dto,HttpSession session) {
		System.out.println(dto);
		BoardMemberDTO user = (BoardMemberDTO) session.getAttribute("user");
		dto.setWriter(user.getBoardMemberId());
		
		boardService.insertBoardComment(dto);
		
		return "redirect:/board/"+dto.getBno();
	}
	
	@GetMapping("/boardComment/delete")
	public String deleteBoardComment(int cno, int bno) {
		int result = boardService.deleteBoardComment(cno);
		System.out.println(result);
		
		return "redirect:/board/"+bno;
	}
	
	@GetMapping("/board/delete/{bno}")
	public String deleteBoard(@PathVariable int bno,
			HttpSession session,HttpServletResponse response) throws IOException {
		//세션 및 작성자 체크
		BoardMemberDTO member = (BoardMemberDTO) session.getAttribute("user");
		BoardDTO board = boardService.selectBoard(bno);
		
		if(member != null && 
				member.getBoardMemberId().equals(board.getBoardMemberId())) {
			//삭제 처리
			boardService.deleteBoard(bno);
		}else {
			//권한이 없는 사용자가 삭제를 수행
			//경고창 출력 이전 페이지로 이동
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<script>"
					+ "alert('삭제할 권한이 없습니다.');"
					+ "history.back();"
					+ "</script>");
			return null;
		}
		
		
		return "redirect:/";
	}
	
}







