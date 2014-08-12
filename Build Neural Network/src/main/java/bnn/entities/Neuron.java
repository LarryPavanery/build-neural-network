/**
 * 
 */
package bnn.entities;

/**
 * @author larrypavanery
 * 
 */
public class Neuron implements Comparable<Neuron> {

    private String id;
    private int index;
    /**
     * E - Excitador; I - Inibidor
     */
    private String tipo;
    private Weight weight;
    private Distance distanceX;
    private Distance distanceY;
    private Distance distanceZ;
    private Neighbor neighbor;

    /**
     * @author larrypavanery
     * @param index
     * @param id
     * @param weight
     * @param distanceX
     * @param distanceY
     * @param distanceZ
     */
    public Neuron(int index, String id, String tipo, Weight weight, Distance distanceX, Distance distanceY, Distance distanceZ) {
	this.id = id;
	this.index = index;
	this.tipo = tipo;
	this.weight = weight;
	this.distanceX = distanceX;
	this.distanceY = distanceY;
	this.distanceZ = distanceZ;
	this.neighbor = new Neighbor(this);
    }

    public Neuron() {}

    /**
     * @return the weight
     */
    public final Weight getWeight() {
	return weight;
    }

    /**
     * @param weight the weight to set
     */
    public final void setWeight(Weight weight) {
	this.weight = weight;
    }

    /**
     * @return the id
     */
    public final String getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(String id) {
	this.id = id;
    }

    /**
     * @return the neighbor
     */
    public final Neighbor getNeighbor() {
	return neighbor;
    }

    /**
     * @param neighbor the neighbor to set
     */
    public final void setNeighbor(Neighbor neighbor) {
	this.neighbor = neighbor;
    }

    /**
     * @return the index
     */
    public final int getIndex() {
	return index;
    }

    /**
     * @param index the index to set
     */
    public final void setIndex(int index) {
	this.index = index;
    }

    public final boolean isLive() {
	return getId() != null && getWeight() != null;
    }

    /**
     * @return the distanceX
     */
    public final Distance getDistanceX() {
        return distanceX;
    }

    /**
     * @param distanceX the distanceX to set
     */
    public final void setDistanceX(Distance distanceX) {
        this.distanceX = distanceX;
    }

    /**
     * @return the distanceY
     */
    public final Distance getDistanceY() {
        return distanceY;
    }

    /**
     * @param distanceY the distanceY to set
     */
    public final void setDistanceY(Distance distanceY) {
        this.distanceY = distanceY;
    }

    /**
     * @return the distanceZ
     */
    public final Distance getDistanceZ() {
        return distanceZ;
    }

    /**
     * @param distanceZ the distanceZ to set
     */
    public final void setDistanceZ(Distance distanceZ) {
        this.distanceZ = distanceZ;
    }
    
    /**
     * @return the tipo
     */
    public final String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public final void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb
	.append("Index: ").append(getIndex())
	.append(", Id: ").append(getId())
	.append(", Weight: ").append(getWeight())
	.append(", DistanceX: ").append(getDistanceX())
	.append(", DistanceY: ").append(getDistanceY())
	.append(", DistanceZ: ").append(getDistanceZ())
	.append(".")
	;
	
	return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Neuron) {
	    return (((Neuron) obj).getId()).equalsIgnoreCase(this.getId());
	}
	return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Neuron o) {
	if (o.getIndex() < this.getIndex()) {
	    return 1;
	} else if (o.getIndex() > this.getIndex()) {
	    return -1;
	}
	return 0;
    }
}
