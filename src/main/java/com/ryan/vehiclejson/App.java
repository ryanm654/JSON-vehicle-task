/****************************************************
 *
 * Author: Ryan Malone
 *
 * This class will simply demonstrate the fulfillment
 * of the exercises detailed by calling the respective
 * methods in the VehicleJSONHandler class
 *
 ****************************************************/

package com.ryan.vehiclejson;

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
