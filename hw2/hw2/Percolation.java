package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] openStatus;
    private int range;
    private int openRecords;
    private WeightedQuickUnionUF connectRecords;
    private WeightedQuickUnionUF backWashQUUF;
    private int TOP;
    private int BOTTOM;
    //constructor
    public Percolation(int N) {
        if (N <= 0) {
            throw (new IllegalArgumentException("bruh, give a positive integer"));
        }
        range = N;
        openStatus = new boolean[N * N];
        openRecords = 0;
        connectRecords = new WeightedQuickUnionUF(N * N + 2);
        backWashQUUF = new WeightedQuickUnionUF(N * N + 1);
        TOP = range * range;
        BOTTOM = range * range + 1;
        //create the so called virtual top N bottom
        int bottomIndex;
        for (int i = 0; i < range; i++) {
            connectRecords.union(TOP, i);
            backWashQUUF.union(TOP, i);
            bottomIndex = indexAdapter(range - 1, i);
            connectRecords.union(BOTTOM, bottomIndex);
        }

    }
    //Wow, using 1-D array would be enough...
    private int indexAdapter(int row, int col) {
        if (row >= range || row < 0 || col >= range || col < 0) {
            throw (new IllegalArgumentException("Out of scope!!"));
        }
        return row * range + col;
    }
    private void connectHelper(int row, int col) {
        int currentIndex = indexAdapter(row, col);
        int[] indexRadar = {currentIndex - range, currentIndex + 1,
                            currentIndex + range, currentIndex - 1};
        if (row == 0) {
            indexRadar[0] = -1;
        }
        if (col == range - 1) {
            indexRadar[1] = -1;
        }
        if (row == range - 1) {
            indexRadar[2] = -1;
        }
        if (col == 0) {
            indexRadar[3] = -1;
        }
        for (int indices : indexRadar) {
            if (indices < 0) {
                continue;
            }
            if (!openStatus[indices]) {
                continue;
            }
            //the connecting method
            connectRecords.union(currentIndex, indices);
            backWashQUUF.union(currentIndex, indices);
        }

    }

    public void open(int row, int col) {
        int index = indexAdapter(row, col);
        if (!openStatus[index]) {
            openStatus[index] = true;
            openRecords += 1;
            connectHelper(row, col);
        }

    }

    public boolean isOpen(int row, int col) {
        int index = indexAdapter(row, col);
        return openStatus[index];
    }

    public boolean isFull(int row, int col) {
        if (row >= range || row < 0 || col >= range || col < 0) {
            throw (new IllegalArgumentException("Out of scope!!"));
        }
        int currentIndex = indexAdapter(row, col);
        return backWashQUUF.connected(TOP, currentIndex) & openStatus[currentIndex];
    }

    public int numberOfOpenSites() {
        return openRecords;
    }

    public boolean percolates() {
        return connectRecords.connected(TOP, BOTTOM);
    }

    public static void main(String[] args) {
    }

}
