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

	    double x = Math.pow(neuronA.getDistanceX().getValue() - neuronB.getDistanceX().getValue(), 2.0D);
	    double y = Math.pow(neuronA.getDistanceY().getValue() - neuronB.getDistanceY().getValue(), 2.0D);
	    double z = Math.pow(neuronA.getDistanceZ().getValue() - neuronB.getDistanceZ().getValue(), 2.0D);

	    double dist = Math.sqrt(x + y + z);

	    return dist;
	}
    }

    public static class ConnectionProbability {
	/**
	 * @return probability
	 */
	public static double calc(Neuron neuronA, Neuron neuronB) {
	    
	    double paramC = Double.parseDouble(config.getProp().getProperty(Constants.BNN_CONSTANT_C));
	    double paramE = Double.parseDouble(config.getProp().getProperty(Constants.BNN_CONSTANT_E));
	    double paramLambda = Double.parseDouble(config.getProp().getProperty(Constants.BNN_CONSTANT_LAMBDA));
	    
	    double prob = (paramC * paramE - DistanceR3.calc(neuronA, neuronB)) / paramLambda;
	    
	    return prob;
	}

    }
}
