package com.board.dto;

import org.apache.ibatis.type.Alias;

@Alias("comment")
public class BoardCommentDTO {
	private int cno;
	private int bno;
	private String writer;
	private String cdate;
	private String comment;
	private int clike;
	private int chate;

	public BoardCommentDTO() {
	}

	public BoardCommentDTO(int bno, String writer, String comment) {
		this.bno = bno;
		this.writer = writer;
		this.comment = comment;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getClike() {
		return clike;
	}

	public void setClike(int clike) {
		this.clike = clike;
	}

	public int getChate() {
		return chate;
	}

	public void setChate(int chate) {
		this.chate = chate;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", cdate=" + cdate + ", comment="
				+ comment + ", clike=" + clike + ", chate=" + chate + "]";
	}
	
	
}
