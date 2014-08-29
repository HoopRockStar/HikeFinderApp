package com.hikefinderapp;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
 
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;
 
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Collections;

import java.util.ArrayList;
import android.app.ListActivity;

public class Results extends ListActivity
{
	// list view
	ListView resultsListView;
	HashSet<Hike> hikesResult;
	ArrayList<Hike> finalHikes;
	
	// button
	Button submitButton;
	// declare class variables
	//private ArrayList<Hike> hikeResults;
	private Runnable viewParts;
	///private ItemAdapter m_adapter;
	ArrayList<String> queryString;
	String distanceString;
	String elevationString;
	public QueryHike queryHike;
	ItemAdapter m_adapter;
	Double latitude;
	Double longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		queryString = null;
		distanceString = null;
		elevationString = null;
		Intent intent = getIntent();
		hikesResult = new HashSet();
		queryString = intent.getStringArrayListExtra("query");
		if (intent.hasExtra("distance")) {
			distanceString = intent.getStringExtra("distance");
		}
		
		if (intent.hasExtra("elevation")) {
			elevationString = intent.getStringExtra("elevation");
		}
		String query;
		Log.d("queryString size ", ""+queryString.size() );
		if (distanceString != null && queryString != null) {
			for (int i =0; i < queryString.size(); i++ ) {
				query = queryString.get(i);
				queryString.set(i, query += " || " + distanceString);
				Log.d("The combined query", queryString.get(i));
			}
		}
		
		if (queryString.size() == 0 && distanceString != null && elevationString != null) {
			queryString.add(distanceString);
			queryString.add(elevationString);
			Log.d("Query is now ", queryString.get(0));
		} else if (queryString.size() ==0 && distanceString !=null) {
			queryString.add(distanceString);
			Log.d("Query is now ", queryString.get(0));
		} else if (queryString.size() == 0 && elevationString != null) {
			queryString.add(elevationString);
			Log.d("Query is now ", queryString.get(0));
		}
		
		latitude = longitude = null;
		
		if (intent.hasExtra("latitude")) {
			latitude = intent.getExtras().getDouble("latitude");
		}
		
		if (intent.hasExtra("longitude")) {
			longitude = intent.getExtras().getDouble("longitude");
		}
		
		Boolean freeParkingChecked = intent.getExtras().getBoolean("freeParking");
        Boolean bathroomChecked = intent.getExtras().getBoolean("bathroom");
        Boolean dogsAllowedChecked = intent.getExtras().getBoolean("dogs");
        Boolean steepHillsChecked = intent.getExtras().getBoolean("steepHills");
        Boolean loopChecked = intent.getExtras().getBoolean("loop");
        Boolean oceanViewChecked = intent.getExtras().getBoolean("oceanView");
        Boolean waterfallChecked = intent.getExtras().getBoolean("waterfall");
        Boolean riverChecked = intent.getExtras().getBoolean("river");
        Boolean historicalChecked = intent.getExtras().getBoolean("historical");
        Boolean geologicalChecked = intent.getExtras().getBoolean("geological");
        Boolean tallTreesChecked = intent.getExtras().getBoolean("tallTrees");
        Boolean wildflowersChecked = intent.getExtras().getBoolean("wildflowers");
        
        queryHike = new QueryHike(bathroomChecked, freeParkingChecked, dogsAllowedChecked, 
  			  steepHillsChecked, loopChecked, oceanViewChecked, waterfallChecked,
  			  riverChecked, historicalChecked, geologicalChecked,
  			  tallTreesChecked, wildflowersChecked);
		
		//resultsListView = (ListView) findViewById(R.id.);
		resultsListView = getListView();
		submitButton = (Button) findViewById(R.id.submitButton);
		
/*		hikeResults = new ArrayList<Hike>(GlobalDataContainer.getQueryResults());
		
		for (Hike hike : hikeResults) {
			hike.setRating(rateHike(hike, queryHike));
		}
		
		Collections.sort(hikeResults, new HikeComparison());
		
		for (Hike hike : hikeResults) {
			System.out.println(hike.getName() + ": " + hike.getRating());
		}
		
		
		 m_adapter = new ItemAdapter(this, R.layout.list_item, hikeResults);
	        setListAdapter(m_adapter);	   
	        
		
		*/
		Log.d("Query: ", " "+ (queryString != null) + " " + distanceString);
		
