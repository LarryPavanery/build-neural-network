package bnn.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import bnn.config.ConfigProp;
import bnn.constants.Constants;
import bnn.entities.Connection;

/**
 * @author larrypavanery
 * 
 */
public class ApplicationHelper {

    private static ConfigProp config = ConfigProp.getInstance();

    /**
     * @return Properties
     * @author larrypavanery
     */
    public static Properties loadsProperties() {
	InputStream input = null;
	Properties prop = new Properties();
	try {
	    input = new FileInputStream(Constants.NAME_FILE_PROPERTIES);
	    prop.load(input);

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (input != null) {
		    input.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return prop;
    }

    /**
     * @return random between 0.0 and 1.0
     * @author larrypavanery
     */
    public static double getRandomWeight() {
	return Math.random();
    }

    /**
     * @return date now | MASK_YYYYMMDDHHMMSS
     */
    public static String timeStamp() {
	return String.format("%s", 
		new SimpleDateFormat(Constants.MASK_YYYYMMDDHHMMSS)
	.format(new GregorianCalendar().getTime()));
    }

    /**
     * @return %.<bnn.decimal>f
     */
    public static String getFormatDecimal() {
	return String.format("%%.%df%s", 
		Integer.parseInt(config.getProp()
			.getProperty(Constants.BNN_DECIMAL)),
			Constants.SEPARATOR_LINE);
    }

    /**
     * @return 0.<bnn.decimal*0>
     */
    public static String getNullOutput() {
	StringBuilder sb = new StringBuilder();
	sb.append("0.");
	for (int i = 0; 
		i < Integer.parseInt(config.getProp()
			.getProperty(Constants.BNN_DECIMAL)); 
		i++) {
	    sb.append("0");
	}
	return String.format(getFormatDecimal(), 
		Double.parseDouble(sb.toString()));
    }

    /**
     * @return String
     */
    public static String getHeader() {
	StringBuilder sb = new StringBuilder();
	int size = getSizeNetwork();
	int sizeForm = getNullOutput().length(); 

	for (int i = 1; i <= size; i++) {
	    sb.append(
		    String.format("%s%d%s", 
			    config.getProp()
			    .getProperty(Constants.BNN_COLUMN_NAME_FILE), 
			    i, 
			    Constants.SEPARATOR_LINE));
	    if (i != size) {
		/* Define layout */
		sb.append(getSpaces(sizeForm - 4));
	    }
	}
	sb.deleteCharAt(sb.length() - 1);
	return sb.append("\n").toString();
    }

    /**
     * @param size
     * @return String
     */
    private static String getSpaces(int size) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < size; i++) {
	    sb.append(Constants.SPACE);
	}
	return sb.toString();
    }

    /**
     * @param greaterId
     * @param id
     * @return String
     */
    public static String getSpaces(String lessId, String id) {
	StringBuilder sb = new StringBuilder();
	sb.append(lessId);

	if (id.length() > lessId.length()) {
	    for (int i = 0; i < (id.length() - lessId.length()); i++) {
		sb.deleteCharAt(0);
	    }    
	}

	return sb.toString();
    }

    /**
     * @param connections
     */
    public static void initListConnection(final List<Connection> connections, final int id) {
	int i = 0;

	try {
	    connections.addAll(new ArrayList<Connection>(ApplicationHelper.getSizeNetwork()));

	    for (; i < getSizeNetwork(); i++) {
		connections.add(new Connection());
	    }

	} catch (OutOfMemoryError ome) {
	    System.out.printf("Neuron id:[%d]. Connection index[%d]. Error:[%s].\n", id, i, ome.getMessage());
	}
    }

    /**
     * @return int
     */
    public static int getSizeNetwork() {
	return Integer.parseInt(config.getProp()
		.getProperty(Constants.BNN_SIZE_NEURON_X))
		* Integer.parseInt(config.getProp()
			.getProperty(Constants.BNN_SIZE_NEURON_Y))
			* Integer.parseInt(config.getProp()
				.getProperty(Constants.BNN_SIZE_NEURON_Z));
    }

    /**
     * @return String
     */
    public static String getNameOutputDir() {

	return config.getProp()
		.getProperty(Constants.BNN_OUTPUT_DIR) 
		+ "/" 
		+ config.getProp()
		.getProperty(Constants.BNN_PREFIX_FILE_NAME)
		+ ApplicationHelper.timeStamp() 
		+ Constants.DOT 
		+ config.getProp()
		.getProperty(Constants.BNN_EXTENSION);
    }

    /**
     * @return double between 0.0 and 1.0
     */
    public static double getRandomRoulette() {
	return Math.random();
    }

    /**
     * @return String 
     */
    public static String getTipo() {
	return getRandomRoulette() 
		<= 0.2D ? Constants.TIPO_I : Constants.TIPO_E;
    }
}
