package Projet_SMA;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
 
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
        
        addBehaviour(new CyclicBehaviour() {
        	/**
        	 * The action of the behavior
        	 * 
        	 */
            public void action() {
                ACLMessage message = receive();
                
                if (message != null) {

                    // getting the integral parameters from message arguments
                    String[] split = message.getContent().split(",");
                    double min =  Double.parseDouble(split[0]);
                    double max =  Double.parseDouble(split[1]);
                    double delta = Double.parseDouble(split[2]);
                    
                    double result = Function.MyFunction(min,max,delta);
                   
                    ACLMessage reply = message.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(String.valueOf(result));
                    send(reply);
                    System.out.println(String.format("** Agent %s send response partial integral between %f and %f = %f",getLocalName(),min,max,result));
                } else block();
            }
         });
		}
	
	/**
	 *This method will take down the agent after completing the registration
	 */
	protected void AgentTakeDown() 
	{
	    // Printout a dismissal message
	    System.out.println("Derigistring Agent "+ getLocalName()+" ...");
	    System.out.println("**************************************************");
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
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
