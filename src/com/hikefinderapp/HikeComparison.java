package com.hikefinderapp;

import java.util.Comparator;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import com.hikefinderapp.entity.hikeendpoint.Hikeendpoint;
import com.hikefinderapp.entity.hikeendpoint.model.CollectionResponseHike;
import com.hikefinderapp.entity.hikeendpoint.model.Hike;
 

public class HikeComparison implements Comparator<Hike>{
	@Override
    public int compare(Hike hike1, Hike hike2) {
        return hike2.getScore() - hike1.getScore();
    }
}


