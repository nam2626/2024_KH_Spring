package com.file.upload.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.file.upload.dto.FileDTO;

@Mapper
public interface BoardMapper {

  int insertAjaxFile(FileDTO fileDTO);

  int selectFileNo();

  FileDTO selectImageFile(int fno);
  
} 

  
