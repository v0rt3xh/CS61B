import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        Queue<Item> tmp = unsorted;
        int currentSize = unsorted.size();
        for (int i = 0; i < currentSize; i++) {
            Item candidate = tmp.dequeue();
            if (candidate.compareTo(pivot) == 0) {
                equal.enqueue(candidate);
            } else if (candidate.compareTo(pivot) > 0) {
                greater.enqueue(candidate);
            } else {
                less.enqueue(candidate);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        Queue<Item> starter = items;
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        Queue<Item> exhaust;
        if (starter.size() <= 1) {
            return starter;
        }
        partition(starter, getRandomItem(starter), less, equal, greater);
        exhaust = catenate(quickSort(less), equal);
        return catenate(exhaust, quickSort(greater));
    }
    public static void main(String[] args) {
        Queue<String> theTask = new Queue<>();
        theTask.enqueue("Sheboygan");
        theTask.enqueue("Milwaukee");
        theTask.enqueue("Lafayette");
        theTask.enqueue("Chicago");
        theTask.enqueue("Seattle");
        theTask.enqueue("Francisco");

        System.out.println("Unsorted objects:");
        for (String s : theTask) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
        System.out.println("Sorted objects:");
        Queue<String> solvedTask = quickSort(theTask);
        for (String s : solvedTask) {
            System.out.print(s + " ");
        }
        System.out.print("\n");
    }
}
