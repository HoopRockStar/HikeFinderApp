package com.hikefinderapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
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
			month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);
			String todaysDate = ""+ month + "-" + day +"-" + year;
			/*UserProfile(long id, int completed, String name, String dateCompleted, 
					double distance, double rating, String review, String notes)*/
			profile = new UserProfile(id, 0, name, todaysDate, distancia, 0.0, "", "");
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
		
		initializeCompleted(profile);

		///profile = new UserProfile(selectedHike.getId(), 0, selectedHike.getName(), "", selectedHike.getDistance(), 0.0, "", "");

		saveButton = (Button) findViewById(R.id.button1);
		
		saveButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	profile.setRating(rating.getRating());
            	profile.setNotes(notesText.getText().toString());
            	profile.setReview(reviewText.getText().toString());
            	int completedChecked = (completedCheck.isChecked()) ? 1 : 0;
                profile.setCompleted(completedChecked);
                profile.setDistance(distance);
            	
                if (newEntry) {
                	db.addProfileEntry(profile);
                	Log.d("New entry in ", "profile db");
                } else {
                	db.updateUserProfile(profile);
                	Log.d("Updated ", " profile in db");
                }
                
	        	//Intent myIntent = new Intent(Completed.this, Profile.class);
	        	//myIntent.putExtra("key", value); //Optional parameters
	        	//Completed.this.startActivity(myIntent);
            }
        });
		
	}
	
	 
	
	public void initializeCompleted(UserProfile profile) {
		completedCheck.setChecked(profile.getCompleted()==1);
		month = Integer.parseInt(profile.getDateCompleted().substring(0,1)) - 1;
		day = Integer.parseInt(profile.getDateCompleted().substring(2,4));
		year = Integer.parseInt(profile.getDateCompleted().substring(5,9));
		Log.d("Date: ",""+profile.getDateCompleted());
		Log.d("month: " + month, "day: " + day + "year: " + year);
		datePicker.init(year, month, day, null);
		Log.d("Rating", ""+profile.getRating());
		rating = (RatingBar) findViewById(R.id.ratingBar1);
		userRating = (float) profile.getRating();
		rating.setRating(userRating);
		reviewText.setText(profile.getReview());
		notesText.setText(profile.getNotes());
	}
	
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
		year = selectedYear;
		month = selectedMonth;
		day = selectedDay;
		
		// set selected date into textview
		profile.setDateCompleted("" + (month+1) + "-" + day + "-" + year);
		Log.d("month is ", "" + month);
		
		// set selected date into datepicker also
		datePicker.init(year, month, day, null);
		
		}
	};
	
	
	/*public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
        protected Long doInBackground(Context... contexts) {
        	
        	

          Hikeendpoint.Builder endpointBuilder = new Hikeendpoint.Builder(
              AndroidHttp.newCompatibleTransport(),
              new JacksonFactory(),
              new HttpRequestInitializer() {
              public void initialize(HttpRequest httpRequest) { }
              });
      Hikeendpoint endpoint = CloudEndpointUtils.updateBuilder(
      endpointBuilder).build();
      try {
    	  selectedHike = GlobalDataContainer.getSelectedHike();
          
          Boolean completedChecked = (completedCheck.isChecked()) ? true : false;
          selectedHike.setCompleted(completedChecked);
          
          selectedHike.setRating((double)rating.getRating());
          
          //selectedHike.setReview((String)reviewText.getText().toString());
          //selectedHike.setNotes((String)notesText.getText().toString());
    	  
          Hike result = endpoint.updateHike(selectedHike).execute();
          Toast.makeText(
                  getApplicationContext(),"Thank you! The Hike Information Has Been Saved.", Toast.LENGTH_LONG).show();
    	  
      } catch (IOException e) {
        e.printStackTrace();
      }
          return (long) 0;
        }
    }*/
	
	
}
