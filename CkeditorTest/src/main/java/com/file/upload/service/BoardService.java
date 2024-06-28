package com.file.upload.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.file.upload.dto.FileDTO;
import com.file.upload.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public int insertAjaxFile(FileDTO fileDTO) {
		return mapper.insertAjaxFile(fileDTO);
	}

	public int selectFileNo() {
		return mapper.selectFileNo();
	}

	public FileDTO selectImageFile(int fno) {
		return mapper.selectImageFile(fno);	
	}
	
}






