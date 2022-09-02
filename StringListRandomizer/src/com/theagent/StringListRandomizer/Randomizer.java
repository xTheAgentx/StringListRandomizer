package com.theagent.StringListRandomizer;

import java.util.Random;

public class Randomizer {

	private String[] list; // lines stored separately inside the array

	/**
	 * Input a multi-line string and get it back randomized
	 * 
	 * @param input Multi-line string
	 * @return String with randomized line order
	 */
	public String randomize(String input) {
		list = randomizeArray(split(input));
		return format(list);
	}

	/**
	 * Split string and store it inside array
	 * 
	 * @param input Mulit-line string
	 * @return Array filled with the string's content
	 */
	private String[] split(String input) {
		return input.split("\n");
	}

	/**
	 * Randomize the order of the content inside the array
	 * 
	 * @param stringArray <b>Filled</b> array
	 * @return Randomized array
	 */
	private String[] randomizeArray(String[] stringArray) {
		Random random = new Random();
		int numberOfRounds = random.nextInt(100) + 1;
		for (int i = 0; i < numberOfRounds; i++) {
			int pos1 = random.nextInt(stringArray.length - 1);
			int pos2 = random.nextInt(stringArray.length - 1);
			String tmp = stringArray[pos1];
			stringArray[pos1] = stringArray[pos2];
			stringArray[pos2] = tmp;
		}
		return stringArray;
	}

	/**
	 * Format the string like it was before
	 * 
	 * @param input
	 * @return Formatted string
	 */
	private String format(String[] input) {
		return String.join("\n", input);
	}

	/**
	 * Count number of lines
	 * 
	 * @return Number of lines
	 */
	public int count() {
		return list.length;
	}

}
