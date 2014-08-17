/**
 * 
 */
package bnn.entities;


/**
 * @author larrypavanery
 *
 */
public class Edge {
    
    private Neuron neuronFrom;
    private Neuron neuronTo;
    private Weight weight; 
    
    /**
     * @param neuronFrom
     * @param neuronTo
     */
    public Edge(Neuron neuronFrom, Neuron neuronTo, Weight weight) {
	this.neuronFrom = neuronFrom;
	this.neuronTo = neuronTo;
	this.weight = weight;
	
	neuronFrom.getEdges()
		.set(
			neuronTo.getIndex(), 
			this
			);
    }
    
    public Edge() {
    }

    /**
     * @return the neuronFrom
     */
    public Neuron getNeuronFrom() {
        return neuronFrom;
    }

    /**
     * @param neuronFrom the neuronFrom to set
     */
    public void setNeuronFrom(Neuron neuronFrom) {
        this.neuronFrom = neuronFrom;
    }

    /**
     * @return the neuronTo
     */
    public Neuron getNeuronTo() {
        return neuronTo;
    }

    /**
     * @param neuronTo the neuronTo to set
     */
    public void setNeuronTo(Neuron neuronTo) {
        this.neuronTo = neuronTo;
    }

    /**
     * @return the weight
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Weight weight) {
        this.weight = weight;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder()
        .append("From: ").append(getNeuronFrom())
        .append(", To: ").append(getNeuronTo())
        .append(", Weight: ").append(getWeight())
        .append(".")
        .toString();
    }

    /**
     * @return boolean
     */
    public boolean isActive() {
	return getNeuronFrom() != null 
		&& getNeuronTo() != null 
		&& getWeight() != null;
    }
}
