package com.qlm.advertisement.service;

import java.util.List;

import com.qlm.advertisement.entity.Advertisement;

public interface AdvertisementSystem {
	public void registerAdvertisement(Advertisement ad);
	public void showNextAdvertisement(int dayIndex);
	public List<Advertisement> getAdvertisementList();
}
