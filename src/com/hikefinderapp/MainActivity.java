package com.hikefinderapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import java.io.IOException;
import java.util.Date;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;
import com.hikefinderapp.R;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;


/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for more details.
 */
public class MainActivity extends Activity {
	
	Button newHikeButton;
	Button createHikeButton;
	Button profileButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Start up RegisterActivity right away
		//Intent intent = new Intent(this, RegisterActivity.class);
		//startActivity(intent);
		// Since this is just a wrapper to start the main activity,
		// finish it after launching RegisterActivity
		//finish();
		
		newHikeButton = (Button) findViewById(R.id.button1);
		profileButton = (Button) findViewById(R.id.button2);
		createHikeButton = (Button) findViewById(R.id.button3);
		
		newHikeButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(MainActivity.this, Select.class);
	        	//myIntent.putExtra("key", value); //Optional parameters
	        	MainActivity.this.startActivity(myIntent);
            }
        });
		
		profileButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(MainActivity.this, Display_Map.class);
	        	MainActivity.this.startActivity(myIntent);
            }
        });
		
		createHikeButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(MainActivity.this, Add_Hike.class);
	        	//myIntent.putExtra("key", value); //Optional parameters
	        	MainActivity.this.startActivity(myIntent);
            }
        });
		
		//new EndpointsTask().execute(getApplicationContext());

	}
	
	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
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
    	  /*Long id = (Long) System.currentTimeMillis();
          Hike purisima = new Hike().setDescription("This lovely hike through the mostly second-generation redwoods, also features a variety of plant and tree species, a flowing creek and views of Half Moon Bay. This place is beautiful and somewhat off the beaten path.");
          purisima.setId(id);
          purisima.setName("Purisima Creek");
          purisima.setAddress("2805 Purisima Creek Rd, Half Moon Bay, CA 94019");
          purisima.setBathroomAvailable(true);
          purisima.setCompleted(false);
          purisima.setDistance(7.0);
          purisima.setDogsAllowed(false);
          purisima.setElevation(1000);
          purisima.setFreeParking(true);
          //lochlomond.setGeoglogical(false);
          purisima.setGeological(false);
          purisima.setHistorical(false);
          purisima.setLakeRiverCreek(true);
          purisima.setLatitude(37.437514);
          purisima.setLongitude(-122.370632);
          purisima.setLoop(true);
          purisima.setNoBikes(true);
          purisima.setNoSteepHills(false);
          purisima.setNotes("");
          purisima.setOceanView(true);
          purisima.setRating(0.0);
          purisima.setReview("");
          purisima.setTallTrees(true);
          purisima.setTrails("Harkins Ridge Trail\nRight on Craig Britton Trail\nRight on Purisima Creek Trail");
          purisima.setWaterfall(false);
          purisima.setWildflowers(true);
          Hike result = endpoint.insertHike(purisima).execute();
          Log.d("Entered: ", result.getName());*/
    	  
    	/*  Hike butanoCandelabra = new Hike().setDescription("Butano State Park is a lesser-known gem located approximately 40 minutes up the coast from Santa Cruz in Pescadero. This hike features lovely ocean views and a spectacular old-growth redwood tree called the Candelabra Tree because of its unique shape.");
          butanoCandelabra.setId(2L);
          butanoCandelabra.setName("Butano State Park Candelabra Trail");
          butanoCandelabra.setAddress("1500 Cloverdale Rd, Pescadero, CA 94060");
          butanoCandelabra.setBathroomAvailable(false);
          butanoCandelabra.setCompleted(false);
          butanoCandelabra.setDistance(8.2);
          butanoCandelabra.setDogsAllowed(false);
          butanoCandelabra.setElevation(1600);
          butanoCandelabra.setFreeParking(true);
          //butanoCandelabra.setGeoglogical(false);
          butanoCandelabra.setGeological(false);
          butanoCandelabra.setHistorical(false);
          butanoCandelabra.setLakeRiverCreek(false);
          butanoCandelabra.setLatitude(37.213333);
          butanoCandelabra.setLongitude(-122.309722);
          butanoCandelabra.setLoop(true);
          butanoCandelabra.setNoBikes(true);
          butanoCandelabra.setNoSteepHills(false);
          butanoCandelabra.setNotes("");
          butanoCandelabra.setOceanView(true);
          butanoCandelabra.setRating(0.0);
          butanoCandelabra.setReview("");
          butanoCandelabra.setTallTrees(true);
          butanoCandelabra.setTrails("Park at the pullout along Gazos Creek Road (not at the main entrance)\nClimb up the Candelabra Trail\nTake a short detour to your left where the path forks to see the magnificent Candelabra Tree before returning to the main trail\nAt the top of the hill head right at the Gazos Trail\nRight at the Olmo Fire Rd\nLeft at the Doe Ridge Trail\nLeft at the Goat Hill Trail\nContinue onto the Candelabra Trail and back to your car");
          butanoCandelabra.setWaterfall(false);
          butanoCandelabra.setWildflowers(false);
          Hike result = endpoint.insertHike(butanoCandelabra).execute();*/
          
          Hike berryCreekFalls = new Hike().setDescription("Beautiful redwoods, an ocean view and no crowds make this an enjoyable and relazing route through Big Basin State Park.");
          berryCreekFalls.setId(5L);
          berryCreekFalls.setName("Big Basin Meteor Loop");
          berryCreekFalls.setAddress("21600 Big Basin Way, Boulder Creek, CA 95006");
          berryCreekFalls.setBathroomAvailable(true);
          berryCreekFalls.setCompleted(false);
          berryCreekFalls.setDistance(6.2);
          berryCreekFalls.setDogsAllowed(false);
          berryCreekFalls.setElevation(400);
          berryCreekFalls.setFreeParking(false);
          //butanoCandelabra.setGeoglogical(false);
          berryCreekFalls.setGeological(false);
          berryCreekFalls.setHistorical(false);
          berryCreekFalls.setLakeRiverCreek(true);
          berryCreekFalls.setLatitude(37.17225);
          berryCreekFalls.setLongitude(-122.22176);
          berryCreekFalls.setLoop(true);
          berryCreekFalls.setNoBikes(true);
          berryCreekFalls.setNoSteepHills(false);
          berryCreekFalls.setNotes("");
          berryCreekFalls.setOceanView(true);
          berryCreekFalls.setRating(0.0);
          berryCreekFalls.setReview("");
          berryCreekFalls.setTallTrees(true);
          berryCreekFalls.setTrails("From Park Headquarters take the Skyline-to-the-Sea Trail \n Creeping Forest Trail > Dool Trail \n Middle Ridge Road \n Meteor Trail \n Skyline-to-the-Sea Trail.");
          berryCreekFalls.setWaterfall(true);
          berryCreekFalls.setWildflowers(false);
          Hike result = endpoint.insertHike(berryCreekFalls).execute(); 
          System.out.println("hike added meteor loop");
      } catch (IOException e) {
        e.printStackTrace();
      }
          return (long) 0;
        }
    }

}
