package com.ryan.vehiclejson;

import java.io.FileReader;
import java.io.*;
import java.util.*;

//import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.annotations.SerializedName;

class Response {
	@SerializedName("Search")
	public Search search;
}

class Search {
	@SerializedName("VehicleList")
	public ArrayList<Vehicle> vehicleList;
}

class Vehicle {
	// From JSON file
	public String sipp;
	public String name;
	public double price;
	public String supplier;
	public double rating;
}

class VehicleJSONHandler {
	// GSON class
	Gson gson;
	// JSON File and contents to read
	String jsonFilename;
	String jsonContents;
	// Array list of vehicles from JSON
	ArrayList<Vehicle> vehicleList;

	// Constructor
	public VehicleJSONHandler(String jsonFilenameVar) {
		// Initialise GSON class
		gson = new Gson();
		jsonFilename = jsonFilenameVar;
		readJSONFile();
	}

	/* Import the vehicles.json file into a string to be transformed into an 
	 * ArrayList */
	public void readJSONFile() {
		try {
			FileReader jsonFile =  new FileReader(jsonFilename);
			Response response = gson.fromJson(jsonFile, Response.class);
			vehicleList = response.search.vehicleList;
			return;		
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/* TODO Print a list of all the cars, in ascending price order */
	@SuppressWarnings("unused")
	public void printAscending() {

		/* Compare the vehicles based on price.
		 * We don't add this as a comparison method of the class as we will want
		 * to compare on different parameters later on.
		 */
		Collections.sort(vehicleList, new Comparator<Vehicle>() {
			@Override 
			public int compare(Vehicle o1, Vehicle o2) {
				return Double.compare(o1.price, o2.price);
			}
		});

		for (Vehicle vehicle : vehicleList) {
			System.out.println(vehicle.name + " - " + vehicle.price);
		}	

		return;
	}
	
	/* TODO Using the table below, calculate the specification of the 
	 * vehicles based on their SIPP. Print the specification */
	@SuppressWarnings("unused")
	public void printSpec() {
		return;
	}
	
	/* TODO Print out the highest rated supplier per car type, 
	 * descending order */
	@SuppressWarnings("unused")
	public void printHighestRated() {
		return;
	}
	
	/* TODO Give each vehicle a score based on the below breakdown, 
	 * then combine this score with the suppliers rating. Print out a 
	 * list of vehicles, ordered by the sum of the scores in descending order
	 * 
	 * Breakdown:
	 * Manual transmission – 1 point
	 * Automatic transmission – 5 points
	 * Air conditioned – 2 points
	 */
	@SuppressWarnings("unused")
	public void printHighestScoring() {
		return;
	}
}

public class App  {
	public static void main(String[] args) {
		// Initialise JSON task handler
		VehicleJSONHandler handler = new VehicleJSONHandler(args[0]);

		handler.printAscending();
		
		return;
	}
}
