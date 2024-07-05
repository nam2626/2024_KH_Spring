package com.member.dto;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class MemberDTO {
	private String userID;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userPhone;
	private String userJoinDate;
	private String userNickName;
	private String userGender;

	public MemberDTO(String userID, String userName, String userPassword, String userEmail, String userPhone,
			String userJoinDate, String userNickName, String userGender) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userJoinDate = userJoinDate;
		this.userNickName = userNickName;
		this.userGender = userGender;
	}

	public MemberDTO() {
		super();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserJoinDate() {
		return userJoinDate;
	}

	public void setUserJoinDate(String userJoinDate) {
		this.userJoinDate = userJoinDate;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Override
	public String toString() {
		return "MemberDTO [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userJoinDate=" + userJoinDate
				+ ", userNickName=" + userNickName + ", userGender=" + userGender + "]";
	}

}
