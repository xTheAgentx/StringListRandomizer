package com.theagent.StringListRandomizer;

import java.util.Random;

public class Randomizer {

	private String list;
	private int numberOfWords;

	/**
	 * Constructor
	 * 
	 * @param input
	 */
	public Randomizer(String input) {
		list = input;
		list = cleanup(list);
		numberOfWords = countWords(list);
		String[] listArray = split(list);
		listArray = randomize(listArray);
		list = format(listArray);
	}

	/**
	 * Turns all lines into a single line separated by commas
	 * 
	 * @param input String to simplify
	 * @return Simplified string
	 */
	private String cleanup(String input) {
		return input.replaceAll("\n", ",");
	}

	/**
	 * Counts all words / lines
	 * 
	 * @param input
	 * @return Number of words / lines
	 */
	private int countWords(String input) {
		int wordcount = 1;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ',') {
				wordcount++;
			}
		}
		return wordcount;
	}

	/**
	 * Splits every word
	 * 
	 * @param input
	 * @return Array with each index representing one word
	 */
	private String[] split(String input) {
		String[] strings = input.split(",");
		return strings;
	}

	/**
	 * Randomize the order of the words inside the array
	 * 
	 * @param input Array filled with words
	 * @return Randomized array
	 */
	private String[] randomize(String[] input) {
		Random iterations = new Random();
		int numberOfRounds = iterations.nextInt(100) + 1;
		for (int i = 0; i < numberOfRounds; i++) {
			int pos1 = iterations.nextInt(input.length - 1);
			int pos2 = iterations.nextInt(input.length - 1);
			String tmp = input[pos1];
			input[pos1] = input[pos2];
			input[pos2] = tmp;
		}
		return input;
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
	 * 
	 * @return List
	 */
	public String getList() {
		return list;
	}

	/**
	 * 
	 * @return Number of words inside the list
	 */
	public int getNumberOfWords() {
		return numberOfWords;
	}

}
