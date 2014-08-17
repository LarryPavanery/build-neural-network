package bnn.main;

import bnn.algorithm.BNN;

/**
 * @author larrypavanery
 * 
 */
public class BNNMain {

    public static void main(String[] args) {
	double timeBegin = System.currentTimeMillis();

	/* Print network in file */
	BNN.buildFileOutputNetwork();

	/* Print network in output console */
	//		System.out.println(BNN.buildStringOutputNetwork());


	double timeEnd = System.currentTimeMillis();

	System.out.printf("\n\tTime for network generation: %.5fm.\n", ((timeEnd - timeBegin) / 1000.0D) / 60.0D);
	System.out.printf("\n\tTime for network generation: %.5fs.\n", (timeEnd - timeBegin) / 1000.0D);

    }
}
