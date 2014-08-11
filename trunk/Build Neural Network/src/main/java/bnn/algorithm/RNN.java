/**
 * 
 */
package bnn.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bnn.config.ConfigProp;
import bnn.constants.Constants;
import bnn.entities.Distance;
import bnn.entities.Neuron;
import bnn.entities.Weight;
import bnn.util.ApplicationHelper;

/**
 * @author larrypavanery
 */
public class RNN {

    private static ConfigProp config = ConfigProp.getInstance();

    /**
     * @author larrypavanery
     * @return neural network
     */
    public static final Map<Neuron, List<Neuron>> buildNeuralNetwork() {

	Map<Neuron, List<Neuron>> network = new HashMap<Neuron, List<Neuron>>();
	List<Neuron> lstNeurons = buildNeurons();
	List<Neuron> lstNeuronsTmp;

	for (Neuron neuron : lstNeurons) {
	    for (Neuron subNeuron : lstNeurons) {
		if (neuron.getNeighbor().isNeighbor(subNeuron)) {
		    if (network.get(neuron) == null) {
			lstNeuronsTmp = new ArrayList<Neuron>(
				Integer.parseInt(config.getProp().getProperty(Constants.BNN_SIZE_NEURON))
				);
			ApplicationHelper.initList(lstNeuronsTmp);

			lstNeuronsTmp.set(subNeuron.getIndex(), subNeuron);
			network.put(neuron, lstNeuronsTmp);
		    } else {
			lstNeuronsTmp = network.get(neuron);
			lstNeuronsTmp.set(subNeuron.getIndex(), subNeuron);
			network.put(neuron, lstNeuronsTmp);
		    }
		}
	    }
	}
	return network;
    }

    /**
     * @author larrypavanery
     * @return list with neurons
     */
    public static final List<Neuron> buildNeurons() {
	List<Neuron> lstNeurons = new ArrayList<Neuron>();

	int distanceX = 1;
	int distanceY = 1;
	int distanceZ = 1;
	int id = 1;

	for (int idx = 0; 
		idx < Integer.parseInt(config.getProp().getProperty(Constants.BNN_SIZE_NEURON)); 
		idx++) {
	    lstNeurons.add(
		    new Neuron(
			    idx,
			    config.getProp().getProperty(Constants.BNN_COLUMN_NAME_FILE) + id++,
			    new Weight(ApplicationHelper.getRandomWeight()), 
			    new Distance(distanceX++),
			    new Distance(distanceY++),
			    new Distance(distanceZ++)
			    )
		    );
	}
	return lstNeurons;
    }

    /**
     * @author larrypavanery
     */
    public static final void buildFileOutputNetwork() {
	PrintWriter pw = null;
	try {
	    String file = ApplicationHelper.getNameOutputDir();

	    pw = new PrintWriter(new File(file));

	    String ouput = buildStringOutputNetwork(); 
	    
	    pw.write(ouput);
	    pw.flush();

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (pw != null) {
		pw.close();
	    }
	}
    }

    /**
     *      rn-1; rn-2; rn-3; ... rn-N
     * nr-1
     * nr-2
     * nr-3
     * ...
     * nr-N
     * @author larrypavanery
     * @return String
     */
    public static final String buildStringOutputNetwork() {
	StringBuilder sb = new StringBuilder();
	Map<Neuron, List<Neuron>> network = buildNeuralNetwork();

	String lessId = ApplicationHelper.getLenghtSpaceLessId(network);

	List<Neuron> keys = new ArrayList<Neuron>();
	keys.addAll(network.keySet());
	Collections.sort(keys);
	sb.append(ApplicationHelper.getHeader(lessId));

	for (Neuron neuronKey : keys) {
	    for (Neuron neuronValue : network.get(neuronKey)) {
		if (neuronValue.isLive()) {
		    sb.append(
			    String.format(ApplicationHelper.getFormatDecimal(), neuronValue.getWeight().getValue()))
			    .append(Constants.SEPARATOR_LINE);
		} else {
		    sb.append(ApplicationHelper.getNullOutput()).append(Constants.SEPARATOR_LINE);
		}
	    }
	    sb.deleteCharAt(sb.length() - 1);
	    sb.append("\n");
	}
	return sb.toString();
    }
}