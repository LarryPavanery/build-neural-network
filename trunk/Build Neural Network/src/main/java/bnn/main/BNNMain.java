/**
 * 
 */
package bnn.main;

import bnn.algorithm.RNN;

/**
 * @author larrypavanery
 * 
 */
public class BNNMain {

    public static void main(String[] args) {
	/* Print network in output console */
	//System.out.println(RNN.buildStringOutputNetwork());

	/* Print network in file */
	RNN.buildFileOutputNetwork();
    }
}
