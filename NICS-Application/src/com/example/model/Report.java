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
	
	public Report(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Gets start date
	 * @return
	 */
	
	public Date getStart() {
		return start;
	}
	
	/**
	 * Gets end date
	 * @return
	 */
	
	public Date getEnd() {
		return end;
	}
}
