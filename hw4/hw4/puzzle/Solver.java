package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;



import java.util.ArrayDeque;
import java.util.Deque;



public class Solver {
    // As mentioned in the description,
    // We need a helper class.
    private class SearchNode implements Comparable {
        private WorldState status;
        private int moves;
        private SearchNode prev;
        private int distance;
        private int eDistance;


        SearchNode(WorldState s, int m, SearchNode p) {
            status = s;
            moves = m;
            prev = p;
            eDistance = s.estimatedDistanceToGoal();
            distance = eDistance + m;
        }

        private boolean ifTerminated() {
            return eDistance == 0;
        }

        private Iterable<WorldState> welcomeNeighbors() {
            return status.neighbors();
        }

        @Override
        public int compareTo(Object o) {
            if (o.getClass() == this.getClass()) {
                SearchNode that = (SearchNode) o;
                return this.distance - that.distance;
            }
            return 0;
        }


        //Need other helper methods though.

    }
    private MinPQ<SearchNode> SolverPQ;
    private Deque<WorldState> solutionSequence;
    private SearchNode endNode;

    public Solver(WorldState initial) {
        SearchNode init = new SearchNode(initial, 0, null);
        SolverPQ = new MinPQ<>();
        SolverPQ.insert(init);
        //We solve the problem when calling the constructor Xd.
        //So? need a solverHelper
        while (!SolverPQ.isEmpty()) {
            SearchNode currentNode = SolverPQ.delMin();
            if (currentNode.ifTerminated()) {
                endNode = currentNode;
                break; // Yep, we have reached the goal.
            }
            // Iteratively search neighbors.
            for (WorldState w : currentNode.welcomeNeighbors()) {
                // Critical optimization needed
                if (currentNode.prev == null || !w.equals(currentNode.prev.status)) {
                    SearchNode newElement = new SearchNode(w, currentNode.moves + 1, currentNode);
                    SolverPQ.insert(newElement);
                }
            }
        }

    }
    public int moves() {
        return endNode.moves;
    }

    public Iterable<WorldState> solution() {
        solutionSequence = new ArrayDeque<>();
        SearchNode tmp = endNode;
        while (tmp != null) {
            solutionSequence.push(tmp.status);
            tmp = tmp.prev;
        }
        return solutionSequence;
    }


}