		new HikesListAsyncTask(this).execute();
	}
	
	 private class HikesListAsyncTask extends AsyncTask<Void, Void, CollectionResponseHike>{
		 Context context;
		 private ProgressDialog pd;
		  
		 public HikesListAsyncTask(Context context) {
			 this.context = context;
		 }
		 protected void onPreExecute(){
		 super.onPreExecute();
		 pd = new ProgressDialog(context);
		 pd.setMessage("Retrieving Hikes...");
		 pd.show();
		 }
		  
		 protected CollectionResponseHike doInBackground(Void... unused) {
			 CollectionResponseHike hikes = null;
			 try {
				 Hikeendpoint.Builder builder = new Hikeendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				 builder.setApplicationName( "HikeFinder" );
				 Hikeendpoint service = builder.build();
				 //hikes = service.listHike().execute();
				 for (String qString : queryString) {
					 
					 Log.d("Running query: ", qString);
					 
					 hikes = service.listHike().setQueryString(qString).execute();
					 if (hikes.getItems() !=null) {
						 hikesResult.addAll(hikes.getItems());
						 Log.d("got items", " hikes");
					 }
				 }
			 } catch (Exception e) {
				 Log.d("Could not retrieve Hikes", e.getMessage(), e);
			 }
			 return hikes;
		 }
		 
		 protected void onPostExecute(CollectionResponseHike hikes) {
			 pd.dismiss();
			 // Do something with the result.
			//ArrayList<Hike> _list = (ArrayList<Hike>) hikes.getItems();
			 //ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
			 
			 
			 finalHikes = new ArrayList<Hike>(hikesResult);
			 
			 for (Hike hike : finalHikes) {
				 hike.setScore(rateHike(hike, queryHike));
			 }
			 
			 Collections.sort(finalHikes, new HikeComparison());
			if (latitude != null && longitude != null) {
				 for (int i = 0; i < finalHikes.size();i++) {
					 Hike hike = finalHikes.get(i);
					 if (withinRange(hike, latitude, longitude) == false) {
						 Log.d("removing hike ", hike.getName());
						 Log.d("Hike: " + hike.getName(), " Hike Latitude: " + hike.getLatitude() + " Latitude: " + latitude + " Hike Longitude: " + hike.getLongitude() + " Longitude: " + longitude);
						 finalHikes.remove(i);
					 }
				 }
			}
			 
			m_adapter = new ItemAdapter(Results.this, R.layout.list_item, finalHikes);
		    setListAdapter(m_adapter);
		 }
		 
	 }
	
	@Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    Hike h = (Hike)getListAdapter().getItem(position);
	    
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
		
		/*if (h.getGeological()) {
			featuresText += "Of Geographical Interest, ";
		}*/
		
		if (h.getTallTrees()) {
			featuresText += "Tall Trees, ";
		}
		
		if (h.getWildflowers()) {
			featuresText += "Wildflowers in Season, ";
		}
		  
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
		
		if (featuresText.length() > 2) { 
			featuresText = featuresText.substring(0, featuresText.length()-2);
		}
		
	    //Log.d("GlobalDataContainer ", GlobalDataContainer.getSelectedHike().getName());
	    Intent myIntent = new Intent(Results.this, Hike_Description.class);
	    myIntent.putExtra("featureString", featuresText);
	    myIntent.putExtra("name", h.getName());
	    myIntent.putExtra("id", h.getId());
	    myIntent.putExtra("distance", h.getDistance());
	    myIntent.putExtra("elevation", h.getElevation());
	    myIntent.putExtra("description", h.getDescription());
	    myIntent.putExtra("trails", h.getTrails());
	    myIntent.putExtra("address", h.getAddress());
	    myIntent.putExtra("latitude", h.getLatitude());
	    myIntent.putExtra("longitude", h.getLongitude());
	    Results.this.startActivity(myIntent);
	   // Toast.makeText(this, GlobalDataContainer.getSelectedHike().getName() + " selected", Toast.LENGTH_LONG).show();
	  }
	
	private int rateHike(Hike hikeToRate, QueryHike queryHike) {
		int rating=0;
		
		if (queryHike.getBathroomAvailable() == hikeToRate.getBathroomAvailable())
			rating++;
        if (queryHike.getFreeParking() == hikeToRate.getFreeParking())
        	rating++;
        if (queryHike.getDogsAllowed() == hikeToRate.getDogsAllowed())
        	rating++;
        if (queryHike.getNoSteepHills() == hikeToRate.getNoSteepHills())
        	rating++;
        if (queryHike.getLoop() == hikeToRate.getLoop())
        	rating++;
        if (queryHike.getOceanView() == hikeToRate.getOceanView())
        	rating++;
        if (queryHike.getWaterfall() == hikeToRate.getWaterfall())
        	rating++;
        if (queryHike.getLakeRiverCreek() == hikeToRate.getLakeRiverCreek())
        	rating++;
        if (queryHike.getHistorical() == hikeToRate.getHistorical())
        	rating++;
        if (queryHike.getGeological() == hikeToRate.getGeological())
        	rating++;
        if (queryHike.getTallTrees() == hikeToRate.getTallTrees())
        	rating++;
        if (queryHike.getWildflowers() == hikeToRate.getWildflowers())
        	rating++;
		
		
		return rating;
	}
	
	//workaround because of jdo stupidity where can't have ands on multiple columns
	
	public boolean withinRange(Hike hike, double latitude, double longitude) {
		double latitudeDifference = Math.abs(hike.getLatitude()) - Math.abs(latitude);
		double longitudeDifference = Math.abs(hike.getLongitude()) - Math.abs(longitude);
		
		if ((Math.abs(latitudeDifference) <= .5) && (Math.abs(longitudeDifference) <= .5)) {
			
			return true;
		}
		
		return false;
	}
	
	
}
