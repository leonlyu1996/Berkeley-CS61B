package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import jdk.nashorn.api.tree.VariableTree;

import java.util.LinkedList;

public class Percolation {

    private int size;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF fullGrid;
    private boolean[] sites;
    private int numOfSites;

    private int virtualTop;
    private int virtualBottom;



    private int xyTo1D(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
        return x * size + y;
    }

    private LinkedList<Integer> neighbors(int row, int col) {

        LinkedList<Integer> neighborList = new LinkedList<>();

         for (int i = -1; i <= 1; i += 2) {
             if (row + i >= 0 && row + i < size) {
                 int index = xyTo1D(row + i, col);
                 neighborList.add(index);
             }
         }

         for (int j = -1; j <= 1; j += 2) {
             if (col + j >= 0 && col + j < size) {
                 int index = xyTo1D(row, col + j);
                 neighborList.add(index);
             }
         }

    return neighborList;

    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException("N must be bigger than 0");
        }

        grid = new WeightedQuickUnionUF(N * N + 2);
        fullGrid = new WeightedQuickUnionUF(N * N + 1);
        size = N;
        numOfSites = 0;
        sites = new boolean[N * N];
        virtualTop = N * N;
        virtualBottom = N * N + 1;

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (!isOpen(row, col)) {

            int index = xyTo1D(row, col);
            sites[index] = true;
            numOfSites += 1;

            for (int neighborIndex : neighbors(row, col)) {

                if (sites[neighborIndex]) {
                    grid.union(index, neighborIndex);
                    fullGrid.union(index, neighborIndex);
                }

            }

            if (row == 0) {
                grid.union(index, virtualTop);
                fullGrid.union(index, virtualTop);
            }

            if (row == size - 1) {
                grid.union(index, virtualBottom);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return grid.connected(index, virtualTop) && fullGrid.connected(index, virtualTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(virtualTop, virtualBottom);
    }

    // use for unit testing (not required)
    //public static void main(String[] args) {

    //}


}                       
