package com.hikefinderapp;

import java.util.Collections;
import java.util.Comparator;

public class ProfileComparison implements Comparator<UserProfile>{
	@Override
	public int compare(UserProfile p1, UserProfile p2) {
		// TODO Auto-generated method stub
		int month1 = Integer.parseInt(p1.getDateCompleted().substring(0,1)) - 1;
		int day1 = Integer.parseInt(p1.getDateCompleted().substring(2,4));
		int year1 = Integer.parseInt(p1.getDateCompleted().substring(5,9));
		
		int month2 = Integer.parseInt(p2.getDateCompleted().substring(0,1)) - 1;
		int day2 = Integer.parseInt(p2.getDateCompleted().substring(2,4));
		int year2 = Integer.parseInt(p2.getDateCompleted().substring(5,9));
		
		if (year1 != year2) {
			return year1-year2;
		} else if (month1 != month2) {
			return month1-month2;
		} else if (day1 != day2) {
			return day1-day2;
		} else {
			return 0;
		}
	}
}
