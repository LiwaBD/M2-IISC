package Projet_SMA;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class TestParallelAgent extends Agent 
{
    
    @SuppressWarnings("removal")
	protected void setup() 
    {
    	double min = 1;
    	double max = 6;
    	double delta = 0.5;
    	double min_sub_interval[]; 
    	double max_sub_interval[];
//        ServiceDescription sd  = new ServiceDescription();
//        sd.setName( getLocalName() );
//        sd.setType("ca");
//        register( sd );
        
        try {
			DFAgentDescription dfd = new DFAgentDescription();
			DFAgentDescription[] result = DFService.search(this, dfd);
	
			System.out.println("Search returns: " + result.length + " compute agents" );
			
			for (double i = min+delta;i<max;i+=delta)
			{
				
			}
//			if (result.length>0)
//				System.out.println(" " + result[0].getName() );

			
//			sd  = new ServiceDescription();
//			sd.setType( "buyer" );
//			dfd.addServices(sd);
//			result = DFService.search(this, dfd);
//			System.out.println("Search for BUYER: " + result.length + " elements" );
//			if (result.length>0)
//				System.out.println(" " + result[0].getName() );
        }
        catch (FIPAException fe) { fe.printStackTrace(); }
    }
    
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
    
//	AID getService( String service )
//	{
//		DFAgentDescription dfd = new DFAgentDescription();
//   		ServiceDescription sd = new ServiceDescription();
//   		sd.setType( service );
//		dfd.addServices(sd);
//		try
//		{
//			DFAgentDescription[] result = DFService.search(this, dfd);
//			if (result.length>0)
//				return result[0].getName() ;
//		}
//		catch (Exception fe) {}
//      	return null;
//	}

}
