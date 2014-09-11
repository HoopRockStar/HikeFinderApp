package com.hikefinderapp;

import java.util.Collections;
import java.util.Comparator;

import android.util.Log;

public class ProfileComparison implements Comparator<UserProfile>{
	int month1;
	int day1;
	int year1;
	
	int month2;
	int day2;
	int year2;
	
	@Override
	public int compare(UserProfile p1, UserProfile p2) {		
		parseDate(p1, month1, day1, year1);
		parseDate(p2, month2, day2, year2);
		
		/*int month1 = Integer.parseInt(p1.getDateCompleted().substring(0,1)) - 1;
		int day1 = Integer.parseInt(p1.getDateCompleted().substring(2,4));
		int year1 = Integer.parseInt(p1.getDateCompleted().substring(5,9));
		
		int month2 = Integer.parseInt(p2.getDateCompleted().substring(0,1)) - 1;
		int day2 = Integer.parseInt(p2.getDateCompleted().substring(2,4));
		int year2 = Integer.parseInt(p2.getDateCompleted().substring(5,9));*/
		
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
	
	public void parseDate(UserProfile p, int month, int day, int year) {
		String profileDate = p.getDateCompleted().trim();
		if(profileDate.length()==8) {
			Log.d("date length is eight", profileDate);
			Log.d("Profile date is ", profileDate);
			month = Integer.parseInt(profileDate.substring(0,1));
			day = Integer.parseInt(profileDate.substring(2,3));
			year = Integer.parseInt(profileDate.substring(4,8));
		} else if (profileDate.length()==9 && profileDate.substring(1,2).equals("-")){
			Log.d("date length ", "is nine");
			Log.d("Profile date is ", profileDate);
			month = Integer.parseInt(profileDate.substring(0,1));
			day = Integer.parseInt(profileDate.substring(2,4));
			year = Integer.parseInt(profileDate.substring(5,9));
		} else if (profileDate.length()==9){
			Log.d("date length ", "is nine");
			Log.d("Profile date is ", profileDate);
			month = Integer.parseInt(profileDate.substring(0,2));
			day = Integer.parseInt(profileDate.substring(3,4));
			year = Integer.parseInt(profileDate.substring(5,9));
		} else {
			Log.d("date length ", "is ten");
			Log.d("Profile date is ", profileDate);
			month = Integer.parseInt(profileDate.substring(0,2));
			day = Integer.parseInt(profileDate.substring(3,5));
			year = Integer.parseInt(profileDate.substring(6,10));
		}
	}
}
