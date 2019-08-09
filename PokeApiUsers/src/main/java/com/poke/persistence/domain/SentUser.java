package com.poke.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SentUser {
	@Id
	@GeneratedValue
	private Long memberNumber;

	private String userName;

	public SentUser() {
	}

	public SentUser(User user) {
		this.memberNumber = user.getMemberNumber();
		this.userName = user.getUserName();
	}

	public Long getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Long memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
		return this.memberNumber + this.userName;
	}

}
