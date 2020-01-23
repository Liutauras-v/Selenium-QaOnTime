package utils;

import java.util.Random;

public class Utils {

	Random random;

	public Utils() {
		this.random = new Random();
	}

	public String randomString(int length) {
		final String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		if (length < 1) {
			throw new IllegalArgumentException();
		}
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			int rndCharAt = random.nextInt(RANDOM_STRING.length());
			char rndChar = RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();

	}

	public int randomInt(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		return random.nextInt((max - min) + 1) + min;
	}

}
