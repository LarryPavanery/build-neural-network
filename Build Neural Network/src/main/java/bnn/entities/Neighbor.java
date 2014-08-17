/**
 * 
 */
package bnn.entities;

import bnn.functions.Function;
import bnn.util.ApplicationHelper;

/**
 * @author larrypavanery
 *
 */
public class Neighbor {

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
	    if (ApplicationHelper.getRandomRoulette()
		    < Function.ConnectionProbability
		    .calc(this.neuron, neuron)
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return String.valueOf(-1);
    }
}
