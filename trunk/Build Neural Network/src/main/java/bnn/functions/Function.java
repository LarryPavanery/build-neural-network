/**
 * 
 */
package bnn.functions;

import bnn.config.ConfigProp;
import bnn.constants.Constants;
import bnn.entities.Neuron;

/**
 * @author larrypavanery
 *
 */
public class Function {

    private static ConfigProp config = ConfigProp.getInstance();

    public static class DistanceR3 {

	/**
	 * @param neuronA
	 * @param neuronB
	 * @return distance R3
	 */
	public static double calc(Neuron neuronA, Neuron neuronB) {

	    double x = Math.pow(neuronA.getDistanceX().getValue() 
		    - neuronB.getDistanceX().getValue(), 2.0D);
	    
	    double y = Math.pow(neuronA.getDistanceY().getValue() 
		    - neuronB.getDistanceY().getValue(), 2.0D);
	    
	    double z = Math.pow(neuronA.getDistanceZ().getValue() 
		    - neuronB.getDistanceZ().getValue(), 2.0D);

	    double dist = Math.sqrt(x + y + z);

	    return dist;
	}
    }

    public static class ConnectionProbability {
	/**
	 * P = C.e^-(d/lambda)^2 
	 * @return probability
	 */
	public static double calc(Neuron neuronA, Neuron neuronB) {
	    
	    double paramC = 0.0D;
	    
	    if (Constants.TIPO_E.equalsIgnoreCase(neuronA.getTipo())
		    && Constants.TIPO_E.equalsIgnoreCase(neuronB.getTipo())) {
		paramC = Double.parseDouble(config.getProp()
			.getProperty(Constants.BNN_PARAM_EE));
		
	    } else if (Constants.TIPO_E.equalsIgnoreCase(neuronA.getTipo())
		    && Constants.TIPO_I.equalsIgnoreCase(neuronB.getTipo())) {
		paramC = Double.parseDouble(config.getProp()
			.getProperty(Constants.BNN_PARAM_EI));
		
	    } else if (Constants.TIPO_I.equalsIgnoreCase(neuronA.getTipo())
		    && Constants.TIPO_I.equalsIgnoreCase(neuronB.getTipo())) {
		paramC = Double.parseDouble(config.getProp()
			.getProperty(Constants.BNN_PARAM_II));
		
	    } else if (Constants.TIPO_I.equalsIgnoreCase(neuronA.getTipo())
		    && Constants.TIPO_E.equalsIgnoreCase(neuronB.getTipo())) {
		paramC = Double.parseDouble(config.getProp()
			.getProperty(Constants.BNN_PARAM_IE));
	    }

	    double paramLambda = Double.parseDouble(config.getProp()
		    .getProperty(Constants.BNN_CONSTANT_LAMBDA));
	    
	    double prob = paramC * Math.pow(Math.E, 
		    -1 * Math.pow(DistanceR3.calc(neuronA, neuronB) / paramLambda, 2.0D));
	    
	    return prob;
	}

    }
}
