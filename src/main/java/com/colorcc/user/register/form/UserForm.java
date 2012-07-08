package com.colorcc.user.register.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.colorcc.user.register.validation.IntegerScopeValidation;

public class UserForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	@NotNull
	@Size(min = 5, max = 40)
	private String email;

	@Size(min = 6, max = 20)
	private String password;

	// @Digits(integer=3, fraction = 0)
	// @Min(1)
	// @Max(127)
	@IntegerScopeValidation(min = 0, max = 100, message = "{integer.scope.validation}")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
