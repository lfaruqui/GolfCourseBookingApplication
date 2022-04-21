package com.teesheet.application.utility;

public class Member {
	private String name;
	private String memberID;
	private String phone;
	
	public Member(String memberID, String name, String phone) {
		this.name = name; 
		this.memberID = memberID;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
