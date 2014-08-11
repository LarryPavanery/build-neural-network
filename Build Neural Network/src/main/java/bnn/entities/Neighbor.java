/**
 * 
 */
package bnn.entities;

import bnn.config.ConfigProp;
import bnn.constants.Constants;
import bnn.functions.Function;

/**
 * @author larrypavanery
 *
 */
public class Neighbor {

    private static ConfigProp config = ConfigProp.getInstance();
    private Neuron neuron;
    
    /**
     * @author larrypavanery
     */
    public Neighbor(Neuron neuron) {
	this.neuron = neuron;
    }
    
    /**
     * @author larrypavanery
     * @param neuron
     * @return boolean
     */
    public boolean isNeighbor(Neuron neuron) {
	if (!this.neuron.equals(neuron)) {
	    if (Integer.parseInt(config.getProp().getProperty(Constants.BNN_ROULETTE))
		    < Math.abs(Function.ConnectionProbability.calc(this.neuron, neuron))
		    ) {
		return true;
	    }
	}
	return false;
    }

    /**
     * @return the neuron
     */
    public final Neuron getNeuron() {
        return neuron;
    }

    /**
     * @param neuron the neuron to set
     */
    public final void setNeuron(Neuron neuron) {
        this.neuron = neuron;
    }

    /**
     * @return the config
     */
    public static final ConfigProp getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public static final void setConfig(ConfigProp config) {
        Neighbor.config = config;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return String.valueOf(-1);
    }
}
