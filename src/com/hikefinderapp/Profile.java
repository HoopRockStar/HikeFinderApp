package com.hikefinderapp;


import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
	
	//DatabaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// when the screen is first created, show the layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		Intent intent = getIntent();
		//String value = intent.getStringExtra("key"); //if it's a string you stored.

		//db = new DatabaseHandler(this);
		
		//MySQLiteHelper db = new MySQLiteHelper(this);
		
		profileListView = getListView();
		
		int id = (int) System.currentTimeMillis();
		
		//UserProfile profile = new UserProfile();
		
		//db.addProfileEntry(profile)
		
		numHikes = (TextView) findViewById(R.id.textView2);
		mileage = (TextView) findViewById(R.id.textView3);
		calories = (TextView) findViewById(R.id.textView4);
		
		//numHikes.setText("Hikes completed ...................... " + hikeResults.size());
		
		int totalMiles = 0;
		/*for(Hike hike : hikeResults)
		{
			totalMiles += hike.getDistance();
		}
		
		mileage.setText("Total miles ............................. " + totalMiles);
		calories.setText("Calories burned ......................... " + totalMiles*80);
		
		
	    m_adapter = new ProfileAdapter(this, R.layout.list_item, hikeResults);
        setListAdapter(m_adapter);
		*/
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
	    Hike item = (Hike)getListAdapter().getItem(position);
	   GlobalDataContainer.setSelectedHike(item);
	   Intent myIntent = new Intent(Profile.this, Completed.class);
	    Profile.this.startActivity(myIntent);
	    //Toast.makeText(this, item.getName() + " selected", Toast.LENGTH_LONG).show();
	  }

}