package com.allen.study.regulation;

public class UserInfo {
	private Long id;
	private String userName;

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

	@Override
	public int hashCode() {
		int result = id == null ? 0 : Long.hashCode(id);
		if (userName != null) {
			result = 31 * result + userName.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		UserInfo userInfo = (UserInfo) obj;
		return (id == userInfo.id || (id != null && id.equals(userInfo.id)))
				&& (userName == userInfo.userName || (userName != null && userName.equals(userInfo.userName)));
	}

	public static void main(String[] args) {
		UserInfo u1 = new UserInfo();
		System.out.println(u1.hashCode());
	}
}
