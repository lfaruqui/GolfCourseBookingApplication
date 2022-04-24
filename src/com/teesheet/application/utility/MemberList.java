package com.teesheet.application.utility;

import java.util.ArrayList;

/**
 * Holds all the members' info in the database
 */

public class MemberList {
	private ArrayList<Member> members;
	
	public MemberList() {
		members = new ArrayList<>();
	}
	
	public void addMember(Member e) {
		members.add(e);
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
}
