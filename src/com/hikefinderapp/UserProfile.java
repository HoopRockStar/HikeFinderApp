package com.hikefinderapp;

import java.util.Comparator;

public class UserProfile {
	private long id;
	private int completed;
	private String hikeName;
	private String dateCompleted;
	private double distance;
	private double rating;
	private String review;
	private String notes;
	
	public UserProfile(){}
	
	public UserProfile(long id, int completed, String name, String dateCompleted, 
			double distance, double rating, String review, String notes) {
		this.id = id;
		this.completed = completed;
		this.hikeName = name;
		this.dateCompleted = dateCompleted;
		this.distance = distance;
		this.rating = rating;
		this.review = review;
		this.notes = notes;
	}
	
	public long getId() {
		return id;
	}
	
	public int getCompleted() {
		return completed;
	}
	
	public String getHikeName() {
		return hikeName;
	}
	
	public String getDateCompleted() {
		return dateCompleted;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getReview() {
		return review;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setId(long id){
		this.id=id;
	}
	
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	
	public void setHikeName(String name) {
		this.hikeName = name;
	}
	
	public void setDateCompleted(String dateCompleted){
		this.dateCompleted = dateCompleted;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return "Hike Name: " + hikeName + "Completed: " + completed + "Date Completed: " +  dateCompleted + "Distance: " + distance;
	}

	
	
}
