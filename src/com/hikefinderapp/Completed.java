package com.hikefinderapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

public class Completed  extends Activity{
	
	static final int DATE_PICKER_ID = 1111; 

	CheckBox completedCheck;
	
	RatingBar rating;
	
	EditText reviewText;
	EditText notesText;
	
	Button saveButton;
	
	Hike selectedHike;
	
	private int year;
	private int month;
	private int day;
	
	String date;
	double distance;
	float userRating;
	String review;
	String notes;
	
	DatePicker datePicker;
	
	MySQLiteHelper db;
	UserProfile profile;
	//DatabaseHandler db;
	
	String featureString;
	String name;
	Long id;
	double distancia;
	int elevation;
	String description;
	String trails;
	String address;
	boolean newEntry;
	boolean completedChecked;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.completed);
		Intent intent = getIntent();
		//String value = intent.getStringExtra("key"); //if it's a string you stored.

		db = new MySQLiteHelper(this);
		//Toast.makeText(getApplicationContext(), "completed distance " + distancia, Toast.LENGTH_LONG).show();
		
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		completedCheck = (CheckBox) findViewById(R.id.checkBox1);
		
		featureString = intent.getStringExtra("featureString");
	    name = intent.getStringExtra("name");
	    id = intent.getLongExtra("id", 1L);
	    distance = intent.getExtras().getDouble("distance");
	    elevation = intent.getExtras().getInt("elevation");
	    description = intent.getStringExtra("description");
	    trails = intent.getStringExtra("trails");
	    address = intent.getStringExtra("address");
  
		
		if (db.getProfileEntry(id)==null) {	
			Log.d("Entry ", "doesn't exist");
			newEntry = true;
			final Calendar calendar = Calendar.getInstance(); 
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			String todaysDate = ""+ month + "-" + day +"-" + year;
			profile = new UserProfile(id, 0, name, todaysDate, distance, 0.0, "", "");
		} else {
			Log.d("Entry ", "exists");
			newEntry = false;
			profile = db.getProfileEntry(id);
		}
		
		if (profile.getCompleted() > 0) {
			completedChecked = true;
		} else {
			completedChecked = false;
		}
		completedCheck.setChecked(completedChecked);
		reviewText = (EditText) findViewById(R.id.editText1);
		notesText = (EditText) findViewById(R.id.editText2);
		
		rating = (RatingBar) findViewById(R.id.ratingBar1);
		userRating = (float) profile.getRating();
		rating.setRating(userRating);
		reviewText.setText(profile.getReview());
		notesText.setText(profile.getNotes());
		
		parseDate(profile.getDateCompleted());
		
        datePicker.init(year, month, day, new OnDateChangedListener(){

           @Override
           public void onDateChanged(DatePicker view, 
             int yearOf, int monthOf,int dayOf) {
            month = monthOf;
            day = dayOf;
            year = yearOf;
            
            /*Toast.makeText(getApplicationContext(),
              "Year: " + year + "\n" +
              "Month of Year: " + month + "\n" +
              "Day of Month: " + day, Toast.LENGTH_LONG).show();*/
            
            
           }});
        
        saveButton = (Button) findViewById(R.id.button1);
		
		saveButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	profile.setRating(rating.getRating());
            	profile.setNotes(notesText.getText().toString());
            	profile.setReview(reviewText.getText().toString());
            	int completedChecked = (completedCheck.isChecked()) ? 1 : 0;
                profile.setCompleted(completedChecked);
                profile.setDateCompleted("" + month + "-" + day + "-" + year);
                //profile.setDistance(distance);
                System.out.println("Date entered: " + profile.getDateCompleted());
                if (newEntry) {
                	db.addProfileEntry(profile);
                	Log.d("New entry in ", "profile db");
                } else {
                	db.updateUserProfile(profile);
                	Log.d("Updated ", " profile in db" + profile.toString());
                }
                
	        	Intent myIntent = new Intent(Completed.this, Profile.class);
	        	//Toast.makeText(getApplicationContext(), 
	            //        "The Information Has Been Saved" + profile.toString(), Toast.LENGTH_LONG).show();//Optional parameters
	        	Completed.this.startActivity(myIntent);
            }
        });
		
	}
        
	public void parseDate(String profileDate) {
		profileDate = profileDate.trim();
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
		Log.d("Parsing date: ", "month: " + month + "day: " + day + "year: " + year);
		
	}
}