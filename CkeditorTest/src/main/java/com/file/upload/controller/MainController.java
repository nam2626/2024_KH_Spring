package com.file.upload.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.dto.FileDTO;
import com.file.upload.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MainController {
    private BoardService boardService;

    public MainController(BoardService boardService){
      this.boardService = boardService;
    }

    @GetMapping("/")
    public String main() {
        return "ckeditor_new";
    }
    @GetMapping("/register")
    public String registerView() {
        return "member_register";
    }

    @PostMapping("/ajax/profile/upload")
    public ResponseEntity<String> profileUpload(@RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {
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
      boardService.insertAjaxFile(fileDTO);
      //	6-4. map에 url로 경로를 만들어서 리턴
      Map<String, Object> map = new HashMap<String,Object>();
      map.put("url", "/ajax/download?fno="+fno);
      return new ResponseEntity(map,HttpStatus.OK);
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
    int fno = boardService.selectFileNo();
		FileDTO fileDTO = new FileDTO(f, 0, fno);
    boardService.insertAjaxFile(fileDTO);
		Map<String, Object> map = new HashMap<String, Object>();
    map.put("url","/ajax/download?fno="+fno);
		return new ResponseEntity(map,HttpStatus.OK);
	}

  @GetMapping("/ajax/download")
  public void ajaxFileDownload(int fno, HttpServletResponse response) throws IOException {
      //파일 정보 읽어옴
		FileDTO dto = boardService.selectImageFile(fno);
    System.out.println(dto);
			System.out.println(dto);
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
  
}
