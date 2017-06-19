package com.rainyhills.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Vadzim Mikhalenak.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculateWaterAmountResponse implements JacksonSettings {

	private Integer amountOfWater;
	private String errorMessage;

	public CalculateWaterAmountResponse(Integer amountOfWater) {
		this.amountOfWater = amountOfWater;
	}

	public CalculateWaterAmountResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getAmountOfWater() {
		return amountOfWater;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
