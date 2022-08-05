package com.qlm.advertisement.request;

/**
 * Responsible for storing Advertisement properties
 *
 */
public class AdvertisementRequest {
	private int maxAppearance;
	private double weight;
	
	public int getMaxAppearance() {
		return maxAppearance;
	}
	public void setMaxAppearance(int maxAppearance) {
		this.maxAppearance = maxAppearance;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
