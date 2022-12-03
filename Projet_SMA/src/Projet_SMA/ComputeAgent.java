package Projet_SMA;

import jade.core.Agent;

public class ComputeAgent extends Agent {

	protected void setup() {
		
		double Single_Integral;
		double min=0.0, max=0.0, delta=0.0;
		System.out.println("My local name is "+ getLocalName());
		 
	        Object[] args = getArguments();
	        String arg1 = args[0].toString(); 
	        String arg2 = args[1].toString(); 
	        String arg3 = args[2].toString(); 
	       if (args != null && args.length != 0)
	       {
		       min = Double.parseDouble(arg1);
		       max = Double.parseDouble(arg2);
		       delta = Double.parseDouble(arg3);
			   System.out.println("Integral Min : "+ min);
			   System.out.println("Integral Max : "+ max);
			   System.out.println("Delta : "+ delta);
	       }
	       
	     System.out.println("** Single calculator: **");
	     
	 	 long startTime = System.currentTimeMillis();
	     Single_Integral = Projet_SMA.Function.MyFunction(min,max,delta);
	     long endTime = System.currentTimeMillis();
	     
	     System.out.println("** Single result = " + Single_Integral + "  Done in :" + (endTime-startTime) + " ms");
		 this.takeDown();
		}
	
	
	protected void takeDown() 
	{
	    // Printout a dismissal message
	    System.out.println("Agent "+ getLocalName()+" terminating.");
	}
	
	
//	  void register( ServiceDescription sd) {
//		    DFAgentDescription dfd = new DFAgentDescription();
//		    dfd.setName(getAID());
//		    dfd.addServices(sd);
//
//		    try {
//		      DFService.register(this, dfd );
//		    }
//		    catch (FIPAException fe) { fe.printStackTrace(); }
//		  }
}
