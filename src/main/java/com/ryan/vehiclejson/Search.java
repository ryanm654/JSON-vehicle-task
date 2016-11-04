/****************************************************
 *
 * Author: Ryan Malone
 *
 * One of the objects within the provided JSON file
 * is known as 'Search', which contains the array
 * of vehicle objects. This class was created to allow
 * GSON to correctly bind the JSON to classes within
 * the program.
 *
 ****************************************************/

package com.ryan.vehiclejson;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

class Search {
	@SerializedName("VehicleList")
	private ArrayList<Vehicle> vehicleList;

	/* Getters */
	public ArrayList<Vehicle> getVehicleList() { return this.vehicleList; }
}