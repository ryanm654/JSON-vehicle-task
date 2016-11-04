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

	public transient String carType;
	public transient String doorType;
	public transient String transmission;
	public transient String fuel;
	public transient String air;

	public transient double score = 0;
	public transient double scoreAddRating;

	public void calculateSIPP() {
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
			put("X", "Special");
		}};

		HashMap<String, String> transmissionMap = new HashMap<String, String>() {{
			put("M", "Manual");
			put("A", "Automatic");
		}};

		HashMap<String,String> fuelAirMap = new HashMap<String, String>() {{
			put("N", "Petrol/no AC");
			put("R", "Petrol/AC");
		}};

		String[] sippArray = this.sipp.split("");
		this.carType = carTypeMap.get(sippArray[1]);
		this.doorType = doorsMap.get(sippArray[2]);
		this.transmission = transmissionMap.get(sippArray[3]);
		String[] fuelAir = fuelAirMap.get(sippArray[4]).split("/");
		this.fuel = fuelAir[0];
		this.air = fuelAir[1];
		return;
	}

	public void calculateScore() {
		if (this.transmission.equals("Manual")) { this.score += 1; }
		// Use an else if instead of an else in case more transmissions are added
		else if (this.transmission.equals("Automatic")) { this.score += 5; }

		if (this.air.equals("AC")) { this.score += 2; }

		this.scoreAddRating = score + rating;
		return;
	}
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
		for (Vehicle vehicle : vehicleList) {
			vehicle.calculateSIPP();
			vehicle.calculateScore();
		}
	}

	/* Import the vehicles.json file into a into an ArrayList */
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

	/* Ex1 Print a list of all the cars, in ascending price order */
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

	/* Ex2 Using the table below, calculate the specification of the
	 * vehicles based on their SIPP. Print the specification */
	public void printSpec() {
		/* Loop through the vehicles and print their specification */
		for (Vehicle vehicle : vehicleList) {
			System.out.println(vehicle.name + " - " + vehicle.sipp + " - " +
											 vehicle.carType + " - " + vehicle.doorType  + " - " +
											 vehicle.transmission + " - " + vehicle.fuel  + " - " +
											 vehicle.air);
		}
		return;
	}

	/* TODO Print out the highest rated supplier per car type,
	 * descending order */
	public void printHighestRated() {
		ArrayList<Vehicle> highestRatedList = new ArrayList<Vehicle>();
		HashMap<String, Vehicle> currentHighestRated 
															= new HashMap<String, Vehicle>();

		/* Loop through each vehicle, adding the highest rated supplier of each
		   type to the array list for printing later, and the hashmap for comparison
		   with other vehicles of the same type */
		for (Vehicle vehicle : vehicleList) {
			Vehicle currentBest = currentHighestRated.get(vehicle.carType);
			
			if (currentBest == null) {
				// Add new vehicle if one does not currently exist
				currentHighestRated.put(vehicle.carType, vehicle);
				highestRatedList.add(vehicle);
			}
			else if (currentBest.rating < vehicle.rating) {
				// Remove current highest rated supplier of car type from arraylist
				highestRatedList.remove(currentBest);

				// Add new vehicle
				currentHighestRated.put(vehicle.carType, vehicle);
				highestRatedList.add(vehicle);
			}
		}

		/* Once this process is complete we must sort the new array list by 
		   rating, descending order */
		Collections.sort(highestRatedList, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				return Double.compare(o2.rating, o1.rating);
			}
		});

		/* Finally, print them out */
		for (Vehicle vehicle : highestRatedList) {
			System.out.println(vehicle.name + " - " + vehicle.carType + " - " + 
												 vehicle.supplier + " - " + vehicle.rating);
		}

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
	public void printHighestScoring() {
		ArrayList<Vehicle> sortedList = (ArrayList<Vehicle>)vehicleList.clone();

		Collections.sort(sortedList, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				return Double.compare(o2.scoreAddRating, o1.scoreAddRating);
			}
		});

		for (Vehicle vehicle : sortedList) {
			System.out.println(vehicle.name + " - " + (int) vehicle.score + " - " + 
											   vehicle.rating + " - " + vehicle.scoreAddRating);
		}
		return;
	}
}

public class App  {
	public static void main(String[] args) {
		// Initialise JSON task handler
		VehicleJSONHandler handler = new VehicleJSONHandler(args[0]);

		System.out.println("-----------------\nEXERCISE 1\n-----------------");
		handler.printAscending();
		System.out.println("\n-----------------\nEXERCISE 2\n-----------------");
		handler.printSpec();
		System.out.println("\n-----------------\nEXERCISE 3\n-----------------");
		handler.printHighestRated();
		System.out.println("\n-----------------\nEXERCISE 4\n-----------------");
		handler.printHighestScoring();

		return;
	}
}
