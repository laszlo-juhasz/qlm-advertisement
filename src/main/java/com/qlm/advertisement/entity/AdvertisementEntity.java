package com.qlm.advertisement.entity;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class AdvertisementEntity implements Advertisement {
	private int maxAppearance;
	private double weight;
	private Hashtable<Integer,Integer> allAppearences;
	
	public AdvertisementEntity(int maxAppearance, double weight) {
		super();
		this.maxAppearance = maxAppearance;
		this.weight = weight;
		allAppearences = new Hashtable<Integer,Integer>();
	}
	
	@Override
	public int getMaxAppearance() {
		return maxAppearance;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public int lastAppearence(int dayIndex, int numberOfDays) {
		return allAppearences.entrySet().stream()
		.filter(e -> (e.getKey() >= dayIndex && e.getKey() <= (dayIndex + numberOfDays)))
		.map(Entry::getValue)
		.collect(Collectors.summingInt(Integer::intValue));
	}

	@Override
	public synchronized void showAdvertisement(int dayIndex) {
		if (allAppearences.containsKey(dayIndex)) {
			if (allAppearences.get(dayIndex) < maxAppearance) {
				allAppearences.put(dayIndex, allAppearences.put(dayIndex, 1) + 1);
			}
		} else {
			allAppearences.put(dayIndex, 1);
		}
	}

	@Override
	public Hashtable<Integer, Integer> getAllAppearences() {
		return allAppearences;
	}
	
	/**
	 * Checks whether the Advertisement can be shown on a given day
	 * @param dayIndex
	 * @return
	 */
	public boolean showable(int dayIndex) {
		return ((!allAppearences.containsKey(dayIndex)) || allAppearences.get(dayIndex) < maxAppearance);
	}

}
