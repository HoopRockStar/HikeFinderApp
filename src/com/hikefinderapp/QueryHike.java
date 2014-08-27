package com.hikefinderapp;


public class QueryHike {
	
	
	  private Long id;
	 
	  private String name;
	 
	  private String address;
	 
	  private String description;
	 
	  private String trails;
	  
	 
	  private Boolean completed;
	 
	  private Double rating;
	
	  private String review;
	 
	  private String notes;
	 
	  private Integer score;
	   
	  private Double longitude;

	  private Double latitude;

	  private Boolean bathroomAvailable;

	  private Boolean freeParking;

	  private Boolean dogsAllowed;

	  private Boolean noBikes;
	  

	  private Double distance;

	  private Integer elevation;

	  private Boolean noSteepHills;
	  

	  private Boolean loop;

	  private Boolean oceanView;

	  private Boolean waterfall;

	  private Boolean lakeRiverCreek;

	  private Boolean historical;

	  private Boolean geological;

	  private Boolean tallTrees;

	  private Boolean wildflowers;
	  
	  public QueryHike() {}
	  
	  public QueryHike(Boolean bathroomAvailable, Boolean freeParking, 
			  Boolean dogsAllowed, Boolean noSteepHills,
			  Boolean loop, Boolean oceanView, Boolean waterfall,
			  Boolean lakeRiverCreek, Boolean historical, Boolean geological,
			  Boolean tallTrees, Boolean wildflowers
			  ) {
		  this.bathroomAvailable = bathroomAvailable;
		  this.freeParking = freeParking;
		  this.dogsAllowed = dogsAllowed;
		  this.noSteepHills = noSteepHills;
		  this.loop = loop;
		  this.oceanView = oceanView;
		  this.waterfall = waterfall;
		  this.lakeRiverCreek = lakeRiverCreek;
		  this.historical = historical;
		  this.geological = geological;
		  this.tallTrees = tallTrees;
		  this.wildflowers = wildflowers;
		  
	  }
	  
	  public Boolean getBathroomAvailable() {
		  return bathroomAvailable;
	  }
	  
	  public Boolean getFreeParking() {
		  return freeParking;
	  }
	  
	  public Boolean getDogsAllowed() {
		  return dogsAllowed;
	  }
	  
	  public Boolean getNoBikes() {
		  return noBikes;
	  }
	  
	  public Double getDistance() {
		  return distance;
	  }
	  
	  public Integer getElevation() {
		  return elevation;
	  }
	  
	  public Boolean getNoSteepHills() {
		  return noSteepHills;
	  }
	  
	  public Boolean getLoop() {
		  return loop;
	  }
	  
	  public Boolean getOceanView() {
		  return oceanView;
	  }
	  
	  public Boolean getWaterfall() {
		  return waterfall;
	  }
	  
	  public Boolean getLakeRiverCreek() {
		  return lakeRiverCreek;
	  }
	  
	  public Boolean getHistorical() {
		  return historical;
	  }
	  
	  public Boolean getGeological() {
		  return geological;
	  }
	  
	  public Boolean getTallTrees() {
		  return tallTrees;
	  }
	  
	  public Boolean getWildflowers() {
		  return wildflowers;
	  }

}
