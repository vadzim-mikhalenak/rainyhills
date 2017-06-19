package com.rainyhills.controller;

import com.rainyhills.client.model.CalculateWaterAmountRequest;
import com.rainyhills.client.model.CalculateWaterAmountResponse;
import com.rainyhills.domain.service.RainyHillsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * @author Vadzim Mikhalenak.
 */
@Controller
@RequestMapping("rainyhills")
public class RainyHillsController {

	private static final Logger logger = LoggerFactory.getLogger(RainyHillsController.class);

	private final RainyHillsService rainyHillsService;

	@Autowired
	public RainyHillsController(RainyHillsService rainyHillsService) {
		this.rainyHillsService = rainyHillsService;
	}

	@RequestMapping(value = "calculatewater",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity calculateWaterAmount(@RequestBody CalculateWaterAmountRequest request) {

		if (request.getHills() == null) {
			logger.info("Illegal input data. Hills couldn't be null.");
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new CalculateWaterAmountResponse("Hills should not be null"));
		}

		logger.debug("Validate hills for negative data");
		for (final int hill : request.getHills()) {
			if (hill < 0) {
				logger.debug("Negative value was found");
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(new CalculateWaterAmountResponse("Hills should not contain negative value"));
			}
		}

		logger.info("Calculate amount of water for: {}", Arrays.toString(request.getHills()));

		Integer waterAmount = rainyHillsService.calculateRemainingWater(request.getHills());

		return ResponseEntity
				.ok(new CalculateWaterAmountResponse(waterAmount));
	}

}
