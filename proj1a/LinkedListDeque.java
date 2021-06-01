public class LinkedListDeque<T> {
    private int size; //The size of the double ended queue
    private GNode sentinel; // We will apply the circular sentinel technique
    /**Define the Generalized Node
     * Here, we want a double ended, which involves prev*/
    private class GNode {
        private T item;
        private GNode next;
        private GNode prev;
        public GNode(T input, GNode n, GNode p) {
            item = input;
            next = n;
            prev = p;
        }
    }
    /**The constructor that create an empty ListDeque */
    public LinkedListDeque() {
        size = 0;
        sentinel = new GNode((T) null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    //Add an element to the list in the front.
    public void addFirst(T input) {
        sentinel.next = new GNode(input, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    /**Add an element to the end of the list*/
    public void addLast(T input) {
        sentinel.prev = new GNode(input, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    /**Check if the list is empty, need modifications
     * when we use the double ended queue */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /**Return the size of the list,
     * probably do need to modify*/
    public int size() {
        return size;
    }
    /**Print the list, would there be better version?*/
    public void printDeque() {
        GNode cursor = sentinel;
        System.out.println("======Printing process Start======");
        while (cursor.next != sentinel) {
            cursor = cursor.next;
            System.out.print(cursor.item);
            System.out.print(" ");
        }
        System.out.println("\n======Printing process End======");
    }
    /**I think it would be better removing unnecessary references*/
    public T removeFirst() {
        if (size == 0) { // empty
            return null;
        }
        T rValue = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return rValue;
    }
    /**Not that concise, may be we can improve it after adding
     * next/previous  */
    public T removeLast() {
        if (size == 0) { //only one node
            return null;
        }
        T rValue = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return rValue;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        GNode cursor = sentinel;
        for (int i = 0; i < (index + 1); i++) {
            cursor = cursor.next;
        }
        return cursor.item;
    }
    /**Helper method for the recursive method*/
    public T recurHelper(GNode origin, int index) {
        if (index == 0) {
            return origin.item;
        }
        index--;
        return recurHelper(origin.next, index);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error: Index out of bound!");
            return null;
        }
        return recurHelper(sentinel.next, index);
    }
}
