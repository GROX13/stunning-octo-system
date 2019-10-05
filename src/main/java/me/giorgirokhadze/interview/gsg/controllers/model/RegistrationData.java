package me.giorgirokhadze.interview.gsg.controllers.model;

import me.giorgirokhadze.interview.gsg.controllers.model.validators.UniqueUsername;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationData {

	@UniqueUsername
	@NotBlank(message = "username is mandatory")
	private String username;

	@NotBlank(message = "password is mandatory")
	private CharSequence password;

	@NotBlank(message = "regionCode is mandatory")
	private String regionCode;

	@Min(value = 1, message = "scheduledMinutes should be greater than 0")
	@Max(value = 60, message = "scheduledMinutes should be les ot equal to 60")
	@NotNull(message = "scheduledMinutes is mandatory")
	private Integer scheduledMinutes;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CharSequence getPassword() {
		return password;
	}

	public void setPassword(CharSequence password) {
		this.password = password;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Integer getScheduledMinutes() {
		return scheduledMinutes;
	}

	public void setScheduledMinutes(Integer scheduledMinutes) {
		this.scheduledMinutes = scheduledMinutes;
	}
}
