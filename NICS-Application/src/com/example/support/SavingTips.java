package com.example.support;

import java.util.Random;

public class SavingTips {
	
	private Random rand;
	
	public SavingTips() {
		rand = new Random(tips.length);
	}
	private static final String[] tips = { 
		"Don't just throw away loose change. Make sure to put it into a jar. It adds up.",
		"Try switching to generic brands instead of name brands.",
		"Start cooking at home instead of getting takeout.",
		"Try carpooling or using public transportation instead of driving to work alone everyday.",
		"Get free haircuts by learning to cut your own hair!",
		"Get a library card instead of paying for books",
		"Buy things in bulk."
		};
	
	public String getTip() {
		return tips[rand.nextInt()];
	}
}
