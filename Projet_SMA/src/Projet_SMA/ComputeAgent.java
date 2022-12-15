package Projet_SMA;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * This class represents the function that we are going to use to calculate the integrals in the project
 * 
 * @author BEN DHIA Liwa / ROUAS Amal
 * @version 1.0
 * 
 * */

public class ComputeAgent extends Agent {
	
    /**
     *This method sets up the compute agents
     */
	protected void setup() {
		
		System.out.println("Compute Agent "+ getLocalName() + " created.");
		
        ServiceDescription sd  = new ServiceDescription();
        sd.setName( getLocalName() );
        sd.setType("Compute Agent");
        register( sd );
	      
		 AgentTakeDown();
		}
	
	/**
	 *This method will take down the agent after completing the registration
	 */
	protected void AgentTakeDown() 
	{
		takeDown();
	    // Printout a dismissal message
	    System.out.println("Agent "+ getLocalName()+" terminating...");
	    System.out.println("**************************************************");
	}
	
	/**
	 *This method will register evry compute agent created by the user 
	 *
	 *@param sd Service Description instance
	 */
    void register( ServiceDescription sd)
    {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);

        try {  
            DFService.register(this, dfd );  
        }
        catch (FIPAException fe) { fe.printStackTrace(); }
    }
}
