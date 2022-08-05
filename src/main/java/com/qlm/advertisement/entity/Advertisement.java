package com.qlm.advertisement.entity;

import java.util.Hashtable;

public interface Advertisement {
	public int getMaxAppearance();
	public double getWeight();
	public int lastAppearence(int dayIndex, int numberOfDays);
	public void showAdvertisement(int dayIndex);
	public Hashtable<Integer,Integer> getAllAppearences();
}
