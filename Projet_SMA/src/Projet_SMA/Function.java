package Projet_SMA;

/**
 * This class represents the function that we are going to use to calculate the integrals in the project
 * 
 * @author BEN DHIA Liwa / ROUAS Amal
 * @version 1.0
 * 
 * */

public class Function {
	
	/**
	 * This method calculates the integral using a method close to the simpson method :
	 * https://fr.wikipedia.org/wiki/M%C3%A9thode_de_Simpson
	 * 
	 * @param min The lower born of the integral
	 * @param max The upper born of the integral
	 * @param delta The step of the calculation of the integral
	 * @return Returns the calculated integral
	 * 
	 * */

public static double MyFunction(double min, double max,double delta) {
    
		double area = 0; // this is the area of the rectangle of the partial integral
		double modifier = 1; // this variable is to modify the result if min is bigger than max 
		
	    if(min > max) {
	        double temp_min = min; // creating a temporary variable to store the min value
	        min = max;
	        max = temp_min;
	        modifier = -1;
	    }
	    
	    for(double i = min + delta; i < max; i += delta) {
	        double distance_from_min = i - min;
	        // Calculating the area of the new rectangle of every part of the integral
	        area += (delta / 2) * (Projet_SMA.function_test.F(min + distance_from_min) + Projet_SMA.function_test.F(min + distance_from_min - delta));
	    }
	    return  area * modifier;
	}
}
