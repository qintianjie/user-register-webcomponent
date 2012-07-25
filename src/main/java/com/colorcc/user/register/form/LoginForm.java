package com.colorcc.user.register.form;

import javax.validation.constraints.Size;

public class LoginForm {
	@Size(min = 5, max = 40)
	private String j_username;

	@Size(min = 5, max = 40)
	private String j_password;

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

}
