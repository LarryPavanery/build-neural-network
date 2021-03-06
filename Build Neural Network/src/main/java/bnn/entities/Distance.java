/**
 * 
 */
package bnn.entities;


/**
 * @author larrypavanery
 *
 */
public class Distance {

    private double distance;

    /**
     * @param distance
     * @author larrypavanery
     */
    public Distance(int distance) {
	this.distance = distance;
    }

    /**
     * @return the distance
     * @author larrypavanery
     */
    public final double getValue() {
	return distance;
    }

    /**
     * @param distance the distance to set
     * @author larrypavanery
     */
    public final void setDistance(Distance distance) {
	this.distance = distance.getValue();
    }
    
    @Override
    public String toString() {
	return String.valueOf(getValue());
    }
}
