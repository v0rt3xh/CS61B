package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] openStorage;
    private int experimentT;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw (new IllegalArgumentException("bruh, give a positive integer"));
        }
        experimentT = T;
        openStorage = new double[experimentT];
        int gridSize = N * N;
        for (int i = 0; i < experimentT; i++) {
            Percolation experimentUnit = pf.make(N);
            while (!experimentUnit.percolates()) {
                int nextOpenX = StdRandom.uniform(N);
                int nextOpenY = StdRandom.uniform(N);
                if (experimentUnit.isOpen(nextOpenX, nextOpenY)) {
                    continue;
                }
                experimentUnit.open(nextOpenX, nextOpenY); //Not efficient, takes ages...
            }
            openStorage[i] = experimentUnit.numberOfOpenSites() * 1.0 / gridSize;
        }

    }
    // the prviate helper method that conduct the experiment.


    public double mean() {
        return StdStats.mean(openStorage);
    }

    public double stddev() {
        return StdStats.stddev(openStorage);
    }

    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(experimentT);
    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(experimentT);
    }

}
