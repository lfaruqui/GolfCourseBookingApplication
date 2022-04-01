package com.teesheet.testing.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Jayden Craft
 * Mar 27, 2022
 */
public class DateAndTime {
	
	public ArrayList<LocalDateTime> getRangeOfDates(LocalDateTime start, int numDays){
		ArrayList<LocalDateTime> days = new ArrayList<>();
		
		for (int i = 0 ; i < numDays; i++) {
			String newDay = start.plusDays(i).format(DateTimeFormatter.ofPattern("M/dd/yy h:mm a"));
			days.add(start.plusDays(i));
		}
		
		return days;
	}
	
	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.now();
		now.format(DateTimeFormatter.ofPattern("M/dd/yy HH:mm:ss"));
		
		DateAndTime d = new DateAndTime();
		
		System.out.println(d.getRangeOfDates(now, 14));
		
	}

}
