package com.hikefinderapp;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint({ "NewApi" })
public class Display_Map extends Activity {

	Hike selectedHike;
	String name;
	double latitude;
	double longitude;
	
	@SuppressLint("NewApi")
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map_view);
	    Intent intent = getIntent();
	    name =  intent.getStringExtra("name");
	    latitude = intent.getExtras().getDouble("latitude");
	    longitude = intent.getExtras().getDouble("longitude");
	    //selectedHike = GlobalDataContainer.getSelectedHike();
	    
	    GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapview)).getMap();
	    String nullMap="false";
	    if (map==null) {
	    	nullMap="true";
	    }
	    
	    System.out.println(nullMap);
	    
	    Log.d("latitude ", ""+latitude);
	    Log.d("longitude", ""+longitude);
	    
	    LatLng hikeLatLng = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hikeLatLng, 13));

        map.addMarker(new MarkerOptions()
                .title(name)
                .position(hikeLatLng));

	  }
}