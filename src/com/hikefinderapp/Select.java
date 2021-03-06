package com.hikefinderapp;

import java.io.IOException;
import java.util.List;

import com.hikefinderapp.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import android.os.Bundle;
import android.widget.ListView;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import android.app.ListActivity;

public class Select extends Activity  implements LocationListener {
	LocationManager locationManager;
	
	/*
	 * First Grouping
	 */
	
	//textfields
	TextView zipTextView;
	TextView freeParkingTextView;
	TextView bathroomTextView;
	TextView dogsAllowedTextView;
	
	//textboxes
	EditText zipText;
	String provider;
	Location location;
	Context context;
	double lat;
	double lng;
	String zip;
	
	//check boxes
	CheckBox freeParkingCheckBox;
	CheckBox bathroomCheckBox;
	CheckBox dogsAllowedCheckBox;
	
	//tablerows
	TableRow zipRow;
	TableRow freeParkingRow;
	TableRow bathroomRow;
	
	
	/*
	 * Second Grouping
	 */
	
	//textfields
	TextView distanceTextView;
	TextView elevationTextView;
	TextView caloriesTextView;
	TextView steepHillsTextView;
	
	//spinners
	Spinner distanceSpinner;
	Spinner elevationSpinner;
	Spinner caloriesSpinner;
	
	//spinner Strings
	String distanceQueryString;
	String elevationQueryString;
	String caloriesQueryString;
	
	//checkboxes
	CheckBox steepHillsCheckBox;
	
	//tablerows
	TableRow distanceRow;
	TableRow elevationRow;
	TableRow caloriesRow;
	
	/*
	 * Third Grouping
	 */
	
	//textfields
	TextView loopTextView;
	TextView oceanViewTextView;
	TextView waterfallTextView;
	TextView riverorLakeTextView;
	TextView historicalFeaturesTextView;
	TextView geologicalFeaturesTextView;
	TextView tallTreesTextView;
	TextView openSpacesTextView;
	
	//checkboxes
	CheckBox loopCheckBox;
	CheckBox oceanViewCheckBox;
	CheckBox waterfallCheckBox;
	CheckBox riverorLakeCheckBox;
	CheckBox historicalFeaturesCheckBox;
	CheckBox geologicalFeaturesCheckBox;
	CheckBox tallTreesCheckBox;
	CheckBox openSpacesCheckBox;
	
	//tablerows
	TableRow loopRow;
	TableRow oceanViewRow;
	TableRow waterfallRow;
	TableRow riverorLakeRow;
	TableRow historicalRow;
	TableRow geologicalRow;
	TableRow tallTreesRow;
	
	/*
	 * Button
	 */
	
	//buttons
	Button submitButton;
	
	//Additional fields
	ArrayList<String> queryString;
	Hike queryHike;
	String zipQuery;
	Geocoder geocoder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// when the screen is first created, show the layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);
		Intent intent = getIntent();
		geocoder = new Geocoder(this, Locale.ENGLISH);
		//String value = intent.getStringExtra("key"); //if it's a string you stored.

		//final DatabaseHandler db = new DatabaseHandler(this);
		
		/*
		 * First Grouping
		 */
		
		//textviews
		zipTextView = (TextView) findViewById(R.id.textViewZipcode);
		freeParkingTextView = (TextView) findViewById(R.id.textViewFreeParking);
		bathroomTextView = (TextView) findViewById(R.id.textViewBathroom);
		dogsAllowedTextView = (TextView) findViewById(R.id.textViewDogsAllowed);
		
		///textbox
		zipText = (EditText) findViewById(R.id.editTextZipcode);
		
		//button
		submitButton = (Button) findViewById(R.id.button1);
		
		// Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria for selecting a location provider
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
	    if (location != null) {
		      System.out.println("Provider " + provider + " has been selected.");
		      lat = (double) (location.getLatitude());
			  lng = (double) (location.getLongitude());
			  Log.d("Original lat and long", ""+lat + " " + lng);
			  
			  try {
		    	  List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
		    	  String zipcode="";
		    	  if(addresses != null) {
		    		  Address returnedAddress = addresses.get(0);
		    		  for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
		    			  zipcode+=returnedAddress.getAddressLine(i).toString();
		    		  }
		    		  zip = zipcode.substring(zipcode.length()-5);
		    		  zipText.setText(zip);
		    	  } else {
		    		  zipText.setText("5-digit zipcode");
		    	  }
		    	  
		    	  
		      } catch (IOException e) {
		    	  // TODO Auto-generated catch block
		    	  e.printStackTrace();
		    	  //myAddress.setText("Canont get Address!");
		      } 

			  
		      onLocationChanged(location);
	   }
	    
	    
	    // Initialize the location fields
	    
	  
		
		// checkboxes
		freeParkingCheckBox = (CheckBox) findViewById(R.id.checkBoxFreeParking);
		bathroomCheckBox = (CheckBox) findViewById(R.id.checkBoxBathroom);
		dogsAllowedCheckBox = (CheckBox) findViewById(R.id.checkBoxDogsAllowed);
		
		// access three out of four tablerows
		zipRow = (TableRow) findViewById(R.id.tableRowZipcode);
		freeParkingRow = (TableRow) findViewById(R.id.tableRowfreeParking);
		bathroomRow = (TableRow) findViewById(R.id.tableRowBathroom);
		
		//make invisible to start
		zipTextView.setVisibility(View.GONE);
		freeParkingTextView.setVisibility(View.GONE);
		bathroomTextView.setVisibility(View.GONE);
		dogsAllowedTextView.setVisibility(View.GONE);
		
		zipText.setVisibility(View.GONE);
		
		freeParkingCheckBox.setVisibility(View.GONE);
		bathroomCheckBox.setVisibility(View.GONE);
		dogsAllowedCheckBox.setVisibility(View.GONE);
		
		zipRow.setVisibility(View.GONE);
		freeParkingRow.setVisibility(View.GONE);
		bathroomRow.setVisibility(View.GONE);
		
		/*
		 * Second Grouping
		 */
		
		//textviews
		distanceTextView = (TextView) findViewById(R.id.textViewDistance);
		elevationTextView = (TextView) findViewById(R.id.textViewElevation);
		caloriesTextView = (TextView) findViewById(R.id.textViewCalories);
		steepHillsTextView = (TextView) findViewById(R.id.textViewSteepHills);
		
		//spinners
		distanceSpinner = (Spinner) findViewById(R.id.spinnerDistance);
		elevationSpinner = (Spinner) findViewById(R.id.spinnerElevation);
		caloriesSpinner = (Spinner) findViewById(R.id.spinnerCalories);
		
		//Strings
		distanceQueryString = null;
		elevationQueryString = null;
		
		//checkbox
		steepHillsCheckBox = (CheckBox) findViewById(R.id.checkBoxSteepHills);
		
		//tablerows
		distanceRow = (TableRow) findViewById(R.id.tableRowDistance);
		elevationRow = (TableRow) findViewById(R.id.tableRowElevation);
		caloriesRow = (TableRow) findViewById(R.id.tableRowCalories);
		
		//Set Visibility to Invisible
		
		distanceTextView.setVisibility(View.GONE);
		elevationTextView.setVisibility(View.GONE);
		caloriesTextView.setVisibility(View.GONE);
		steepHillsTextView.setVisibility(View.GONE);
		
		steepHillsCheckBox.setVisibility(View.GONE);
		
		distanceSpinner.setVisibility(View.GONE);
		elevationSpinner.setVisibility(View.GONE);
		caloriesSpinner.setVisibility(View.GONE);
		
		distanceRow.setVisibility(View.GONE);
		elevationRow.setVisibility(View.GONE);
		caloriesRow.setVisibility(View.GONE);
		
		/*
		 * Third Grouping
		 */
		
		//textviews
		loopTextView = (TextView) findViewById(R.id.textViewLoop);
		waterfallTextView = (TextView) findViewById(R.id.textViewWaterfall);
		oceanViewTextView = (TextView) findViewById(R.id.textViewOceanView);
		riverorLakeTextView = (TextView) findViewById(R.id.textViewRiverorLake);
		historicalFeaturesTextView = (TextView) findViewById(R.id.textViewHistoricalFeatures);
		geologicalFeaturesTextView = (TextView) findViewById(R.id.textViewGeologicalFeatures);
		tallTreesTextView = (TextView) findViewById(R.id.textViewTallTrees);
		openSpacesTextView = (TextView) findViewById(R.id.textViewOpenSpaces);
		
		//checkboxes
		loopCheckBox = (CheckBox) findViewById(R.id.checkBoxLoop);
		waterfallCheckBox = (CheckBox) findViewById(R.id.checkBoxWaterfall);
		oceanViewCheckBox = (CheckBox) findViewById(R.id.checkBoxOceanView);
		historicalFeaturesCheckBox = (CheckBox) findViewById(R.id.checkBoxHistoricalFeatures);
		geologicalFeaturesCheckBox = (CheckBox) findViewById(R.id.checkBoxGeologicalFeatures);
		riverorLakeCheckBox = (CheckBox) findViewById(R.id.checkBoxRiverorLake);
		tallTreesCheckBox = (CheckBox) findViewById(R.id.checkBoxTallTrees);
		openSpacesCheckBox = (CheckBox) findViewById(R.id.checkBoxOpenSpaces);
		
		//tablerows
		loopRow = (TableRow) findViewById(R.id.tableRowLoop);
		waterfallRow = (TableRow) findViewById(R.id.tableRowWaterfall);
		oceanViewRow = (TableRow) findViewById(R.id.tableRowOceanView);
		riverorLakeRow = (TableRow) findViewById(R.id.tableRowRiverorLake);
		historicalRow = (TableRow) findViewById(R.id.tableRowHistoricalFeatures);
		geologicalRow = (TableRow) findViewById(R.id.tableRowGeologicalFeatures);
		tallTreesRow = (TableRow) findViewById(R.id.tableRowTallTrees);
	
		//set visibility to invisible
		loopTextView.setVisibility(View.GONE);
		waterfallTextView.setVisibility(View.GONE);
		oceanViewTextView.setVisibility(View.GONE);
		riverorLakeTextView.setVisibility(View.GONE);
		historicalFeaturesTextView.setVisibility(View.GONE);
		geologicalFeaturesTextView.setVisibility(View.GONE);
		riverorLakeTextView.setVisibility(View.GONE);
		tallTreesTextView.setVisibility(View.GONE);
		openSpacesTextView.setVisibility(View.GONE);
		
		loopCheckBox.setVisibility(View.GONE);
		waterfallCheckBox.setVisibility(View.GONE);
		oceanViewCheckBox.setVisibility(View.GONE);
		riverorLakeCheckBox.setVisibility(View.GONE);
		historicalFeaturesCheckBox.setVisibility(View.GONE);
		geologicalFeaturesCheckBox.setVisibility(View.GONE);
		riverorLakeCheckBox.setVisibility(View.GONE);
		tallTreesCheckBox.setVisibility(View.GONE);
		openSpacesCheckBox.setVisibility(View.GONE);
		
		loopRow.setVisibility(View.GONE);
		waterfallRow.setVisibility(View.GONE);
		oceanViewRow.setVisibility(View.GONE);
		riverorLakeRow.setVisibility(View.GONE);
		historicalRow.setVisibility(View.GONE);
		geologicalRow.setVisibility(View.GONE);
		tallTreesRow.setVisibility(View.GONE);
		
		/*
		 * Button
		 */
		
		//buttons
		submitButton = (Button) findViewById(R.id.button1);
		
		submitButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	queryString = new ArrayList<String>();
            	
            	/*
            	 * First Grouping
            	 */
            	
            	//textboxes
            	if (zipText.getText().toString().length() < 5) {
            		zipQuery = null;
            	} else if (!zipText.getText().toString().equals(zip)){
            		List<Address> addresses;
					try {
						addresses = geocoder.getFromLocationName(zipText.getText().toString(), 1);
						if (addresses != null && !addresses.isEmpty()) {
	            		    Address address = addresses.get(0);
	            		    lat = address.getLatitude();
	            		    lng = address.getLongitude();
	            		    Log.d("Extracting new lat and long: ", "" + lat + " " + lng);
	            		}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	} else {
            		//leave lat and lng as they are
            	}
            	
            	
            	//check boxes
            	Boolean freeParkingChecked = (freeParkingCheckBox.isChecked()) ? true : false;
            	if (freeParkingChecked == true) {
            		queryString.add("freeParking == " + freeParkingChecked);
            	}
            	
            	Boolean bathroomChecked = (bathroomCheckBox.isChecked()) ? true : false;
            	
            	if (bathroomChecked == true) {
            		queryString.add("bathroomAvailable == " + bathroomChecked);
            	}
            	
            	Boolean dogsAllowedChecked = (dogsAllowedCheckBox.isChecked()) ? true : false;
            	
            	if (dogsAllowedChecked == true) {
            		queryString.add("dogsAllowed == " + dogsAllowedChecked); 
            	}
            	
            	
            	/*
            	 * Second Grouping
            	 */
            	
            	//spinners
            	Spinner distanceSpinner;
            	Spinner elevationSpinner;
            	Spinner caloriesSpinner;
            	
            	//checkboxes
            	Boolean steepHillsChecked = (steepHillsCheckBox.isChecked()) ? true : false;
            	
            	if (steepHillsChecked == true) {
            		queryString.add("noSteepHills == " + steepHillsChecked); 
            	}
            	
            	/*
            	 * Third Grouping
            	 */
            	
            	//checkboxes
            	Boolean loopChecked = (loopCheckBox.isChecked()) ? true : false;
            	
            	if (loopChecked == true) {
            		queryString.add("loop == " + loopChecked);   
            	}
            	
            	Boolean oceanViewChecked = (oceanViewCheckBox.isChecked()) ? true : false;
            	
            	if (oceanViewChecked == true) {
            		queryString.add("oceanView == " + oceanViewChecked);  
            	}
            	
            	Boolean waterfallChecked = (waterfallCheckBox.isChecked()) ? true : false;
            	
            	if (waterfallChecked == true) {
            		queryString.add("waterfall == " + waterfallChecked); 
            	}
            	
            	Boolean riverorLakeChecked = (riverorLakeCheckBox.isChecked()) ? true : false;
            	
            	if (riverorLakeChecked == true) {
            		queryString.add("lakeRiverCreek == " + riverorLakeChecked);
            	}
            	
            	Boolean historicalFeaturesChecked = (historicalFeaturesCheckBox.isChecked()) ? true : false;
            	
            	if (historicalFeaturesChecked == true) {
            		queryString.add("historical == " + historicalFeaturesChecked);
            	}
            	
            	Boolean geologicalFeaturesChecked = (geologicalFeaturesCheckBox.isChecked()) ? true : false;
            	
            	if (geologicalFeaturesChecked == true) {
            		queryString.add("geological == " + geologicalFeaturesChecked);
            	}
            	
            	Boolean tallTreesChecked = (tallTreesCheckBox.isChecked()) ? true : false;
            	
            	if (tallTreesChecked == true) {
            		queryString.add("tallTrees == " + tallTreesChecked); 
            	}
            	
            	Boolean openSpacesChecked = (openSpacesCheckBox.isChecked()) ? true : false;
            	
            	if (openSpacesChecked == true) {
            		queryString.add("wildflowers == " + openSpacesChecked);
            	}
             		
		        Intent myIntent = new Intent(Select.this, Results.class);
		      
		        myIntent.putStringArrayListExtra("query", queryString);
		        
		        myIntent.putExtra("distance", distanceQueryString);
		        myIntent.putExtra("elevation", elevationQueryString);
		        String nullQuery = "" + (queryString==null);
		        myIntent.putExtra("latitude", lat);
		        myIntent.putExtra("longitude",  lng);
		        
		        //Toast.makeText(getApplicationContext(), nullQuery, Toast.LENGTH_LONG).show();
		        
		        
		        myIntent.putExtra("freeParking", freeParkingChecked);
		        myIntent.putExtra("bathroom", bathroomChecked);
		        myIntent.putExtra("dogs", dogsAllowedChecked);
		        myIntent.putExtra("steepHills", steepHillsChecked);
		        myIntent.putExtra("loop", loopChecked);
		        myIntent.putExtra("oceanView", oceanViewChecked);
		        myIntent.putExtra("waterfall", waterfallChecked);
		        myIntent.putExtra("river", riverorLakeChecked);
		        myIntent.putExtra("historical", historicalFeaturesChecked);
		        myIntent.putExtra("geological", geologicalFeaturesChecked);
		        myIntent.putExtra("tallTrees", tallTreesChecked);
		        myIntent.putExtra("wildflowers", openSpacesChecked);
		        if (queryString.size() < 1 && distanceQueryString == null && elevationQueryString == null) {
		        	Toast.makeText(getApplicationContext(), "Please enter some search criteria.", Toast.LENGTH_LONG).show();
		        } else {
		        	Select.this.startActivity(myIntent);
		        }
            }
        });
		
		distanceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
                 
                if (position == 1)
                	distanceQueryString = "(distance >= 1.0 && distance <= 3.0)";
                else if (position == 2)
                	distanceQueryString = "(distance >= 3.0 && distance <= 6.0)";
                else if (position == 3 )
                	distanceQueryString = "(distance >= 6.0 && distance <= 8.0)";
                else if (position == 4)
                	distanceQueryString = "(distance >= 8.0 && distance <= 10.0)";
                else if (position == 5)
                	distanceQueryString = "(distance >= 10.0 && distance <= 12.0)";
                else if (position == 6)
                	distanceQueryString = "(distance >= 12.0 && distance <= 15.0)";
                else if (position == 7)
                	distanceQueryString = "(distance >= 15.0)";
                 
                //Toast.makeText(getApplicationContext(),distanceQueryString, Toast.LENGTH_LONG).show();
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
 
        });	
		
        elevationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
                 
                if (position == 1)
                	elevationQueryString = "(elevation >= 0 && elevation <= 500)";
                else if (position == 2)
                	elevationQueryString = "(elevation >= 500 && elevation <= 1000)";
                else if (position == 3 )
                	elevationQueryString = "(elevation >= 1000 && elevation <= 2000)";
                else if (position == 4)
                	elevationQueryString = "(elevation >= 2000 && elevation <= 3000)";
                else if (position == 5)
                	elevationQueryString = "(elevation >= 3000 && elevation <= 4000)";
                else if (position == 6)
                	elevationQueryString = "(elevation >= 4000 && elevation <= 5000)";
                else if (position == 7)
                	elevationQueryString = "(elevation >= 5000)";
                
            }
		
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
 
        });	
        
       caloriesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
                 
                if (position == 1) //0-250 calories
                	distanceQueryString = "(distance >= 1.0 && distance <= 3.0)";
                else if (position == 2)//250-500 calories
                	distanceQueryString = "(distance >= 3.0 && distance <= 6.0)";
                else if (position == 3 )//500-750 calories
                	distanceQueryString = "(distance >= 6.0 && distance <= 10.0)";
                else if (position == 4)//750-1000 calories
                	distanceQueryString = "(distance >= 10.0 && distance <= 15.0)";
                else if (position == 5)//1000 or above
                	distanceQueryString = "(distance >= 15.0)";         
            }
		
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
 
        });	
       
	
	}
	
	
	/**
	* onClick handler for first grouping
	*/
	public void toggle_contents(View v){
	      zipText.setVisibility( zipText.isShown()
	                          ? View.GONE
	                          : View.VISIBLE );
	      zipTextView.setVisibility( zipTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      freeParkingCheckBox.setVisibility( freeParkingCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      bathroomCheckBox.setVisibility( bathroomCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      dogsAllowedCheckBox.setVisibility( dogsAllowedCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      freeParkingTextView.setVisibility( freeParkingTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      bathroomTextView.setVisibility( bathroomTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      dogsAllowedTextView.setVisibility( dogsAllowedTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      zipRow.setVisibility( zipRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      freeParkingRow.setVisibility( freeParkingRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      bathroomRow.setVisibility( bathroomRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	}
	
	/**
	* onClick handler for second grouping
	*/
	public void toggle_contents_difficulty(View v){
	      distanceTextView.setVisibility( distanceTextView.isShown()
	                          ? View.GONE
	                          : View.VISIBLE );
	      
	      elevationTextView.setVisibility( elevationTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      caloriesTextView.setVisibility( caloriesTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      steepHillsTextView.setVisibility( steepHillsTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      steepHillsCheckBox.setVisibility( steepHillsCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      distanceSpinner.setVisibility( distanceSpinner.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      elevationSpinner.setVisibility( elevationSpinner.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      caloriesSpinner.setVisibility( caloriesSpinner.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      distanceRow.setVisibility( distanceRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      elevationRow.setVisibility( elevationRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      caloriesRow.setVisibility( caloriesRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	}
	
	public void toggle_contents_features(View v){
	      loopTextView.setVisibility( loopTextView.isShown()
	                          ? View.GONE
	                          : View.VISIBLE );
	      
	      waterfallTextView.setVisibility( waterfallTextView.isShown()
                ? View.GONE
                : View.VISIBLE );
	      
	      oceanViewTextView.setVisibility( oceanViewTextView.isShown()
                ? View.GONE
                : View.VISIBLE );
	      
	      historicalFeaturesTextView.setVisibility( historicalFeaturesTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      geologicalFeaturesTextView.setVisibility( geologicalFeaturesTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      riverorLakeTextView.setVisibility( riverorLakeTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      tallTreesTextView.setVisibility( tallTreesTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      openSpacesTextView.setVisibility( openSpacesTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      loopCheckBox.setVisibility( loopCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );

		  waterfallCheckBox.setVisibility( waterfallCheckBox.isShown()
			    ? View.GONE
			    : View.VISIBLE );
			
		  oceanViewCheckBox.setVisibility( oceanViewCheckBox.isShown()
			    ? View.GONE
			    : View.VISIBLE );
			
		  historicalFeaturesCheckBox.setVisibility( historicalFeaturesCheckBox.isShown()
				  ? View.GONE
				  : View.VISIBLE );
			
		  geologicalFeaturesCheckBox.setVisibility( geologicalFeaturesCheckBox.isShown()
				  ? View.GONE
				  : View.VISIBLE );
			
		  riverorLakeCheckBox.setVisibility( riverorLakeCheckBox.isShown()
				  ? View.GONE
				  : View.VISIBLE );
			
		  tallTreesCheckBox.setVisibility( tallTreesCheckBox.isShown()
				  ? View.GONE
				  : View.VISIBLE );
			
		  openSpacesCheckBox.setVisibility( openSpacesCheckBox.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  loopRow.setVisibility( loopRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  waterfallRow.setVisibility( waterfallRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  oceanViewRow.setVisibility( oceanViewRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  historicalRow.setVisibility( historicalRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
	      
		  geologicalRow.setVisibility( geologicalRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  riverorLakeRow.setVisibility( riverorLakeRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
		  
		  tallTreesRow.setVisibility( tallTreesRow.isShown()
				  ? View.GONE
				  : View.VISIBLE );
	}
	
	/* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	    locationManager.removeUpdates(this);
	    
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Disabled provider " + provider,
		        Toast.LENGTH_SHORT).show();	
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
		        Toast.LENGTH_SHORT).show();	
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}



}
