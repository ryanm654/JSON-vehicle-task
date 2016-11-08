/****************************************************
 *
 * Author: Ryan Malone
 *
 * This class is for implementing all of the exercises
 * that I needed to fulfill for the coding task and
 * will also be used to provide the methods
 * that a REST API will call
 *
 ****************************************************/

package com.ryan.vehiclejson;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import com.google.gson.Gson;
import com.google.gson.Gson;

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
			vehicleList = response.getSearch().getVehicleList();
			return;
		}
		catch (IOException e) {
			System.out.println("File has not been found, please make sure you are running from the base directory and have pulled vehicles.json\n\n");
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
				return Double.compare(o1.getPrice(), o2.getPrice());
			}
		});

		for (Vehicle vehicle : sortedList) {
			System.out.println(vehicle.getName() + " - " + vehicle.getPrice());
		}

		return;
	}

	/* Ex2 Using the table below, calculate the specification of the
	 * vehicles based on their SIPP. Print the specification */
	public void printSpec() {
		/* Loop through the vehicles and print their specification */
		for (Vehicle vehicle : vehicleList) {
			System.out.println(vehicle.getName() + " - " + vehicle.getSipp() + " - " +
											 vehicle.getCarType() + " - " + vehicle.getDoorType()  + " - " +
											 vehicle.getTransmission() + " - " + vehicle.getFuel()  + " - " +
											 vehicle.getAir());
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
			Vehicle currentBest = currentHighestRated.get(vehicle.getCarType());

			if (currentBest == null) {
				// Add new vehicle if one does not currently exist
				currentHighestRated.put(vehicle.getCarType(), vehicle);
				highestRatedList.add(vehicle);
			}
			else if (currentBest.getRating() < vehicle.getRating()) {
				// Remove current highest rated supplier of car type from arraylist
				highestRatedList.remove(currentBest);

				// Add new vehicle
				currentHighestRated.put(vehicle.getCarType(), vehicle);
				highestRatedList.add(vehicle);
			}
		}

		/* Once this process is complete we must sort the new array list by
		   rating, descending order */
		Collections.sort(highestRatedList, new Comparator<Vehicle>() {
			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				return Double.compare(o2.getRating(), o1.getRating());
			}
		});

		/* Finally, print them out */
		for (Vehicle vehicle : highestRatedList) {
			System.out.println(vehicle.getName() + " - " + vehicle.getCarType() + " - " +
												 vehicle.getSupplier() + " - " + vehicle.getRating());
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
			System.out.println(vehicle.getName() + " - " + (int) vehicle.score + " - " +
											   vehicle.getRating() + " - " + vehicle.scoreAddRating);
		}
		return;
	}
}
