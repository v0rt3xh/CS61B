package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] curBoard;
    private int width;
    private final int BLANK = 0;
    public Board(int[][] tiles) {
        width = tiles[0].length;
        curBoard = new int[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                curBoard[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i > width - 1 || j > width - 1) {
            throw new IndexOutOfBoundsException("Out of Bound!");
        }
        return curBoard[i][j];
    }
    public int size() {
        return width;
    }
    /**Use the version provided by Josh Hug*/
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>(); // Use a queue to store neighbors.
        int w = size(); // obtain the size of current board
        int blankW = -1;
        int blankH = -1;
        // Find the blank tile
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                if (tileAt(i, j) == BLANK) {
                    blankW = i;
                    blankH = j;
                }
            }
        }
        // We need a interim matrix (Generate neighbors from here)
        int[][] generator = new int[w][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                // Now, just copy the current board.
                generator[i][j] = tileAt(i, j);
            }
        }
        // Now we start the generate process
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                // Notice our neighbor condition:
                // Either width & height's difference is exactly one
                // (For the BLANK element)
                if (Math.abs(-blankW + i) + Math.abs(j - blankH) - 1 == 0) {
                    // Swap BLANK & original element.
                    generator[blankW][blankH] = generator[i][j];
                    generator[i][j] = BLANK;
                    Board neighbor = new Board(generator);
                    neighbors.enqueue(neighbor);
                    // Switch back to the original one,
                    // Continue generating.
                    generator[i][j] = generator[blankW][blankH];
                    generator[blankW][blankH] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int hammingDist = 0;
        int N = size();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    continue; // Why?
                    //Scenario 1, the last one is blank,
                    //No need to add Dist
                    //Scenario 2, the last one is not blank,
                    //The wrong distance num has been added somewhere before.
                }
                if (curBoard[i][j] != i * N + j + 1) {
                    hammingDist += 1;
                }
            }
        }
        return hammingDist;
    }

    public int manhattan() {
        int N = size();
        int manhattanDist = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (curBoard[i][j] == BLANK) {
                    //Do not need to compute the distance in this case.
                    continue;
                }
                if (curBoard[i][j] != i * width + j + 1) {
                    // Now compute the distance to the correct position
                    int rightW = (curBoard[i][j] - 1) / width;
                    int rightH = (curBoard[i][j] - 1) % width;
                    manhattanDist += Math.abs(rightH - j) + Math.abs(rightW - i);
                }

            }
        }
        return manhattanDist;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;

        if (that.size() != this.size()) {
            return false;
        }
        int currentSize = this.size();
        for (int i = 0; i < currentSize; i++) {
            for (int j = 0; j < currentSize; j++) {
                if (that.curBoard[i][j] != this.curBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode(){
        return estimatedDistanceToGoal();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
