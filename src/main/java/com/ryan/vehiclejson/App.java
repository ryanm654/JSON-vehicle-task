package com.ryan.vehiclejson;

import java.io.FileReader;
import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.ryan.vehiclejson.VehicleJSONHandler;

class Vehicle {
	// From JSON file
	String sipp;
	String name;
	double price;
	String supplier;
	double rating;

	// To be calculated
	String specification;
	int score;
}

class VehicleJSONHandler {
	// JSON File to read
	String jsonFilename;

	// Array list of vehicles from JSON
	ArrayList vehicleList = new ArrayList<Vehicle>();

	// Constructor
	public VehicleJSONHandler(String jsonFile) {
		jsonFilename = jsonFile;
	}

	/* Import the vehicles.json file into a JSON Object to be transformed into an 
	 * ArrayList */
	public static void readJSONFile() {
		return;
	}
	
	/* TODO Print a list of all the cars, in ascending price order */
	@SuppressWarnings("unused")
	public static void printAscending() {
		return;
	}
	
	/* TODO Using the table below, calculate the specification of the 
	 * vehicles based on their SIPP. Print the specification */
	@SuppressWarnings("unused")
	public static void printSpec() {
		return;
	}
	
	/* TODO Print out the highest rated supplier per car type, 
	 * descending order */
	@SuppressWarnings("unused")
	public static void printHighestRated() {
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
	public static void printHighestScoring() {
		return;
	}
}

public class App  {
	public static void main(String[] args) {
		System.out.println("Test!");

		// Initialise gson class
		Gson gson = new Gson();

		// Initialise JSON task handler
		VehicleJSONHandler handler = new VehicleJSONHandler("./vehicles.json");
		
		return;
	}
}
