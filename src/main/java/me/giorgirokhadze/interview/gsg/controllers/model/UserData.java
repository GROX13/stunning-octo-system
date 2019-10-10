package me.giorgirokhadze.interview.gsg.controllers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

	@Pattern(regexp = "[a-zA-Z]{2,3}", message = "regionCode has invalid value")
	@NotBlank(message = "regionCode is mandatory")
	private String regionCode;

	@Min(value = 1, message = "scheduledMinutes should be greater than 0")
	@Max(value = 60, message = "scheduledMinutes should be les ot equal to 60")
	@NotNull(message = "scheduledMinutes is mandatory")
	private Integer scheduledMinutes;

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
