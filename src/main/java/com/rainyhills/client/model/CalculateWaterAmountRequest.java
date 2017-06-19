package com.rainyhills.client.model;

/**
 * @author Vadzim Mikhalenak.
 */
public class CalculateWaterAmountRequest implements JacksonSettings {

	private int[] hills;

	public int[] getHills() {
		return hills;
	}

	public void setHills(int[] hills) {
		this.hills = hills;
	}
}
