package com.hikefinderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;

import java.util.ArrayList;


public class ProfileAdapter extends ArrayAdapter<Hike> {

	// declaring our ArrayList of items
	private ArrayList<Hike> hikes;

	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<Item> objects,
	* because it is the list of objects we want to display.
	*/
	public ProfileAdapter(Context context, int textViewResourceId, ArrayList<Hike> hikes) {
		super(context, textViewResourceId, hikes);
		this.hikes = hikes;
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
			v = inflater.inflate(R.layout.list_item, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		Hike h = hikes.get(position);

		if (h != null) {

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
				ttd.setText(h.getName());
			}
			if (mt != null){
				mt.setText("Your Rating: ");
			}
			
			if (mtd != null){
				mtd.setText(h.getRating() + " Stars");
			}
			
		}

		// the view must be returned to our activity
		return v;

	}

}