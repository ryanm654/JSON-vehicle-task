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

import java.util.TreeMap;

class Vehicle {
	// From JSON file
	private String sipp;
	private String name;
	public double price;
	public String supplier;
	public double rating;

	/* Calculated from SIPP */
	public transient String carType;
	public transient String doorType;
	public transient String transmission;
	public transient String fuel;
	public transient String air;

	/* Calculated from SIPP and Rating */
	public transient double score = 0;
	public transient double scoreAddRating;


	/* Getters */
	public String getSipp() { return this.sipp; }
	public String getName() { return this.name; }
	public double getPrice() { return this.price; }
	public String getSupplier() { return this.supplier; }
	public double getRating() { return this.rating; }
	public String getCarType() { return this.carType; }
	public String getDoorType() { return this.doorType; }
	public String getTransmission() { return this.transmission; }
	public String getFuel() { return this.fuel; }
	public String getAir() { return this.air; }

	/* Setters */
	public void setSipp(String newSIPP) { this.sipp = newSIPP; return; }
	public void setName(String newName) { this.name = newName; return; }
	public void setPrice(double newPrice) { this.price = newPrice; return; }
	public void setSupplier(String newSupp) { this.supplier = newSupp; return; }
	public void setRating(double newRating) { this.rating = newRating; return; }

	public void calculateSIPP() {
		/* Define the hashmaps for the SIPP comparison procedure */
		TreeMap<String, String> carTypeMap = new TreeMap<String, String>() {{
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

		TreeMap<String, String> doorsMap = new TreeMap<String, String>() {{
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

		TreeMap<String, String> transmissionMap = new TreeMap<String, String>() {{
			put("M", "Manual");
			put("A", "Automatic");
		}};

		TreeMap<String,String> fuelAirMap = new TreeMap<String, String>() {{
			put("N", "Petrol/no AC");
			put("R", "Petrol/AC");
		}};

		String[] sippArray = this.sipp.split("");
		this.carType = carTypeMap.get(sippArray[0]);
		this.doorType = doorsMap.get(sippArray[1]);
		this.transmission = transmissionMap.get(sippArray[2]);
		String[] fuelAir = fuelAirMap.get(sippArray[3]).split("/");
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
