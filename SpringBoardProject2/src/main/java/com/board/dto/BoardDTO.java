package com.board.dto;

public class BoardDTO {
	private int boardNo;
	private String boardTitle;
	private String boardMemberId;
	private String boardMemberNick;
	private String boardContent;
	private String boardWriteDate;
	private int boardCount;
	private int boardLike;
	private int boardHate;

	public BoardDTO() {
	}

	public BoardDTO(String boardTitle, String boardMemberId, String boardContent) {
		this.boardTitle = boardTitle;
		this.boardMemberId = boardMemberId;
		this.boardContent = boardContent;
	}

	public BoardDTO(int boardNo, String boardTitle, String boardMemberId, String boardContent) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardMemberId = boardMemberId;
		this.boardContent = boardContent;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardMemberId() {
		return boardMemberId;
	}

	public void setBoardMemberId(String boardMemberId) {
		this.boardMemberId = boardMemberId;
	}

	public String getBoardMemberNick() {
		return boardMemberNick;
	}

	public void setBoardMemberNick(String boardMemberNick) {
		this.boardMemberNick = boardMemberNick;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriteDate() {
		return boardWriteDate;
	}

	public void setBoardWriteDate(String boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}

	public int getBoardHate() {
		return boardHate;
	}

	public void setBoardHate(int boardHate) {
		this.boardHate = boardHate;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardMemberId=" + boardMemberId
				+ ", boardMemberNick=" + boardMemberNick + ", boardContent=" + boardContent + ", boardWriteDate="
				+ boardWriteDate + ", boardCount=" + boardCount + ", boardLike=" + boardLike + ", boardHate="
				+ boardHate + "]";
	}

}
