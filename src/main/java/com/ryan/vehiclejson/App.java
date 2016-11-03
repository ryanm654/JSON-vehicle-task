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
	public void printAscending() {

		/* Compare the vehicles based on price.
		 * We don't add this as a comparison method of the class as we will want
		 * to compare on different parameters later on.
		 */
		ArrayList<Vehicle> sortedList = (ArrayList<Vehicle>)vehicleList.clone();

		Collections.sort(sortedList, new Comparator<Vehicle>() {
			@Override 
			public int compare(Vehicle o1, Vehicle o2) {
				return Double.compare(o1.price, o2.price);
			}
		});

		for (Vehicle vehicle : sortedList) {
			System.out.println(vehicle.name + " - " + vehicle.price);
		}	

		return;
	}
	
	/* TODO Using the table below, calculate the specification of the 
	 * vehicles based on their SIPP. Print the specification */
	@SuppressWarnings("unused")
	public void printSpec() {
		/* Define the hashmaps for the SIPP comparison procedure */
		HashMap<String, String> carTypeMap = new HashMap<String, String>() {{
			put("M", "Mini");
			put("E", "Economy");
			put("C", "Compact");
			put("I", "Intermediate");
			put("S", "Standard");
			put("F", "Full size");
			put("P", "Premium");
			put("L", "Luxury");
			put("X", "Special");
		}};

		HashMap<String, String> doorsMap = new HashMap<String, String>() {{
			put("B", "2 doors");
			put("C", "4 doors");
			put("D", "5 doors");
			put("W", "Estate");
			put("T", "Convertible");
			put("F", "SUV");
			put("P", "Pick up");
			put("V", "Passenger Van");
		}};

		HashMap<String, String> transmissionMap = new HashMap<String, String>() {{
			put("M", "Manual");
			put("A", "Automatic");
		}};

		HashMap<String,String> fuelAirMap = new HashMap<String, String>() {{
			put("N", "Petrol/no AC");
			put("R", "Petrol/AC");
		}};

		/* Loop through the vehicles and print their specification */
		for (Vehicle vehicle : vehicleList) {
			System.out.print(vehicle.name + " - " + vehicle.sipp + " - ");
			String[] sippArray = vehicle.sipp.split("");

			System.out.print(carTypeMap.get(sippArray[1]) + " - ");
			System.out.print(doorsMap.get(sippArray[2]) + " - ");
			System.out.print(transmissionMap.get(sippArray[3]) + " - ");

			String[] fuelAir = fuelAirMap.get(sippArray[4]).split("/");
			System.out.println(fuelAir[0] + " - " + fuelAir[1]);
		}

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

		System.out.println("EXERCISE 1");
		handler.printAscending();
		System.out.println("\n\nEXERCISE 2");
		handler.printSpec();
		
		return;
	}
}
