/**
 * 
 */
package bnn.entities;

import java.util.ArrayList;
import java.util.List;

import bnn.util.ApplicationHelper;

/**
 * @author larrypavanery
 * 
 */
public class Neuron {

    private String id;
    private int index;
    /**
     * E - Excitador; I - Inibidor
     */
    private String tipo;
    private Distance distanceX;
    private Distance distanceY;
    private Distance distanceZ;
    private Neighbor neighbor;
    private List<Edge> edges;

    /**
     * @author larrypavanery
     * @param index
     * @param id
     * @param tipo
     * @param distanceX
     * @param distanceY
     * @param distanceZ
     */
    public Neuron(int index, String id, String tipo, Distance distanceX, Distance distanceY, Distance distanceZ) {
	this.id = id;
	this.index = index;
	this.tipo = tipo;
	this.distanceX = distanceX;
	this.distanceY = distanceY;
	this.distanceZ = distanceZ;
	this.neighbor = new Neighbor(this);
	/* init list sizeless */
	this.edges = new ArrayList<Edge>(0);

	ApplicationHelper.initListEdge(this.edges, this.index);
    }

    public Neuron() {}

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

    /**
     * @return the edge
     */
    public List<Edge> getEdges() {
	return edges;
    }

    /**
     * @param edge the edge to set
     */
    public void setEdges(List<Edge> edge) {
	this.edges = edge;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb
	.append("Index: ").append(getIndex())
	.append(", Id: ").append(getId())
	.append(", DistanceX: ").append(getDistanceX())
	.append(", DistanceY: ").append(getDistanceY())
	.append(", DistanceZ: ").append(getDistanceZ())
	.append(".")
	;

	return sb.toString();
    }
}
