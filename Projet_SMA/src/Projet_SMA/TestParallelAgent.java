package Projet_SMA;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class TestParallelAgent extends Agent 
{
	static double delta, min, max;
	double Single_Integral = 0.0; 
	protected void setup() 
    {
    	// TODO we should take a look at the behaviours 
    	
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
        
        try {
			DFAgentDescription dfd = new DFAgentDescription();
			DFAgentDescription[] result = DFService.search(this, dfd);
			
			System.out.println("Search returns: " + result.length + " compute agents" );
//			System.out.println(getService("Compute Agent"));
			for (int i=0; i<result.length;i++)
			{
			    double[] partial_integral = new double[result.length];
			    double[] new_min = new double[result.length];
			    double[] new_max = new double[result.length];
			    
			    new_min[i]=min;
			    new_max[i]=max/result.length;
			    new_min[i]=new_max[i];
			    new_max[i] += new_min[i];
			    
	//			String ca_name[] = (String) result[i].toString();

//			    for(double j = min + delta; j < i; j += delta)
//			    {
			    partial_integral[i] = Function.MyFunction(min,max,delta);
				System.out.println("ComputeAgent " + i + " replies: " + partial_integral[i]);
//				}
			}
				
			 long startTime = System.currentTimeMillis();
		     Single_Integral = Projet_SMA.Function.MyFunction(min,max,delta);
		     long endTime = System.currentTimeMillis();
			System.out.println("** Single result = " + Single_Integral + "  Done in :" + (endTime-startTime) + " ms");

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
