package com.hikefinderapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;


public class Route extends Activity
{
	TextView address;
	ListView trails;
	TextView header;
	TextView stats;
	/// buttons
	Button checklistButton;
	Button mapButton;
	Button completedButton;
	Button editButton;
	
	Hike selectedHike;
	
	String featureString;
	String name;
	Long id;
	double distance;
	int elevation;
	String description;
	String trail;
	String addres;
	double latitude;
	double longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);
		Intent intent = getIntent();
		
		featureString = intent.getStringExtra("featureString");
	    name = intent.getStringExtra("name");
	    id = intent.getLongExtra("id", 1L);
	    distance = intent.getExtras().getDouble("distance");
	    elevation = intent.getExtras().getInt("elevation");
	    description = intent.getStringExtra("description");
	    trail = intent.getStringExtra("trails");
	    addres = intent.getStringExtra("address");
	    latitude = intent.getExtras().getDouble("latitude");
	    longitude = intent.getExtras().getDouble("longitude");
	    
	    //Toast.makeText(getApplicationContext(), "route distance " + distance, Toast.LENGTH_LONG).show();
		
		trails = (ListView) findViewById(R.id.list_trails);
		stats = (TextView) findViewById(R.id.textViewStatistics);
		header = (TextView) findViewById(R.id.textViewName);
		address = (TextView) findViewById(R.id.textViewAddress);
		
		String newTrail;
		newTrail = trail.replace(", ", "\n");
		newTrail = trail.replace(">", "\n");
		String[] trails_list = newTrail.split("\n");
		
		CustomList adapter = new CustomList(Route.this, trails_list);
		trails.setAdapter(adapter);
		
		//trails.setText("Trails :\n" + newTrail);
		stats.setText("Latitude: " + latitude + "\nLongitude: " + longitude +"\n\nDistance: " + distance + " miles\nElevation Gain: " + elevation + " feet\nApproximate Calories: " + ((int) (distance * 80)));
		header.setText(name);
		address.setText(addres);

		checklistButton = (Button) findViewById(R.id.button1);
		editButton = (Button) findViewById(R.id.button2);
		completedButton = (Button) findViewById(R.id.button3);
		
		
		checklistButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
		        	Intent myIntent = new Intent(Route.this, Checklist.class);
		        	myIntent.putExtra("featureString", featureString);
		    	    myIntent.putExtra("name", name);
		    	    myIntent.putExtra("id", id);
		    	    myIntent.putExtra("distance", distance);
		    	    myIntent.putExtra("elevation", elevation);
		    	    myIntent.putExtra("description", description);
		    	    myIntent.putExtra("trails", trail);
		    	    myIntent.putExtra("address", addres);
		    	    myIntent.putExtra("latitude", latitude);
		        	myIntent.putExtra("longitude", longitude);
		        	Route.this.startActivity(myIntent);
            }
        });
		
		
		completedButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
		        	Intent myIntent = new Intent(Route.this, Completed.class);
		        	myIntent.putExtra("featureString", featureString);
		    	    myIntent.putExtra("name", name);
		    	    myIntent.putExtra("id", id);
		    	    myIntent.putExtra("distance", distance);
		    	    myIntent.putExtra("elevation", elevation);
		    	    myIntent.putExtra("description", description);
		    	    myIntent.putExtra("trails", trail);
		    	    myIntent.putExtra("address", addres);
		        	Route.this.startActivity(myIntent);
            }
        });
		
		editButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
		        	Intent myIntent = new Intent(Route.this, Edit_Hike.class);
		    	    myIntent.putExtra("id", id);
		        	Route.this.startActivity(myIntent);
            }
        });
		
	}
}
