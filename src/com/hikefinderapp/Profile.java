package com.hikefinderapp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

public class Profile extends ListActivity{
	Button homeButton;
	ListView profileListView;
	TextView numHikes;
	TextView mileage;
	TextView calories;
	
	private ArrayList<Hike> hikeResults;
	private Runnable viewParts;
	private ProfileAdapter m_adapter;
	
	MySQLiteHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		Intent intent = getIntent();
		//String value = intent.getStringExtra("key"); //if it's a string you stored.

		db = new MySQLiteHelper(this);
		profileListView = getListView();
		
		List<UserProfile> profiles;
		
		profiles = db.getAllProfileEntries();
		
		//db.addProfileEntry(profile)
		Collections.sort(profiles, new ProfileComparison());
		numHikes = (TextView) findViewById(R.id.textView2);
		mileage = (TextView) findViewById(R.id.textView3);
		calories = (TextView) findViewById(R.id.textView4);
		
		numHikes.setText("Hikes completed ...................... " + profiles.size());
		
		int totalMiles = 0;
		for(UserProfile p : profiles)
		{
			totalMiles += p.getDistance();
		}
		
		mileage.setText("Total miles ................................. " + totalMiles);
		calories.setText("Calories burned ........................ " + totalMiles*80);
		
		ArrayList<UserProfile> p = new ArrayList<UserProfile>(profiles);
		
	    m_adapter = new ProfileAdapter(this, R.layout.list_item, p);
        setListAdapter(m_adapter);
		
		homeButton = (Button) findViewById(R.id.retrn);
		
		homeButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(Profile.this, MainActivity.class);
	        	//myIntent.putExtra("key", value); //Optional parameters
	        	Profile.this.startActivity(myIntent);
            }
        });
	}
	
	@Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	   UserProfile item = (UserProfile)getListAdapter().getItem(position);
	   
	   Intent myIntent = new Intent(Profile.this, Completed.class);
	   myIntent.putExtra("id", item.getId());
	   Profile.this.startActivity(myIntent);
	   Log.d("Name of selected hike: ", item.getHikeName());
	    //Toast.makeText(this, item.getName() + " selected", Toast.LENGTH_LONG).show();
	  }

}