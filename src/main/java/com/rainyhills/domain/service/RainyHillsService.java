package com.rainyhills.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service for calculation of water volume.
 *
 * @author Vadzim Mikhalenak.
 */
@Service
public class RainyHillsService {

	private static final Logger logger = LoggerFactory.getLogger(RainyHillsService.class);

	/**
	 * Calculates the volume of water which remained after the rain, in units.
	 *
	 * @param hills array of ints represents height of each hill.
	 * @return volume of water
	 */
	public Integer calculateRemainingWater(int... hills) {

		logger.debug("Calculation of remaining water");
		if (hills == null) {
			logger.info("input data is null");
			return null;
		}

		logger.debug("Check for negative values");
		for (final int hill : hills) {
			if (hill < 0) {
				logger.debug("Negative value was found");
				return null;
			}
		}

		logger.debug("Hills: {}", Arrays.toString(hills));

		logger.debug("Start from Left to Right traverse");
		int result = 0;
		int maxL = 0;

		for (int i = 0; i < hills.length; i++) {
			if (maxL <= hills[i]) {
				maxL = hills[i];
				if (i < hills.length - 1 && maxL <= hills[i + 1]) {
					continue;
				}
			}

			Pair rIndex = getSumFromLeftToRight(i, maxL, hills);
			//Pair rIndex = sumPart(i, hills.length - 1, 1, maxL, hills);

			if (rIndex.index == -1) {
				logger.debug("From Left to Right traverse is over");
				break;
			}

			result += rIndex.sum;
			i = rIndex.index - 1;
		}

		logger.debug("Sum of Left to Right traverse = {}", result);

		logger.debug("----------------------------------");

		logger.debug("Start from Right to Left traverse");
		int maxR = 0;
		for (int i = hills.length - 1; i >= 0; i--) {
			if (maxR <= hills[i]) {
				maxR = hills[i];
				if (i > 0 && maxR <= hills[i - 1]) {
					continue;
				}
			}

			Pair lIndex = getSumFromRightToLeft(i, maxR, hills);
			//Pair lIndex = sumPart(i, 0, -1, maxR, hills);

			if (lIndex.index == -1) {
				logger.debug("From Right to Left traverse is over");
				break;
			}

			result += lIndex.sum;
			i = lIndex.index + 1;
		}
		logger.debug("Result={}", result);
		return result;
	}

	/**
	 * Calculates water volume for part of hills (going from left to right while a hill to right is lower or equal to passed hill [maxL])
	 *
	 * @param i     current index of the hill
	 * @param maxL  height of this hill
	 * @param hills arrays of the hills
	 * @return calculated water volume for the separate part
	 */
	private Pair getSumFromLeftToRight(int i, int maxL, int[] hills) {
		int sum = 0;

		for (int j = i + 1; j < hills.length; j++) {
			if (hills[j] >= maxL) {
				return new Pair(j, sum);
			}
			sum += maxL - hills[j];
		}

		return new Pair(-1, 0);
	}

	/**
	 * Calculates water volume for part of hills (going from right to left while a hill to left is lower or equal to passed hill [maxR])
	 *
	 * @param i     current index of the hill
	 * @param maxR  height of this hill
	 * @param hills arrays of the hills
	 * @return calculated water volume for the separate part
	 */
	private Pair getSumFromRightToLeft(int i, int maxR, int[] hills) {
		int sum = 0;

		for (int j = i - 1; j >= 0; j--) {
			if (hills[j] >= maxR) {
				return new Pair(j, sum);
			}
			sum += maxR - hills[j];
		}

		return new Pair(-1, 0);
	}

	/**
	 * Utility class for representing pair of index and sum.
	 */
	private class Pair {

		int index;
		int sum;

		Pair(int index, int sum) {
			this.index = index;
			this.sum = sum;
		}
	}

}
