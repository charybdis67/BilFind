package com.bilfind;

/**
 * This class is used for saving coordinates for locations specific to our application
 * @author Abdul Basit Anees
 * @version 12/05/2017
 */

public class PointDouble
{
    //properties
    double x;
    double y;

    //constructor1
    public PointDouble()
    {
        this.x = 0.0;
        this.y = 0.0;
    }

    //constructor2
    public PointDouble( double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    //methods

    /**
     * This method returns the x-coordinate of the point
     * @return the x-coordinate of the point
     */
    public double getX()
    {
        return x;
    }

    /**
     * This method returns the y-coordinate of the point
     * @return the y-coordinate of the point
     */
    public double getY()
    {
        return y;
    }

    /**
     * This method updates a given point
     * @param x - the new x-coordinate
     * @param y - the new y-coordinate
     */
    public void setLocation( double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the data held by the object as a String
     * @return a string representation of the point
     */
    public String toString()
    {
        return "X = " + x + "\tY = " + y;
    }

}