package com.mybatis.dto;

import org.apache.ibatis.type.Alias;

@Alias("student")
public class StudentDTO {
	private String studentNo;
	private String studentName;
	private String studentGender;
	private double studentScore;
	private String majorNo;

	public StudentDTO() {
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public double getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(double studentScore) {
		this.studentScore = studentScore;
	}

	public String getMajorNo() {
		return majorNo;
	}

	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}

	@Override
	public String toString() {
		return "StudentDTO [studentNo=" + studentNo + ", studentName=" + studentName + ", studentGender="
				+ studentGender + ", studentScore=" + studentScore + ", majorNo=" + majorNo + "]";
	}
	
}
