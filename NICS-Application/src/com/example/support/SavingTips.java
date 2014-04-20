package com.example.support;

import java.util.Random;

/**
 * 
 * @author Team 16
 * 
 * Creates saving tips for the user to see while in use of the application
 */

public class SavingTips {
	
	private Random rand;
	
	/**
	 * Randomizes the order of the tips each time
	 * the application is used
	 */
	
	public SavingTips() {
		rand = new Random();
	}
	
	/**
	 * A group of tips to be shown while the application
	 * is being used
	 */
	
	private static final String[] tips = { 
		"Don't just throw away loose change. Make sure to put it into a jar. It adds up.",
		"Try switching to generic brands instead of name brands.",
		"Start cooking at home instead of getting takeout.",
		"Try carpooling or using public transportation instead of driving to work alone everyday.",
		"Get free haircuts by learning to cut your own hair!",
		"Get a library card instead of paying for books",
		"Buy things in bulk."
		};
	
	/**
	 * Returns a tip based on the number
	 * 
	 * @return Random tip
	 */
	
	public String getTip() {
		return tips[rand.nextInt(tips.length)];
	}
}
