package com.board.dto;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class BoardMemberDTO {
	private String boardMemberId;
	private String boardMemberName;
	private String boardMemberPasswd;
	private String boardMemberNick;
	private int boardMemberGrade;
	private int boardMemberProfile;
	
	public BoardMemberDTO() {	}

	public int getBoardMemberProfile() {
		return boardMemberProfile;
	}

	public void setBoardMemberProfile(int boardMemberProfile) {
		this.boardMemberProfile = boardMemberProfile;
	}


	public String getBoardMemberId() {
		return boardMemberId;
	}

	public void setBoardMemberId(String boardMemberId) {
		this.boardMemberId = boardMemberId;
	}

	public String getBoardMemberName() {
		return boardMemberName;
	}

	public void setBoardMemberName(String boardMemberName) {
		this.boardMemberName = boardMemberName;
	}

	public String getBoardMemberPasswd() {
		return boardMemberPasswd;
	}

	public void setBoardMemberPasswd(String boardMemberPasswd) {
		this.boardMemberPasswd = boardMemberPasswd;
	}

	public String getBoardMemberNick() {
		return boardMemberNick;
	}

	public void setBoardMemberNick(String boardMemberNick) {
		this.boardMemberNick = boardMemberNick;
	}

	public int getBoardMemberGrade() {
		return boardMemberGrade;
	}

	public void setBoardMemberGrade(int boardMemberGrade) {
		this.boardMemberGrade = boardMemberGrade;
	}

	@Override
	public String toString() {
		return "BoardMemberDTO [boardMemberId=" + boardMemberId + ", boardMemberName=" + boardMemberName
				+ ", boardMemberPasswd=" + boardMemberPasswd + ", boardMemberNick=" + boardMemberNick
				+ ", boardMemberGrade=" + boardMemberGrade + ", boardMemberProfile=" + boardMemberProfile + "]";
	}

	
}
