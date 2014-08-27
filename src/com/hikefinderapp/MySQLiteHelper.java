package com.hikefinderapp;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "UserProfileDB";
    
 // Books table name
    private static final String TABLE_PROFILE = "userprofile";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_COMPLETED = "completed";
    private static final String KEY_NAME = "hikeName";
    private static final String KEY_DATE_COMPLETED = "dateCompleted";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_RATING = "rating";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_NOTES = "notes";

    private static final String[] COLUMNS = {KEY_ID, KEY_COMPLETED, KEY_NAME, KEY_DATE_COMPLETED, KEY_DISTANCE, KEY_RATING, KEY_REVIEW, KEY_NOTES};
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PROFILE_TABLE = "CREATE TABLE userprofile ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        		"completed INTEGER, " +
                "hikeName TEXT, "+
                "dateCompleted TEXT, " +
                "distance REAL, " + 
                "rating REAL, " +
                "review TEXT, " +
                "notes TEXT )";
 
        // create books table
        db.execSQL(CREATE_PROFILE_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS userprofile");
 
        // create fresh books table
        this.onCreate(db);
    }
    
    //add a profile entry
    public void addProfileEntry(UserProfile profile) {
        //for logging
    	Log.d("addProfileEntry", profile.toString());

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("INSERT INTO userprofile (" + KEY_ID + ", " + KEY_COMPLETED + ", " + KEY_NAME +", " 
    			+ KEY_DATE_COMPLETED + ", " + KEY_DISTANCE + ", "
    			+ KEY_RATING + ", " + KEY_REVIEW + ", " + KEY_NOTES
    			+ ") VALUES ('" + (int) profile.getId() + "', '" + profile.getCompleted() + "', '" + profile.getHikeName() + "', '" 
    			+ profile.getDateCompleted() + "', '" + profile.getDistance()
    			+ "', '" + profile.getRating() + "', '" + profile.getReview()
    			+ "', '" + profile.getNotes() + "')");

		// 2. create ContentValues to add key "column"/value
		/*ContentValues values = new ContentValues();
		values.put(KEY_COMPLETED, profile.getCompleted());
		values.put(KEY_NAME, profile.getHikeName());
		values.put(KEY_DATE_COMPLETED, profile.getDateCompleted()); 
		values.put(KEY_DISTANCE, profile.getDistance()); 
		values.put(KEY_RATING, profile.getRating()); 
		values.put(KEY_REVIEW, profile.getReview()); 
		values.put(KEY_NOTES, profile.getNotes());
		
		// 3. insert
		db.insert(TABLE_PROFILE, // table
		        null, //nullColumnHack
		        values); // key/value -> keys = column names/ values = column values
		*/
		
		// 4. close
		db.close();
    }
    
    public UserProfile getProfileEntry(long id){
    	id = (int) id;
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        UserProfile profile = null;
       /* Cursor cursor = 
        		db.execSQL("SELECT * FROM userprofile WHERE " 
        				+ KEY_NAME + " = '" + profile.getHikeName() + "' && " 
        				+ KEY_DISTANCE + " = '" + profile.getDistance() + "' && "
        				+ KEY_COMPLETED + " = '" + profile.getCompleted() + "'");
*/
     
        // 2. build query
        Cursor cursor =
                db.query(TABLE_PROFILE, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
     
            // 4. build profile object
	        profile = new UserProfile();
	        profile.setId(Integer.parseInt(cursor.getString(0)));
	        profile.setCompleted(cursor.getInt(1));
	        profile.setHikeName(cursor.getString(2));
	        profile.setDateCompleted(cursor.getString(3));
	        profile.setDistance(cursor.getDouble(4));
	        profile.setRating(cursor.getDouble(5));
	        profile.setReview(cursor.getString(6));
	        profile.setNotes(cursor.getString(7));
        } else {
        	Log.d("Cursor is null", " does not exists in database");
        	return null;
        }
        
        
        // 5. return profile
        return profile;
    }
    
    public int updateUserProfile(UserProfile profile) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("completed", profile.getCompleted());
        values.put("hikeName", profile.getHikeName()); 
        values.put("dateCompleted", profile.getDateCompleted());
        values.put("distance", profile.getDistance());
        values.put("rating", profile.getRating());
        values.put("review", profile.getReview());
        values.put("notes", profile.getNotes());
     
        // 3. updating row
        int i = db.update(TABLE_PROFILE, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(profile.getId()) }); //selection args
     
        // 4. close
        db.close();
     
        return i;
     
    }
    
    public List<UserProfile> getAllProfileEntries() {
        List<UserProfile> profileEntries = new LinkedList<UserProfile>();
  
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PROFILE;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        UserProfile profile = null;
        if (cursor.moveToFirst()) {
            do {
                profile = new UserProfile();
                profile.setId(Integer.parseInt(cursor.getString(0)));
                profile.setCompleted(cursor.getInt(1));
                profile.setHikeName(cursor.getString(2));
                profile.setDateCompleted(cursor.getString(3));
                profile.setDistance(cursor.getDouble(4));
                profile.setRating(cursor.getDouble(5));
                profile.setReview(cursor.getString(6));
                profile.setNotes(cursor.getString(7));
  
                // Add book to books
                profileEntries.add(profile);
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllProfileEntries()", profileEntries.toString());
  
        // return books
        return profileEntries;
    }
    
    public void deleteUserProfileEntry(UserProfile profile) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_PROFILE, //table name
                KEY_ID + " = ?",  // selections
                new String[] { String.valueOf(profile.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
        Log.d("deleteUserProfileEntry", profile.toString());
 
    }
    
    public int entryExists(long id) {
        Cursor c = null;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String query = "select count(*) from userprofile where id = ?";
            c = db.rawQuery(query, new String[] {"'"+id +"'"});
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        }
        finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }


    
}
