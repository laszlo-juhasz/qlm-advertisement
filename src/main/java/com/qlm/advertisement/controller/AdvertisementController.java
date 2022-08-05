package com.qlm.advertisement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qlm.advertisement.entity.Advertisement;
import com.qlm.advertisement.entity.AdvertisementEntity;
import com.qlm.advertisement.request.AdvertisementRequest;
import com.qlm.advertisement.service.AdvertisementSystemService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.ApiDescription;

@RestController
@Api(value = "", tags = {"API for managing Advertisements"})
public class AdvertisementController {
	static final int MAX_APPEARANCE = 999;
	static final int MAX_ADVERTISEMENT = 999;
	
	@Autowired
	AdvertisementSystemService advertisementSystemService;

	@SuppressWarnings("rawtypes")
	@PostMapping(path = "advertisement", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register Advertisement", description = "Register a new Advertisement with a given parameters (if parameters fit to the specifications)")
	public ResponseEntity registerAdvertisement(@RequestBody AdvertisementRequest advertisementRequest) {
		//validate input fields
		if (advertisementSystemService.getAdvertisementList().size() == MAX_ADVERTISEMENT) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Advertisements reached the maximum size");
		}
		if (advertisementRequest.getMaxAppearance() < 1 || advertisementRequest.getMaxAppearance() > MAX_APPEARANCE) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("MaxAppearance should be between 1 and " + MAX_APPEARANCE);	
		}
		if (advertisementRequest.getWeight() < 0 || advertisementRequest.getWeight() > 1 ) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Weight should be between 0 and 1");	
		}
		advertisementSystemService.registerAdvertisement(new AdvertisementEntity(advertisementRequest.getMaxAppearance(), advertisementRequest.getWeight()));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(path = "advertisements", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Operation(summary = "Get Advertisements", description = "Returns the list of the Advertisements")
	public List<Advertisement> getAdvertisements() {
		return advertisementSystemService.getAdvertisementList();
	}	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "showNextAdvertisement")
	@Operation(summary = "Show next Advertisements", description = "Shows the next Advertisement(if it hasn't reached the daily limit)")
	public ResponseEntity showNextAdvertisement(@RequestParam(required = true) int dayIndex) {
		//validate input field
		if (dayIndex < 0) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("DayIndex should be greater than 1");	
		}
		//check whether at least one Advertisement has been registered
		if (advertisementSystemService.getAdvertisementList().isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is no registered Advertisement");	
		}
		advertisementSystemService.showNextAdvertisement(dayIndex);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}	
}
