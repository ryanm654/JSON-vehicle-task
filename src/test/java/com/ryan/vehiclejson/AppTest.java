package com.ryan.vehiclejson;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    public void setUpStreams() {

    }

    public void cleanUpStreams() {

    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Ex1 Print a list of all the cars, in ascending price order
     * Test set of 4 cars from vehicle.json
     *
     * Expected result:
     * Ford Focus - 157.85
     * Kia Ceed Estate - 311.03
     * VW Passat Estate - 469.37
     * Ford Galaxy - 706.89
     */
    public void testEx1()
    {
        String expectedResult = "Kia Picanto - 136.57" + System.getProperty("line.separator") +
                                "Ford Focus - 157.85" + System.getProperty("line.separator")  +
                                "Ford Focus - 157.85" + System.getProperty("line.separator")  +
                                "VW Passat Estate - 469.37" + System.getProperty("line.separator")  +
                                "Ford Galaxy - 706.89" + System.getProperty("line.separator");

        ByteArrayOutputStream consoleLogs = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleLogs));

        VehicleJSONHandler app = new VehicleJSONHandler("testSet.json");
        app.printAscending();


        assertTrue( consoleLogs.toString().equals(expectedResult) );

        System.setOut(null);
    }

    /**
     * Ex2 Using the table below, calculate the specification of the
     * vehicles based on their SIPP. Print the specification
     */
    public void testEx2()
    {
        String expectedResult = "Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC" + System.getProperty("line.separator") +
                                "Ford Galaxy - FVAR - Full size - Passenger Van - Automatic - Petrol - AC" + System.getProperty("line.separator") +
                                "VW Passat Estate - IWMR - Intermediate - Estate - Manual - Petrol - AC" + System.getProperty("line.separator") +
                                "Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC" + System.getProperty("line.separator") +
                                "Kia Picanto - MBMN - Mini - 2 doors - Manual - Petrol - no AC" + System.getProperty("line.separator");

        ByteArrayOutputStream consoleLogs = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleLogs));

        VehicleJSONHandler app = new VehicleJSONHandler("testSet.json");
        app.printSpec();

        assertTrue( consoleLogs.toString().equals(expectedResult) );

        System.setOut(null);
    }


    /**
     * Ex3 Print out the highest rated supplier per car type,
     * descending order
     */
    public void testEx3()
    {
        String expectedResult = "Ford Focus - Compact - Hertz - 8.9" + System.getProperty("line.separator") +
                                "Ford Galaxy - Full size - Hertz - 8.9" + System.getProperty("line.separator") +
                                "VW Passat Estate - Intermediate - Hertz - 8.9" + System.getProperty("line.separator") +
                                "Kia Picanto - Mini - Hertz - 8.9" + System.getProperty("line.separator");

        ByteArrayOutputStream consoleLogs = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleLogs));

        VehicleJSONHandler app = new VehicleJSONHandler("testSet.json");
        app.printHighestRated();

        assertTrue( consoleLogs.toString().equals(expectedResult) );

        System.setOut(null);
    }

    /**
     * Ex4 Give each vehicle a score based on the below breakdown,
     * then combine this score with the suppliers rating. Print out a
     * list of vehicles, ordered by the sum of the scores in descending order
     */
    public void testEx4()
    {
        String expectedResult = "Ford Galaxy - 7 - 8.9 - 15.9" + System.getProperty("line.separator") +
                                "Ford Focus - 3 - 8.9 - 11.9" + System.getProperty("line.separator") +
                                "VW Passat Estate - 3 - 8.9 - 11.9" + System.getProperty("line.separator") +
                                "Ford Focus - 3 - 7.8 - 10.8" + System.getProperty("line.separator") +
                                "Kia Picanto - 1 - 8.9 - 9.9" + System.getProperty("line.separator");

        ByteArrayOutputStream consoleLogs = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleLogs));

        VehicleJSONHandler app = new VehicleJSONHandler("testSet.json");
        app.printHighestScoring();

        assertTrue( consoleLogs.toString().equals(expectedResult) );

        System.setOut(null);
    }
}
