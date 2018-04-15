package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] threshold;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        if (N <=0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("N and T must be bigger than 0");
        }

        threshold = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;
            while(!p.percolates()) {

                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);

                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count++;
                }

            }
            threshold[i] = (double)count/(double)(N*N);

        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(100, 1000);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }

}                       
