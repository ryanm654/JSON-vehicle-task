/****************************************************
 *
 * Author: Ryan Malone
 *
 * This class represents a vehicle, with details
 * specified from a json file provided to the user.
 * It is capable of deciphering its own SIPP and
 * score but I have made these public methods
 * rather than something that is done in a constructor
 * as most of the variables are set through the gson
 * binding call rather than by constructing a vehicle
 *
 ****************************************************/

package com.ryan.vehiclejson;

import java.util.HashMap;

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