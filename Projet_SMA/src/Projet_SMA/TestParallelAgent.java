package Projet_SMA;

import jade.core.Agent;
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
		    double[] partial_integral = new double[result.length];
		    double[] new_min = new double[result.length];
		    double[] new_max = new double[result.length];
		    double sum = 0;
			for (int i=0; i<result.length;i++)
			{

			    double devider = (max - min)/result.length;
			    new_min[i] = min + (i*devider);
			    new_max[i] = min + ((i+1)*devider);
			      
			    partial_integral[i] = Function.MyFunction(new_min[i],new_max[i],delta);
			    sum+= partial_integral[i];
				System.out.println("ComputeAgent " + result[i].getName().getLocalName()+ " replies: " + partial_integral[i]);
			}
			System.out.println("sum = "+ sum);	
			 long startTime = System.currentTimeMillis();
		     Single_Integral = Projet_SMA.Function.MyFunction(min,max,delta);
		     long endTime = System.currentTimeMillis();
			System.out.println("** Single result = " + Single_Integral + "  Done in :" + (endTime-startTime) + " ms");

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
