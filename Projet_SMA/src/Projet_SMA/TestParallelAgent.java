package Projet_SMA;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;
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
			//calculating the single integral
			long single_int_start = timeStamp();
		    Single_Integral = Projet_SMA.Function.MyFunction(min,max,delta);
		    long single_int_stop = timeStamp();
			System.out.println("** Single Calculator = " + Single_Integral + "  Done in :" + (single_int_stop-single_int_start) + " ms");
			System.out.println("**************************************************");
			
        	//searching for compute agents
			DFAgentDescription dfd = new DFAgentDescription();
			DFAgentDescription[] result = DFService.search(this, dfd);
			
			System.out.println("Search returns: " + result.length + " compute agents" );
			
		    double[] new_min = new double[result.length];
		    double[] new_max = new double[result.length];

		    long part_int_start = timeStamp();
			for (i=0; i<result.length;i++)
			{
			    double devider = (max - min)/result.length;
			    new_min[i] = min + (i*devider);
			    new_max[i] = min + ((i+1)*devider);
				
				ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
	            message.setContent(new_min[i] + "," + new_max[i] + "," + delta);
	            message.addReceiver(result[i].getName());
	            send(message);}
	            
	            // Add the behavior to wait for agents to send their response
	            addBehaviour(new SimpleBehaviour() {

	                double current_sum = 0.0;
	                int result_count_received = 0;
	            	/**
	            	 * The action of the behavior 
	            	 * 
	            	 */
	                public void action() {
	                    ACLMessage message = receive();
	                    if (message != null) {
	                    	result_count_received++;
	                        double value = Double.parseDouble(message.getContent());
	                        current_sum += value;
	                    } else {
	                        block();
	                    }  
	                }
	                
	            	/**
	            	 * Method that returns the count of the received messages
	            	 * 
	            	 */
	                @Override
	                public boolean done() {
	                    // Done when all messages are received
	                    return result_count_received >= result.length;
	                }
	            	/**
	            	 * Method that prints the results at the end of the reception
	            	 * 
	            	 */
	                @Override
	                public int onEnd() {
	                    // All messages are received, print the result
	                	long part_int_stop = timeStamp();
	                    System.out.println("** Summed Integral from ComputeAgents = " + current_sum);
	                    System.out.println("Done in :" + (part_int_stop-part_int_start) + " ms");
	                    System.out.println("**************************************************");
	                    return 0;
	                }
	            });
				
			System.out.println("**************************************************");
        }
        catch (FIPAException fe) { fe.printStackTrace(); }
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
