package com.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardCommentDTO;
import com.board.dto.BoardDTO;
import com.board.dto.BoardMemberDTO;
import com.board.dto.FileDTO;
import com.board.dto.GradeDTO;
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
			@PathVariable int bno, HttpSession session) {
		//글번호에 해당하는 게시글 조회
		BoardDTO dto = boardService.selectBoard(bno);
		//해당 게시글의 댓글 조회 
		List<BoardCommentDTO> commentList = boardService.selectBoardCommentList(bno);
		//해당 게시글의 첨부파일 목록을 조회
		List<FileDTO> fileList = boardService.selectBoardFileList(bno);
		//게시글 조회수 업데이트
		HashSet<Integer> set = (HashSet<Integer>) session.getAttribute("history");
		//세션에 방문한 게시글 목록이 없을때
		if(set == null) {
			set = new HashSet<Integer>();
			session.setAttribute("history", set);
		}
		if(set.add(bno)) {
			boardService.updateBoardCount(bno);
		}
		
		
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
			//해당 첨부파일도 삭제 처리
			
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
	
	@GetMapping("/board/write/view")
	public String boardWriteView() {
		return "board_write";
	}
	
	@PostMapping("/board/write")
	public String boardWrite(BoardDTO dto, HttpSession session,
			@RequestParam(value = "file") MultipartFile[] file) throws IllegalStateException, IOException {
		//1. 사용자가 작성한 게시글 제목, 내용, 파일 받아옴
		//2. 작성자는 세션에서 아이디만 빌려옴
		BoardMemberDTO memberDTO = (BoardMemberDTO) session.getAttribute("user");
		dto.setBoardMemberId(memberDTO.getBoardMemberId());
		//3. 게시글 새번호 받아옴
		int bno = boardService.getBoardNo();
		dto.setBoardNo(bno);
		//4. 해당 게시글 DB에 등록
		boardService.insertBoard(dto);
		//5. 파일 업로드
		//업로드할 기본 경로 지정 및 없으면 생성
		File root = new File("c:\\fileupload");
		if(!root.exists())
			root.mkdirs();
		
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getSize() + " " + file[i].getOriginalFilename());
			//파일 사이즈 체크 해서 0이면 업로드가 안된 항목
			if(file[i].getSize() == 0)
				continue;
			//파일 쓰기
			//업로드할 경로 설정
			File f = new File(root, file[i].getOriginalFilename());
			file[i].transferTo(f);//실제 파일 쓰기를 수행
			//6. 해당 파일 경로를 DB에 등록
			FileDTO fileDTO = new FileDTO(f, bno, i+1);
			boardService.insertBoardFile(fileDTO);
		}
		return "redirect:/board/"+bno;
	}
	
	@GetMapping("/file")
	public void fileDown(int bno, int fno, HttpServletResponse response) throws IOException {
		//파일 정보 읽어옴
		FileDTO dto = boardService.selectFile(bno,fno);
		
		//출력 스트림 연결 데이터 전송
		File file = new File(dto.getPath());
		
		response.setHeader("Content-Disposition", "attachement;fileName="+dto.getFileName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		
		FileInputStream fis = new FileInputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		byte[] buffer = new byte[1024*1024];
		
		while(true) {
			int size = fis.read(buffer);
			if(size == -1) break;
			bos.write(buffer, 0, size);
			bos.flush();
		}
		
		bos.close();
		fis.close();
	}
	
	@PostMapping("/file/ajax")
	public ResponseEntity<String> fileAjaxUpload(@RequestParam(value="upload") MultipartFile file) throws IllegalStateException, IOException{
		File root = new File("c:\\fileupload");
		if(!root.exists())
			root.mkdirs();
		
		System.out.println(file.getSize() + " " + file.getOriginalFilename());
		//파일 사이즈 체크 해서 0이면 업로드가 안된 항목

		//파일 쓰기
		//업로드할 경로 설정
		File f = new File(root, file.getOriginalFilename());
		file.transferTo(f);//실제 파일 쓰기를 수행
		//6. 해당 파일 경로를 DB에 등록
		//	6-1. 파일 번호 받아옴
		int fno = boardService.selectFileNo();
		//	6-2. fileDTO에 파일번호 등록
		FileDTO fileDTO = new FileDTO(f, 0, fno);
		//	6-3. DB에 데이터 추가
		boardService.insertImageFile(fileDTO);
		//	6-4. map에 url로 경로를 만들어서 리턴
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("url", "/file/ajax/down/"+fno);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@GetMapping("/file/ajax/down/{fno}")
	public void fileAjaxDownload(@PathVariable int fno, HttpServletResponse response) throws IOException {
		//파일 정보 읽어옴
		FileDTO dto = boardService.selectImageFile(fno);
		
		//출력 스트림 연결 데이터 전송
		File file = new File(dto.getPath());
		
		response.setHeader("Content-Disposition", "attachement;fileName="+dto.getFileName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		
		FileInputStream fis = new FileInputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		byte[] buffer = new byte[1024*1024];
		
		while(true) {
			int size = fis.read(buffer);
			if(size == -1) break;
			bos.write(buffer, 0, size);
			bos.flush();
		}
		
		bos.close();
		fis.close();
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/member/register/view")
	public String memberReigsterView() {
		return "member_register";
	}
	
	@PostMapping("/ajax/profile/upload")
	public ResponseEntity<String> fileAjaxProfileUpload(@RequestParam(value="upload") MultipartFile file) throws IllegalStateException, IOException{
		File root = new File("c:\\fileupload\\profile");
		if(!root.exists())
			root.mkdirs();
		
		System.out.println(file.getSize() + " " + file.getOriginalFilename());
		//파일 사이즈 체크 해서 0이면 업로드가 안된 항목

		//파일 쓰기
		//업로드할 경로 설정
		File f = new File(root, file.getOriginalFilename());
		file.transferTo(f);//실제 파일 쓰기를 수행
		//6. 해당 파일 경로를 DB에 등록
		//	6-1. 파일 번호 받아옴
		int fno = boardService.selectFileNo();
		//	6-2. fileDTO에 파일번호 등록
		FileDTO fileDTO = new FileDTO(f, 0, fno);
		//	6-3. DB에 데이터 추가
		boardService.insertImageFile(fileDTO);
		//	6-4. map에 url로 경로를 만들어서 리턴
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("url", "/file/ajax/down/"+fno);
		map.put("fno",fno);
		return new ResponseEntity(map,HttpStatus.OK);
	}
	
	@PostMapping("/member/insert")
	public String insertMember(BoardMemberDTO dto, HttpSession session,
			HttpServletResponse response) throws IOException {
		
		try {
			memberService.insertMember(dto);
			dto.setBoardMemberPasswd("");
			session.setAttribute("user", dto);
		} catch (Exception e) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<script>"
					+ "alert('회원가입에 실패하셨습니다.\\n입력하신 내용을 확인하세요');"
					+ "location.href='/member/register/view';"
					+ "</script>");
			return null;
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/member/update/{id}")
	public ModelAndView myPage(@PathVariable String id, ModelAndView view) {
		//회원정보 조회
		BoardMemberDTO dto = memberService.selectMember(id);
		view.addObject("dto", dto);		
		view.setViewName("my_page");
		return view;
	}
	
	@PostMapping("/member/update")
	public String updateMember(BoardMemberDTO dto, HttpSession session) {
		memberService.updateMember(dto);
		session.setAttribute("user", dto);
		return "redirect:/";
	}
	

	@GetMapping("/member")
	public ModelAndView memberMain(ModelAndView view) {
		//전체 회원정보, 전체 등급정보
		List<BoardMemberDTO> memberList = memberService.selectAllMember(); 		
		List<GradeDTO> gradeList = memberService.selectAllGrade();
		
		view.addObject("memberList", memberList);
		view.addObject("gradeList", gradeList);
		
		view.setViewName("member_manage");
		return view;
	}
	
	@DeleteMapping("/member/delete")
	public ResponseEntity<String> deleteMember(String id){
		System.out.println("내용 : " + id);	
		//회원정보 삭제하고 결과값 리턴해서 Ajax로 응답
		int count = memberService.deleteMember(id);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("msg", count == 0 ? "회원정보 삭제 실패" : "회원정보 삭제 성공" );
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@PutMapping("/member/admin/update")
	public ResponseEntity<String> updateMember(BoardMemberDTO dto){
		System.out.println(dto);
//		int count = 0;		
		dto.setBoardMemberProfile(-1);
		int count = memberService.updateMember(dto);		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("msg", count == 0 ? "회원정보 수정 실패" : "회원정보 수정 성공" );
		return new ResponseEntity(map, HttpStatus.OK);
	}
	@PatchMapping("/member/update/grade")
	public ResponseEntity<String> updateMemberGreade(@RequestParam Map<String, String> param){
		System.out.println(param);
		int count = memberService.updateMemberGrade(param);		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("msg", count == 0 ? "회원정보 등급 수정 실패" : "회원정보 등급 수정 성공" );
		return new ResponseEntity(map, HttpStatus.OK);
	}
	@GetMapping("/member/search")
	public ResponseEntity<String> searchMember(@RequestParam Map<String , String> param){
		System.out.println(param);
		List<BoardMemberDTO> list = memberService.searchMember(param);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", "회원정보 조회 성공");
		map.put("list", list);
		map.put("grade",memberService.selectAllGrade());
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@GetMapping("/board/update/{bno}")
	public ModelAndView boardUpdateView(ModelAndView view,
			@PathVariable int bno) {
		BoardDTO dto = boardService.selectBoard(bno);
		List<FileDTO> fileList = boardService.selectBoardFileList(bno);

		view.addObject("board", dto);
		view.addObject("fileList", fileList );
		view.setViewName("board_update_view");
		return view;
	}
	
	@PostMapping("/board/update")
	public ModelAndView updateBoard(BoardDTO dto, ModelAndView view,
			@RequestParam(name = "file", required = false) MultipartFile[] file, 
			@RequestParam(name = "dfile", required = false) int[] fnoList) throws IOException {
		System.out.println(Arrays.toString(fnoList));
		System.out.println(dto);
		
		
		//파일 삭제
		//	1. 삭제할 파일 목록을 조회
		if(fnoList != null) {
			List<FileDTO> deleteFileList = boardService.selectBoardFileList(dto.getBoardNo());
			//	2. 물리적으로 삭제
			for(int fno : fnoList) {
				for(int i = 0; i<deleteFileList.size();i++) {
					if(deleteFileList.get(i).getFno() == fno) {
						File f = new File(deleteFileList.get(i).getPath());
						f.delete();
					}
				}
			}
			//	3. DB에서 삭제
			boardService.deleteBoardFileList(dto.getBoardNo(),fnoList);
		}
		//새 파일 업로드
		//	1. 물리적으로 저장
		//	2. DB에 추가
		if(file != null) {
			File root = new File("c:\\fileupload");
			if(!root.exists())
				root.mkdirs();
			
			TreeSet<Integer> set = new TreeSet<Integer>(boardService.selectBoardFileNumbers(dto.getBoardNo()));
			
			int no = 0;
			for(int i=0;i<3;i++) {
				//파일 사이즈 체크 해서 0이면 업로드가 안된 항목
				if(file[no].getSize() == 0)
					continue;
				if(!set.add(i+1)) continue;
				//파일 쓰기
				//업로드할 경로 설정
				File f = new File(root, file[no].getOriginalFilename());
				file[no].transferTo(f);//실제 파일 쓰기를 수행
				//6. 해당 파일 경로를 DB에 등록
				FileDTO fileDTO = new FileDTO(f, dto.getBoardNo(), i+1);
				boardService.insertBoardFile(fileDTO);
				no++;
				if(no == file.length) break; //마지막 파일번호 안쓸때 체크
			}
		}
		//---------------------------------------------
		int count = boardService.updateBoard(dto);
		view.setViewName("redirect:/board/"+dto.getBoardNo());
		return view;
	}
	
	
	
	
	
	
	
	
	
 }







