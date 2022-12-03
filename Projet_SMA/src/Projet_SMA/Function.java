package Projet_SMA;

public class Function {

public static double MyFunction(double min, double max,double delta) {
    
		double area = 0;
		double modifier = 1; // this variable is to modify the result if min is bigger than max 
	
		
	    if(min > max) {
	        double temp_min = min; // creating a temperary var to store the min value
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
