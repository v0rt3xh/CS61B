import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> payCheck = new Queue<>();
        for (Item i : items) {
            Queue<Item> singleElement = new Queue<>();
            singleElement.enqueue(i);
            payCheck.enqueue(singleElement);
        }
        return payCheck;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> omega = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            //Place the smallest item into omega
            omega.enqueue(getMin(q1, q2));
        }
        return omega;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        //From least to greatest =D
        //Corner case
        int currentSize = items.size();
        if (currentSize <= 1) {
            return items;
        }
        Queue<Queue<Item>> bachelors = makeSingleItemQueues(items);
        while (bachelors.size() > 1) {
            Queue<Item> p1 = bachelors.dequeue();
            Queue<Item> p2 = bachelors.dequeue();
            bachelors.enqueue(mergeSortedQueues(p1, p2));
        }
        return  bachelors.dequeue();
    }

    //Should a useless paragraph at the beginning =D.
    public static void main(String[] args) {
        Queue<String> theTask = new Queue<>();
        theTask.enqueue("Sheboygan");
        theTask.enqueue("Milwaukee");
        theTask.enqueue("Lafayette");
        theTask.enqueue("Chicago");
        theTask.enqueue("Seattle");
        theTask.enqueue("Francisco");
        for (int i = 0; i < 10000; i++) {
            theTask.enqueue("Ghost");
        }
        System.out.println("Unsorted objects:");
        for (String s : theTask) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
        System.out.println("Sorted objects:");
        Queue<String> solvedTask = mergeSort(theTask);
        for (String s : solvedTask) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }

}
