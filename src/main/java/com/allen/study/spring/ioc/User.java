/**
 * 
 */
package com.allen.study.spring.ioc;

import java.io.Serializable;

/**
 * @author dream
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -517882688311941105L;

	private Long id;

	private String userName;

	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "User{id='" + id + "', userName='" + userName + "', note='" + note + "'}";
	}

}
