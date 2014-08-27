package com.hikefinderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Checklist  extends Activity{
	Button backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// when the screen is first created, show the layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checklist);
		Intent intent = getIntent();
		//String value = intent.getStringExtra("key"); //if it's a string you stored.
		backButton = (Button) findViewById(R.id.button1);
		
		backButton.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
	        	Intent myIntent = new Intent(Checklist.this, Route.class);
	        	//myIntent.putExtra("key", value); //Optional parameters
	        	Checklist.this.startActivity(myIntent);
            }
        });
		
	}

}
