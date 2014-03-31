package com.example.model;

import java.util.Date;

/**
 * 
 * @author Team 16
 *
 */

public class Report {
	
	private Date start;
	private Date end;
	
	public Report(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnd() {
		return end;
	}
}
