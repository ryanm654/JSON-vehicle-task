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
    private ByteArrayOutputStream consoleLogs = new ByteArrayOutputStream();

    public void setUpStreams() {
       System.setOut(new PrintStream(consoleLogs));
    }

    public void cleanUpStreams() {
       System.setOut(null);
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
        setUpStreams();
        VehicleJSONHandler app = new VehicleJSONHandler("./testSet.json");
        app.printAscending();

        cleanUpStreams();
        assertTrue( consoleLogs.toString() == "Ford Focus - 157.85\n" +
                                              "Kia Ceed Estate - 311.03\n" +
                                              "VW Passat Estate - 469.37\n" +
                                              "Ford Galaxy - 706.89" );
    }

    /**
     * Ex2 Using the table below, calculate the specification of the 
     * vehicles based on their SIPP. Print the specification
     */
    public void testEx2()
    {
        assertTrue( true );
    }

    /**
     * Ex3 Print out the highest rated supplier per car type, 
     * descending order
     */
    public void testEx3()
    {
        assertTrue( true );
    }

    /**
     * Ex4 Give each vehicle a score based on the below breakdown, 
     * then combine this score with the suppliers rating. Print out a 
     * list of vehicles, ordered by the sum of the scores in descending order
     */
    public void testEx4()
    {
        assertTrue( true );
    }
}
