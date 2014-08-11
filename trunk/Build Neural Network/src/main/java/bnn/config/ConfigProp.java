/**
 * 
 */
package bnn.config;

import java.util.Properties;

import bnn.util.ApplicationHelper;

/**
 * @author larrypavanery
 *
 */
public class ConfigProp {

    /* Loads properties bnn.properties */
    private Properties prop;
    private static ConfigProp configProp;
    
    /**
     * @author larrypavanery
     */
    private ConfigProp() {
	setProp(ApplicationHelper.loadsProperties());
    }
    
    public static final ConfigProp getInstance() {
	if (configProp == null) {
	    configProp = new ConfigProp();
	}

	return configProp;
    }
    
    /**
     * @return the prop
     */
    public final Properties getProp() {
        return prop;
    }

    /**
     * @param prop the prop to set
     */
    public final void setProp(Properties prop) {
        this.prop = prop;
    }
}
