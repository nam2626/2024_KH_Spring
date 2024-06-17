package com.mybatis.dto;

import org.apache.ibatis.type.Alias;

@Alias("major")
public class MajorDTO {
	private String majorNo;
	private String majorName;

	public MajorDTO() {	}

	public String getMajorNo() {
		return majorNo;
	}

	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Override
	public String toString() {
		return "MajorDTO [majorNo=" + majorNo + ", majorName=" + majorName + "]";
	}

}
