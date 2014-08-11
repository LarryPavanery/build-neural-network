/**
 * 
 */
package bnn.entities;


/**
 * @author larrypavanery
 *
 */
public class Weight {

    private double weight;

    /**
     * @param weight
     * @author larrypavanery
     */
    public Weight(double weight) {
	this.weight = weight;
    }

    /**
     * @return the weight
     * @author larrypavanery
     */
    public final double getValue() {
	return weight;
    }

    /**
     * @param weight the weight to set
     * @author larrypavanery
     */
    public final void setWeight(double weight) {
	this.weight = weight;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return String.valueOf(getValue());
    }
}
