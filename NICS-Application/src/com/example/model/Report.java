package com.example.model;

import java.util.Date;

/**
 * 
 * @author Team 16
 * 
 * Deals with report dates
 *
 */

public class Report {
	
	private Date start;
	private Date end;
	
	/**
	 * Instantiates variables
	 * 
	 * @param start Begin Date
	 * @param end Finish date
	 */
	
	public Report(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Gets start date
	 * 
	 * @return Start date
	 */
	
	public Date getStart() {
		return start;
	}
	
	/**
	 * Gets end date
	 * 
	 * @return End date
	 */
	
	public Date getEnd() {
		return end;
	}
}
