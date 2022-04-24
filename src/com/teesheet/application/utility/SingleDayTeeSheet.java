package com.teesheet.application.utility;

import java.util.ArrayList;

public class SingleDayTeeSheet {
	
	private String date;
	private ArrayList<SingleTeeTime> info;
    

    public SingleDayTeeSheet(String date) {
        this.date = date;
        info = new ArrayList<SingleTeeTime>();
    }
    
    public SingleDayTeeSheet() {
    	
    }


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public void addTeeTime(SingleTeeTime time) {
		info.add(time);
	}

	public ArrayList<SingleTeeTime> getTee_Times() {
		return info;
	}


	public void setTeeTimes(ArrayList<SingleTeeTime> info) {
		this.info = info;
	}

    
}