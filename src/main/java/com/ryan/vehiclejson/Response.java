/****************************************************
 *
 * Author: Ryan Malone
 *
 * This class represents the lowest level of the
 * JSON received in this task, and contains a Search
 * object. It was created to allow the GSON to
 * correctly bind the JSON to classes within
 * the program.
 *
 ****************************************************/

package com.ryan.vehiclejson;

import com.google.gson.annotations.SerializedName;

class Response {
	@SerializedName("Search")
	private Search search;

	/* Getters */
	public Search getSearch() { return this.search; }
}