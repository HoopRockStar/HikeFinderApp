package com.hikefinderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

public class Hike_Description extends Activity{
	
	Hike selectedHike;
	
	TextView tv4;
	TextView tv2;
	TextView tv5;
	TextView tv6;
	
	Button selectHike;
	ImageButton mapButton;
	
	String featureString;
	String name;
	Long id;
	double distance;
	int elevation;
	String description;
	String trails;
	String address;
	double latitude;
	double longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// when the screen is first created, show the layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hike_description);
		Intent intent = getIntent();
		//String value = intent.getStringExtra("key"); //if it's a string you stored.
		
		featureString = intent.getStringExtra("featureString");
	    name = intent.getStringExtra("name");
	    id = intent.getLongExtra("id", 1L);
	    distance = intent.getExtras().getDouble("distance");
	    elevation = intent.getExtras().getInt("elevation");
	    description = intent.getStringExtra("description");
	    trails = intent.getStringExtra("trails");
	    address = intent.getStringExtra("address");
	    latitude = intent.getExtras().getDouble("latitude");
	    longitude = intent.getExtras().getDouble("longitude");
		
		tv6 = (TextView) findViewById(R.id.textView6);
		tv6.setText(name);
		
		tv4 = (TextView) findViewById(R.id.textView4);
		tv4.setText(featureString);

		tv2 = (TextView) findViewById(R.id.textView2);
		tv5 = (TextView) findViewById(R.id.textView5);
		tv5.setText(description);
		
		selectHike = (Button) findViewById(R.id.button1);
		mapButton = (ImageButton) findViewById(R.id.imageButtonMap);
		
		selectHike.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
        	Intent myIntent = new Intent(Hike_Description.this, Route.class);
        	myIntent.putExtra("featureString", featureString);
    	    myIntent.putExtra("name", name);
    	    myIntent.putExtra("id", id);
    	    myIntent.putExtra("distance", distance);
    	    myIntent.putExtra("elevation", elevation);
    	    myIntent.putExtra("description", description);
    	    myIntent.putExtra("trails", trails);
    	    myIntent.putExtra("address", address);
    	    myIntent.putExtra("latitude", latitude);
        	myIntent.putExtra("longitude", longitude);
        	Hike_Description.this.startActivity(myIntent);
        }
    });
		
		mapButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
        	Intent myIntent = new Intent(Hike_Description.this, Display_Map.class);
        	myIntent.putExtra("name", name);
        	myIntent.putExtra("latitude", latitude);
        	myIntent.putExtra("longitude", longitude);
        	Hike_Description.this.startActivity(myIntent);
        }
    });
	
	}
	
	public String getFeatures(Hike h) {
		String featuresText = "";
		
		if (h.getLoop()) {
			featuresText += "Loop, ";
		}
		
		if (h.getOceanView()) {
			featuresText += "Ocean View, ";
		}
		 
		if (h.getWaterfall()) {
			featuresText += "Waterfall, ";
		}
		
		if (h.getLakeRiverCreek()) {
			featuresText += "River/Lake/Creek, ";
		}
		
		if (h.getHistorical()) {
			featuresText += "Of Historical Interest, ";
		}
		
		/*if (h.getGeoglogical()) {
			featuresText += "Of Geographical Interest, ";
		}*/
		
		if (h.getTallTrees()) {
			featuresText += "Tall Trees, ";
		}
		
		if (h.getWildflowers()) {
			featuresText += "Wildflowers in Season, ";
		}
		 //h.getNoBikes()
		  
		if (h.getBathroomAvailable()) {
			featuresText += "Public Restrooms, ";
		}
		
		if (h.getFreeParking()) {
			featuresText += "Free Parking, ";
		}
		  
		if (h.getNoSteepHills()) {
			featuresText += "No Steep Hills, ";
		}
		
		if (h.getDogsAllowed()) {
			featuresText += "Dog-Friendly, ";
		}
		
		featuresText = featuresText.substring(0, featuresText.length()-2);
		return featuresText;
	}

}






