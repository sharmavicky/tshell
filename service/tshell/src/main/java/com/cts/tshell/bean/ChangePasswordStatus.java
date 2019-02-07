package com.cts.tshell.bean;

public class ChangePasswordStatus {
	
	private boolean currentPasswordIncorrect;
	private boolean passwordChanged;
	private boolean newAndOldPasswordSame;
	private String message;
	
	public ChangePasswordStatus() {
		super();
	}

	public boolean isCurrentPasswordIncorrect() {
		return currentPasswordIncorrect;
	}

	public void setCurrentPasswordIncorrect(boolean currentPasswordIncorrect) {
		this.currentPasswordIncorrect = currentPasswordIncorrect;
	}

	public boolean isPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(boolean passwordChanged) {
		this.passwordChanged = passwordChanged;
	}

	public boolean isNewAndOldPasswordSame() {
		return newAndOldPasswordSame;
	}

	public void setNewAndOldPasswordSame(boolean newAndOldPasswordSame) {
		this.newAndOldPasswordSame = newAndOldPasswordSame;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
