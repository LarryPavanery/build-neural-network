/**
 * 
 */
package bnn.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bnn.config.ConfigProp;
import bnn.constants.Constants;
import bnn.entities.Connection;
import bnn.entities.Distance;
import bnn.entities.Neuron;
import bnn.entities.Weight;
import bnn.util.ApplicationHelper;

/**
 * @author larrypavanery
 */
public class BNN {

    private static ConfigProp config = ConfigProp.getInstance();
    private static List<Neuron> network;

    /**
     * PS: -Xms256M -Xmx4056M for Heap 
     * -Xms<size> - Set initial Java heap size
     * -Xmx<size> - Set maximum Java heap size
     * 
     * See more http://www.mkyong.com/eclipse/eclipse-java-lang-outofmemoryerror-java-heap-space/
     * 
     * @author larrypavanery
     * @return neural network
     */
    public static final List<Neuron> buildNeuralNetwork() {
	network = buildNeurons();
	boolean withThread = Boolean.parseBoolean(config.getProp()
		.getProperty(Constants.WITH_THREAD));

	for (final Neuron neuron : network) {
	    if (withThread) {
		new Thread(new Runnable() {

		    @Override
		    public void run() {
			for (Neuron subNeuron : network) {
			    if (neuron.getNeighbor().isNeighbor(subNeuron)) {
				new Connection(
					neuron, 
					subNeuron, 
					new Weight(ApplicationHelper.getRandomWeight())
					);
			    }
			}
		    }
		}).start();

	    } else {
		for (Neuron subNeuron : network) {
		    if (neuron.getNeighbor().isNeighbor(subNeuron)) {
			new Connection(
				neuron, 
				subNeuron, 
				new Weight(ApplicationHelper.getRandomWeight())
				);
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
	List<Neuron> lstNeurons = new ArrayList<Neuron>(
		ApplicationHelper.getSizeNetwork()
		);

	String columnName = config.getProp()
		.getProperty(Constants.BNN_COLUMN_NAME_FILE);
	int idx = 0;
	int id = 1;

	for (int distanceX = 0; 
		distanceX < Integer.parseInt(config.getProp()
			.getProperty(Constants.BNN_SIZE_NEURON_X)); 
		distanceX++) {
	    for (int distanceY = 0; 
		    distanceY < Integer.parseInt(config.getProp()
			    .getProperty(Constants.BNN_SIZE_NEURON_Y)); 
		    distanceY++) {
		for (int distanceZ = 0; 
			distanceZ < Integer.parseInt(config.getProp()
				.getProperty(Constants.BNN_SIZE_NEURON_Z)); 
			distanceZ++) {
		    lstNeurons.add(
			    new Neuron(
				    idx++,
				    columnName + id++,
				    ApplicationHelper.getTipo(), 
				    new Distance(distanceX),
				    new Distance(distanceY),
				    new Distance(distanceZ)
				    )
			    );
		}
	    }
	}
	return lstNeurons;
    }

    /**
     * @author larrypavanery
     */
    public static final void buildFileOutputNetwork() {
	StringBuilder sb = new StringBuilder();
	network = buildNeuralNetwork();
	PrintWriter pw = null;
	
	try {
	    pw = new PrintWriter(new File(
		    ApplicationHelper.getNameOutputDir()));

	    pw.write(ApplicationHelper.getHeader());

	    for (Neuron neuron : network) {
		for (Connection connection : neuron.getConnections()) {
		    if (connection.isActive()) {
			sb.append(
				String.format(ApplicationHelper
					.getFormatDecimal(), connection.getWeight().getValue())
				);
		    } else {
			sb.append(ApplicationHelper.getNullOutput());
		    }
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\n");
		pw.write(sb.toString());
		sb = new StringBuilder();
	    }

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
	network = buildNeuralNetwork();

	sb.append(ApplicationHelper.getHeader());

	for (Neuron neuron : network) {
	    for (Connection connection : neuron.getConnections()) {
		if (connection.isActive()) {
		    sb.append(
			    String.format(ApplicationHelper
				    .getFormatDecimal(), connection.getWeight().getValue())
			    );
		} else {
		    sb.append(ApplicationHelper.getNullOutput());
		}
	    }
	    sb.deleteCharAt(sb.length() - 1);
	    sb.append("\n");
	}

	return sb.toString();
    }
}