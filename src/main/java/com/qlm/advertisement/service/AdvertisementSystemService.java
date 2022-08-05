package com.qlm.advertisement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.qlm.advertisement.entity.Advertisement;
import com.qlm.advertisement.entity.AdvertisementEntity;

@Component
public class AdvertisementSystemService implements AdvertisementSystem {

	public List<Advertisement> advertisements = new ArrayList<Advertisement>();
	
	@Override
	public void registerAdvertisement(Advertisement ad) {
		advertisements.add(ad);
	}

	@Override
	public void showNextAdvertisement(int dayIndex) {
		double sumWeight = advertisements.stream()
				.filter(a -> ((AdvertisementEntity) a).showable(dayIndex))
				.map(Advertisement::getWeight)
				.collect(Collectors.summingDouble(Double::doubleValue));
		
		int idx = 0;
		for (double r = Math.random() * sumWeight; idx < advertisements.size() - 1; ++idx) {
			if (((AdvertisementEntity)advertisements.get(idx)).showable(dayIndex)) {
			    r -= advertisements.get(idx).getWeight();
			    if (r <= 0.0) break;				
			}
		}
		advertisements.get(idx).showAdvertisement(dayIndex);
	}

	@Override
	public List<Advertisement> getAdvertisementList() {
		return advertisements;
	}
}
