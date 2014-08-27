package com.hikefinderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Checklist  extends Activity{
	Button backButton;
	
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
		setContentView(R.layout.checklist);
		Intent intent = getIntent();
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
		//String value = intent.getStringExtra("key"); //if it's a string you stored.
		backButton = (Button) findViewById(R.id.button1);
		
		backButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(Checklist.this, Route.class);
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
	        	Checklist.this.startActivity(myIntent);
            }
        });
		
	}

}
