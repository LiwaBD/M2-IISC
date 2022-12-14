package Projet_SMA;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

/**
 * This class is the TestParallelAgents that starts by searching for compute Agents, divides the initial integral to 
 * intervals depending on the number of agents found, calculates the partial integrals, the total of them as well as the 
 * single integral, and compares the results
 * 
 * @author BEN DHIA Liwa / ROUAS Amal
 * @version 1.0
 * 
 * */

public class TestParallelAgent extends Agent 
{
	
	/**
	 * Method to setup the agent that we are going to use to test our project
	 * 
	 */
	protected void setup() 
    {	
		int i;
		double min = 0.0;
		double max = 0.0;
		double delta = 0.0;
		double Single_Integral = 0.0; 

		// getting the arguments inserted by the user
        Object[] args = getArguments();
        String arg1 = args[0].toString(); 
        String arg2 = args[1].toString(); 
        String arg3 = args[2].toString(); 
        
        // getting the integral parameters from args 
       if (args != null && args.length != 0)
       {
	       min = Double.parseDouble(arg1);
	       max = Double.parseDouble(arg2);
	       delta = Double.parseDouble(arg3);
	       System.out.println("TestParallelAgent " + this.getLocalName() + " starting...");
	       System.out.println("**************************************************");
		   System.out.println("Arguments : Integral Min : "+ min +" | Integral Max : "+ max + " | Delta : "+ delta);
		   System.out.println("**************************************************");
       }
        
        try {
        	
        	//searching for compute agents
			DFAgentDescription dfd = new DFAgentDescription();
			DFAgentDescription[] result = DFService.search(this, dfd);
			
			System.out.println("Search returns: " + result.length + " compute agents" );
			
			//calculating partial integrals
		    double[] partial_integral = new double[result.length];
		    double[] new_min = new double[result.length];
		    double[] new_max = new double[result.length];

		    long part_int_start = timeStamp();
			for (i=0; i<result.length;i++)
			{
			    double devider = (max - min)/result.length;
			    new_min[i] = min + (i*devider);
			    new_max[i] = min + ((i+1)*devider);
			      
			    partial_integral[i] = Function.MyFunction(new_min[i],new_max[i],delta);

				System.out.println("ComputeAgent " + result[i].getName().getLocalName()+ " replies: " + partial_integral[i]);
			}
			
			long part_int_stop = timeStamp();
			System.out.println("** Summed integal = "+ total(partial_integral,i) + "  Done in :" + (part_int_stop-part_int_start) + " ms");	
			
			System.out.println("**************************************************");
			
			//calculating the single integral
			long single_int_start = timeStamp();
		    Single_Integral = Projet_SMA.Function.MyFunction(min,max,delta);
		    long single_int_stop = timeStamp();
			System.out.println("** Single Calculator = " + Single_Integral + "  Done in :" + (single_int_stop-single_int_start) + " ms");
			
			System.out.println("**************************************************");

        }
        catch (FIPAException fe) { fe.printStackTrace(); }
    }
	
	/**
	 *Method that calculates the sum of the partial integrals
	 *
	 *@param partial_integrals : The integrals that we wants to calculate their sum
	 *@param integral_nb : the number of the integrals
	 *
	 *@return Returns a float representing the total of the partial integrals 
	 */
	protected double total(double[] partial_integrals, int integral_nb)
	{
		double total = 0;
		
		for (int counter = 0;counter<integral_nb;counter++)
		{
			total+= partial_integrals[counter];
		}
		
		return total;
	}
	
	/**
	 *Method to get the current time in milliseconds
	 *
	 *@return Returns the current time 
	 */
	protected long timeStamp()
	{
		return  System.currentTimeMillis();
		
	}
}
