package com.hikefinderapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
//import com.hikefinderapp.Results.HikesListAsyncTask;
import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Edit_Hike extends Activity {
	
	/*
	 * First Grouping
	 */
	
	//textfields
	TextView nameTextView;
	TextView streetTextView;
	TextView cityTextView;
	TextView stateTextView;
	TextView zipTextView;
	TextView latitudeTextView;
	TextView longitudeTextView;
	TextView trailsTextView;
	TextView descriptionTextView;
	
	//textboxes
	EditText nameText;
	EditText streetText;
	EditText cityText;
	EditText zipText;
	EditText latitudeText;
	EditText longitudeText;
	EditText trailsText;
	EditText descriptionText;
	
	//spinners
	Spinner stateSpinner;
	
	//tablerows
	TableRow nameRow;
	TableRow streetRow;
	TableRow cityRow;
	TableRow stateRow;
	TableRow zipRow;
	TableRow latitudeRow;
	TableRow longitudeRow;
	TableRow trailsRow;
	TableRow descriptionRow;
	
	/*
	 * Second Grouping
	 */
	
	//textfields
	TextView freeParkingTextView;
	TextView bathroomTextView;
	TextView dogsAllowedTextView;
	
	//textboxes
	EditText locationText;
	
	//check boxes
	CheckBox freeParkingCheckBox;
	CheckBox bathroomCheckBox;
	CheckBox dogsAllowedCheckBox;
	
	//tablerow
	TableRow freeParkingRow;
	TableRow bathroomRow;
	TableRow dogsAllowedRow;
	
	
	/*
	 * Third Grouping
	 */
	
	//textfields
	TextView distanceTextView;
	TextView elevationTextView;
	TextView steepHillsTextView;
	
	//return values
	Double distanceValue;
	Integer elevationValue;
	
	//textboxes
	EditText distanceText;
	EditText elevationText;
	
	//checkboxes
	CheckBox steepHillsCheckBox;
	
	//tablerows
	TableRow distanceRow;
	TableRow elevationRow;
	TableRow steepHillsRow;
	
	//initial values
	String stateValue;
	
	/*
	 * Fourth Grouping
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
	
	//Other initial values

	String name;
	String street;
	String city;
	String state;
	String zipString;
	Integer zip;
	String latitudeString;
	Double latitude;
	Double longitude; 
	String trails;
	String description;
	String longitudeString;
	String distanceString;
	String elevationString;
	
	Boolean freeParkingChecked;
	Boolean bathroomChecked;
   	Boolean dogsAllowedChecked;
   	Boolean steepHillsChecked;
   	Boolean loopChecked;
	Boolean oceanViewChecked;
	Boolean waterfallChecked;
	Boolean riverorLakeChecked;
	Boolean historicalFeaturesChecked;
	Boolean geologicalFeaturesChecked;
	Boolean tallTreesChecked;
	Boolean openSpacesChecked;
	
	Long id;
	Hike myHike;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// when the screen is first created, show the layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_hike);
		Intent intent = getIntent();
		id = intent.getLongExtra("id", 0L);
		
		//Recreate Address
		
		/*
		 * First Grouping
		 */
		
		//textfields
		nameTextView = (TextView) findViewById(R.id.textViewName);
		streetTextView = (TextView) findViewById(R.id.textViewStreet);
		latitudeTextView = (TextView) findViewById(R.id.textViewLatitude);
		longitudeTextView = (TextView) findViewById(R.id.textViewLongitude);
		trailsTextView = (TextView) findViewById(R.id.textViewTrails);
		descriptionTextView = (TextView) findViewById(R.id.textViewDescription);
		
		//textboxes
		nameText = (EditText) findViewById(R.id.editTextName);
		streetText = (EditText) findViewById(R.id.editTextStreet);
		latitudeText = (EditText) findViewById(R.id.editTextLatitude);
		longitudeText = (EditText) findViewById(R.id.editTextLongitude);
		trailsText = (EditText) findViewById(R.id.editTextTrails);
		descriptionText = (EditText) findViewById(R.id.editTextDescription);
		
		
		//tablerows
		nameRow = (TableRow) findViewById(R.id.tableRowName);
		streetRow  = (TableRow) findViewById(R.id.tableRowStreet);
		latitudeRow  = (TableRow) findViewById(R.id.tableRowLatitude);
		longitudeRow  = (TableRow) findViewById(R.id.tableRowLongitude);
		trailsRow = (TableRow) findViewById(R.id.tableRowTrails);
		descriptionRow = (TableRow) findViewById(R.id.tableRowDescription);
		
		//make invisible to start
		
		nameTextView.setVisibility(View.GONE);
		streetTextView.setVisibility(View.GONE);
		latitudeTextView.setVisibility(View.GONE);
		longitudeTextView.setVisibility(View.GONE);
		trailsTextView.setVisibility(View.GONE);
		descriptionTextView.setVisibility(View.GONE);
		
		nameText.setVisibility(View.GONE);
		streetText.setVisibility(View.GONE);
		latitudeText.setVisibility(View.GONE);
		longitudeText.setVisibility(View.GONE);
		trailsText.setVisibility(View.GONE);
		descriptionText.setVisibility(View.GONE);
		
		nameRow.setVisibility(View.GONE);
		streetRow.setVisibility(View.GONE);
		latitudeRow.setVisibility(View.GONE);
		trailsRow.setVisibility(View.GONE);
		descriptionRow.setVisibility(View.GONE);
	
		
		/*
		 * Second Grouping
		 */
		
		//textviews
		freeParkingTextView = (TextView) findViewById(R.id.textViewFreeParking);
		bathroomTextView = (TextView) findViewById(R.id.textViewBathroom);
		dogsAllowedTextView = (TextView) findViewById(R.id.textViewDogsAllowed);
		
		//textbox
		//locationText = (EditText) findViewById(R.id.editTextLocation);
		
		// checkboxes
		freeParkingCheckBox = (CheckBox) findViewById(R.id.checkBoxFreeParking);
		bathroomCheckBox = (CheckBox) findViewById(R.id.checkBoxBathroom);
		dogsAllowedCheckBox = (CheckBox) findViewById(R.id.checkBoxDogsAllowed);
		
		// access three out of four tablerows
		freeParkingRow = (TableRow) findViewById(R.id.tableRowfreeParking);
		bathroomRow = (TableRow) findViewById(R.id.tableRowBathroom);
		
		//make invisible to start
		freeParkingTextView.setVisibility(View.GONE);
		bathroomTextView.setVisibility(View.GONE);
		dogsAllowedTextView.setVisibility(View.GONE);
		
		freeParkingCheckBox.setVisibility(View.GONE);
		bathroomCheckBox.setVisibility(View.GONE);
		dogsAllowedCheckBox.setVisibility(View.GONE);
		
		freeParkingRow.setVisibility(View.GONE);
		bathroomRow.setVisibility(View.GONE);
		
		/*
		 * Third Grouping
		 */
		
		//textviews
		distanceTextView = (TextView) findViewById(R.id.textViewDistance);
		elevationTextView = (TextView) findViewById(R.id.textViewElevation);
		steepHillsTextView = (TextView) findViewById(R.id.textViewSteepHills);
		
		//initial values
		distanceValue = 0.0;
		elevationValue = 0;
		stateValue = "";
		trails = "";
		
		//edittext
		distanceText = (EditText) findViewById(R.id.editTextDistance);
		elevationText = (EditText) findViewById(R.id.editTextElevation);
		
		//checkbox
		steepHillsCheckBox = (CheckBox) findViewById(R.id.checkBoxSteepHills);
		
		//tablerows
		distanceRow = (TableRow) findViewById(R.id.tableRowDistance);
		elevationRow = (TableRow) findViewById(R.id.tableRowElevation);
		
		
		//Set Visibility to Invisible
		
		distanceTextView.setVisibility(View.GONE);
		elevationTextView.setVisibility(View.GONE);
		steepHillsTextView.setVisibility(View.GONE);
		
		steepHillsCheckBox.setVisibility(View.GONE);
		
		//distanceSpinner.setVisibility(View.GONE);
		distanceText.setVisibility(View.GONE);
		elevationText.setVisibility(View.GONE);
		
		
		distanceRow.setVisibility(View.GONE);
		elevationRow.setVisibility(View.GONE);
		
		
		/*
		 * Fourth Grouping
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
		
		//Other initial values
		name = "";
		street = "";
		city = "";
		zip = 0;
		latitude = 0.0;
		longitude = 0.0; 
		description = "";
		
		new GetHikeAsyncTask(this).execute();
		
		nameText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				name = nameText.getText().toString();
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				nameText.setError(null);
			}
            
        });
		
		streetText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				street = streetText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				streetText.setError(null);
			}
            
        });
		
		
		latitudeText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				latitudeString = latitudeText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				latitudeText.setError(null);
			}
            
        });
		
		longitudeText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				longitudeString = longitudeText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				longitudeText.setError(null);
			}
            
        });
		
		distanceText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				distanceString = distanceText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				distanceText.setError(null);
			}
            
        });
		
		elevationText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				elevationString = elevationText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				elevationText.setError(null);
			}
            
        });
		
		trailsText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				trails = trailsText.getText().toString();
				trails.replace(",", "\n");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				trailsText.setError(null);
			}
            
        });
		
		descriptionText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				description = descriptionText.getText().toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, 
					int count) {
				descriptionText.setError(null);
			}
            
        });
		
		submitButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
            
            	
            	/*
            	 * Second Grouping
            	 */
            	
            	freeParkingChecked = (freeParkingCheckBox.isChecked()) ? true : false;
            	bathroomChecked = (bathroomCheckBox.isChecked()) ? true : false;
               	dogsAllowedChecked = (dogsAllowedCheckBox.isChecked()) ? true : false;

            	/*
            	 * Third Grouping
            	 */
            	
            	
            	steepHillsChecked = (steepHillsCheckBox.isChecked()) ? true : false;
            	
            	/*
            	 * Fourth Grouping
            	 */
            	
            	loopChecked = (loopCheckBox.isChecked()) ? true : false;
            	oceanViewChecked = (oceanViewCheckBox.isChecked()) ? true : false;
            	waterfallChecked = (waterfallCheckBox.isChecked()) ? true : false;
            	riverorLakeChecked = (riverorLakeCheckBox.isChecked()) ? true : false;
            	historicalFeaturesChecked = (historicalFeaturesCheckBox.isChecked()) ? true : false;
            	geologicalFeaturesChecked = (geologicalFeaturesCheckBox.isChecked()) ? true : false;
            	tallTreesChecked = (tallTreesCheckBox.isChecked()) ? true : false;
            	openSpacesChecked = (openSpacesCheckBox.isChecked()) ? true : false;
		        
            	longitude = Double.parseDouble(longitudeString);
            	distanceValue = Double.parseDouble(distanceString);
            	latitude = Double.parseDouble(latitudeString);
            	elevationValue = Integer.parseInt(elevationString);
            	if (myHike.getFreeParking() != freeParkingChecked) {
            		myHike.setFreeParking(freeParkingChecked);
            	}
        		myHike.setBathroomAvailable(bathroomChecked);
        		myHike.setDogsAllowed(dogsAllowedChecked);
            	myHike.setLoop(loopChecked);
        		myHike.setOceanView(oceanViewChecked);
        		myHike.setWaterfall(waterfallChecked);
        		myHike.setLakeRiverCreek(riverorLakeChecked);
        		myHike.setHistorical(historicalFeaturesChecked);
        		myHike.setGeological(geologicalFeaturesChecked);//geologicalFeaturesCheckBox.setChecked(hike.getGeological());
        		myHike.setTallTrees(tallTreesChecked);
        		myHike.setWildflowers(openSpacesChecked);
            	
            	if (name.length()==0) {
            		if (!nameText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid name for the hike. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		nameText.requestFocus();
            		//nameText.setError("A hike name is required.");
            		nameText.setError(Html.fromHtml("<font color='red'>A hike name is required.</font>"));
            	} else if (street.length()==0) {
            		if (!streetText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid street address for the hike. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		streetText.requestFocus();
            		streetText.setError(Html.fromHtml("<font color='red'>A street address is required.</font>"));
            	} else if (latitude > 90.0 || latitude < -90.0 || latitude == 0.0) {
            		if (!latitudeText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid decimal latitude for the hike. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		latitudeText.requestFocus();
            		latitudeText.setError(Html.fromHtml("<font color='red'>A valid decimal latitude is required.</font>"));
            	} else if (longitude > 180.0 || longitude < -180.0 || longitude == 0.0) {
            		if (!longitudeText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid decimal longitude for the hike. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		longitudeText.requestFocus();
            		longitudeText.setError(Html.fromHtml("<font color='red'>A valid decimal longitude is required.</font>"));
            	} else if (trails.length() == 0) {
            		if (!trailsText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter information about the hiking trails. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		trailsText.requestFocus();
            		trailsText.setError(Html.fromHtml("<font color='red'>Please enter information about the hiking trails.</font>"));
            	} else if (description.length() == 0) {
            		if (!descriptionText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a description of the hike. See Hike Basics.", Toast.LENGTH_LONG).show();
            		}
            		descriptionText.requestFocus();
            		descriptionText.setError(Html.fromHtml("<font color='red'>Please enter a description of the hike.</font>"));
            	} else if (distanceValue > 100 || distanceValue < 1) {
            		if (!distanceText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid distance between 1.0 and 99.0 miles. See Length and Difficulty.", Toast.LENGTH_LONG).show();
            		}
            		distanceText.requestFocus();
            		distanceText.setError(Html.fromHtml("<font color='red'>Please enter a valid distance between 1.0 and 99.0 miles.</font>"));
            	} else if (elevationValue <= 0) {
            		if (!elevationText.isShown()){
            			Toast.makeText(
                                getApplicationContext(),"Please enter a valid elevation in feet. See Length and Difficulty.", Toast.LENGTH_LONG).show();
            		}
            		elevationText.requestFocus();
            		elevationText.setError(Html.fromHtml("<font color='red'>Please enter a valid elevation in feet.</font>"));
            	} else {
            		myHike.setElevation(elevationValue);
            		Toast.makeText(
                            getApplicationContext(), name + " " + street + " " + latitude + " " + longitude, Toast.LENGTH_LONG).show();
            		myHike.setName(name);
            		myHike.setAddress(street);
            		myHike.setLatitude(latitude);
            		myHike.setLongitude(longitude);
            		myHike.setTrails(trails);
            		myHike.setDescription(description);
            		myHike.setDistance(distanceValue);
            		
            		new UpdateHikeAsyncTask(Edit_Hike.this).execute();
            		Intent myIntent = new Intent(Edit_Hike.this, MainActivity.class);
            		Log.d("Done making changes ", "" + myHike.getLongitude());
            		Edit_Hike.this.startActivity(myIntent);
            	}
            }
        });

	}	
	
	public void setViews(Hike hike) {
		/*
		 * First Grouping
		 */
		
		//textboxes
		nameText.setText(hike.getName());
		streetText.setText(hike.getAddress());
		latitudeText.setText(""+hike.getLatitude());
		longitudeText.setText(""+hike.getLongitude());
		trailsText.setText(hike.getTrails());
		descriptionText.setText(hike.getDescription());
		
		/*
		 * Second Grouping
		 */
		
		//check boxes
		freeParkingCheckBox.setChecked(hike.getFreeParking());
		bathroomCheckBox.setChecked(hike.getBathroomAvailable());
		dogsAllowedCheckBox.setChecked(hike.getDogsAllowed());
	
		
		/*
		 * Third Grouping
		 */
		
		distanceText.setText("" + hike.getDistance());
		elevationText.setText("" + hike.getElevation());
		
		steepHillsCheckBox.setChecked(hike.getNoSteepHills());
		
		/*
		 * Fourth Grouping
		 */
		
		loopCheckBox.setChecked(hike.getLoop());
		oceanViewCheckBox.setChecked(hike.getOceanView());
		waterfallCheckBox.setChecked(hike.getWaterfall());
		riverorLakeCheckBox.setChecked(hike.getLakeRiverCreek());
		historicalFeaturesCheckBox.setChecked(hike.getHistorical());
		//geologicalFeaturesCheckBox.setChecked(hike.getGeological());
		geologicalFeaturesCheckBox.setChecked(false);
		tallTreesCheckBox.setChecked(hike.getTallTrees());
		openSpacesCheckBox.setChecked(hike.getWildflowers());
	
	}
	
	
	/**
	* onClick handler for first grouping
	*/
	public void toggle_contents_basic(View v){
	      nameTextView.setVisibility( nameTextView.isShown()
	                          ? View.GONE
	                          : View.VISIBLE );
	      streetTextView.setVisibility( streetTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      latitudeTextView.setVisibility( latitudeTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      longitudeTextView.setVisibility( longitudeTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      trailsTextView.setVisibility( trailsTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      descriptionTextView.setVisibility( descriptionTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      nameText.setVisibility( nameText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      streetText.setVisibility( streetText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      latitudeText.setVisibility( latitudeText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      longitudeText.setVisibility( longitudeText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      trailsText.setVisibility( trailsText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      descriptionText.setVisibility( descriptionText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      nameRow.setVisibility( nameRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      streetRow.setVisibility( streetRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      latitudeRow.setVisibility( latitudeRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      trailsRow.setVisibility( trailsRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      descriptionRow.setVisibility( descriptionRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	}
	
	
	
	/**
	* onClick handler for second grouping
	*/
	public void toggle_contents(View v){
	      
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
	      
	      freeParkingRow.setVisibility( freeParkingRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      bathroomRow.setVisibility( bathroomRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	}
	
	/**
	* onClick handler for third grouping
	*/
	public void toggle_contents_difficulty(View v){
	      distanceTextView.setVisibility( distanceTextView.isShown()
	                          ? View.GONE
	                          : View.VISIBLE );
	      
	      elevationTextView.setVisibility( elevationTextView.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      steepHillsTextView.setVisibility( steepHillsTextView.isShown()
	    		  ? View.GONE
	    		  : View.VISIBLE );
	      
	      steepHillsCheckBox.setVisibility( steepHillsCheckBox.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	     /* distanceSpinner.setVisibility( distanceSpinner.isShown()
                  ? View.GONE
                  : View.VISIBLE );*/
	      
	      distanceText.setVisibility( distanceText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      elevationText.setVisibility( elevationText.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      distanceRow.setVisibility( distanceRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	      elevationRow.setVisibility( elevationRow.isShown()
                  ? View.GONE
                  : View.VISIBLE );
	      
	}
	
	/*
	 * Onclick handler for fourth grouping
	 */
	
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
	
	
	
	private class GetHikeAsyncTask extends AsyncTask<Void, Void, Hike>{
		 Context context;
		 private ProgressDialog dialogue;  
		 public GetHikeAsyncTask(Context context) {
			 this.context = context;
		 }
		 
		 protected void onPreExecute(){
			 super.onPreExecute();
			 Log.d("in hike ", "async class");
			 dialogue = new ProgressDialog(context);
			 dialogue.setMessage("Loading Hike...");
			 dialogue.show();
			 //nothing here
		 }
		  
		 protected Hike doInBackground(Void... unused) {
			 Hike hike = null;
			 try {
				 Hikeendpoint.Builder builder = new Hikeendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				 builder.setApplicationName( "HikeFinder" );
				 Hikeendpoint service = builder.build();
				 hike = service.getHike(id).execute();
				 
			 } catch (Exception e) {
				 Log.d("Could not retrieve Hikes", e.getMessage(), e);
			 }
			 Log.d("Retrieved hike", hike.getName());
			 return hike;
		 }
		 
		 protected void onPostExecute(Hike hike) {
			 // Do something with the result.
			 if(dialogue != null && dialogue.isShowing())
		        {
		            dialogue.dismiss();
		        }
			 myHike = hike;
			 setViews(hike);
			//ArrayList<Hike> _list = (ArrayList<Hike>) hikes.getItems();
			 //ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
			 
			
		 }
	 }
	
	private class UpdateHikeAsyncTask extends AsyncTask<Void, Void, Hike>{
		 Context context;
		 private ProgressDialog dialogue;  
		 public UpdateHikeAsyncTask(Context context) {
			 this.context = context;
		 }
		 
		 protected void onPreExecute(){
			 super.onPreExecute();
			 Log.d("in update hike ", "async class");
			 dialogue = new ProgressDialog(context);
			 dialogue.setMessage("Updating Hike Record...");
			 dialogue.show();
			 //nothing here
		 }
		  
		 protected Hike doInBackground(Void... unused) {
			 Hike result=null;
			 try {
				 
				 Hikeendpoint.Builder builder = new Hikeendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				 builder.setApplicationName( "HikeFinder" );
				 Hikeendpoint service = builder.build();
				 result = service.updateHike(myHike).execute();
				 
			 } catch (Exception e) {
				 Log.d("Could not retrieve Hikes", e.getMessage(), e);
			 }
			 Log.d("Updated hike", result.getName());
			 return result;
		 }
		 
		 protected void onPostExecute(Hike result) {
			 // Do something with the result.
			 if(dialogue != null && dialogue.isShowing())
		        {
		            dialogue.dismiss();
		        }
			//ArrayList<Hike> _list = (ArrayList<Hike>) hikes.getItems();
			 //ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
			 
			
		 }
	}
		

}
