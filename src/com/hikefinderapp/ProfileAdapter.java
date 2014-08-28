package com.hikefinderapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

import java.util.ArrayList;
import java.util.List;


public class ProfileAdapter extends ArrayAdapter<UserProfile> {

	// declaring our ArrayList of items
	private ArrayList<UserProfile> profiles;

	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<Item> objects,
	* because it is the list of objects we want to display.
	*/
	public ProfileAdapter(Context context, int textViewResourceId, ArrayList<UserProfile> profiles) {
		super(context, textViewResourceId, profiles);
		this.profiles = profiles;
	}

	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.profile_item, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		UserProfile p = profiles.get(position);
		//Toast.makeText(getContext(), "Retrieving profile: " + p.toString(), Toast.LENGTH_LONG).show();

		if (p != null) {
			
			int month=0;
			String day="0";
			String year="0";
			Log.d("p-date: ", p.getDateCompleted());
			if(p.getDateCompleted().length()==9) {
				month = Integer.parseInt(p.getDateCompleted().substring(0,1));
				day = p.getDateCompleted().substring(2,4);
				year = p.getDateCompleted().substring(5,9);
			} else {
				month = Integer.parseInt(p.getDateCompleted().substring(0,2));
				day = p.getDateCompleted().substring(3,5);
				year = p.getDateCompleted().substring(6,10);
			}
			
			String date = "" + (month +1) +"-" + day + "-" + year;

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView tt = (TextView) v.findViewById(R.id.toptext);
			TextView ttd = (TextView) v.findViewById(R.id.toptextdata);
			TextView mt = (TextView) v.findViewById(R.id.middletext);
			TextView mtd = (TextView) v.findViewById(R.id.middletextdata);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (tt != null){
				tt.setText("Hike: ");
			}
			if (ttd != null){
				ttd.setText(p.getHikeName() );
			}
			if (mt != null){
				
				mt.setText("Your Rating: ");
			}
			
			if (mtd != null) {
				mtd.setText(""+p.getRating() + " stars (" + date +")");
			}
			
			
			
		}

		// the view must be returned to our activity
		return v;

	}
}