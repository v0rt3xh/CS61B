public class ArrayDeque<T> {
    private int capacity = 8;
    private T[] items;
    private int size;
    private int first;
    private int last;

    /**Make it easier to find "before"*/
    private int minusOne(int index) {
        if (index > 0) {
            return index - 1;
        }
        return items.length - 1;
    }
    /**Make it easier to move "pointer"*/
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
    /**Resize function*/
    private void resize() {
        if (size == items.length) {
            expand();
        }
        float usage = (float) size / (float) items.length;
        if (usage < 0.25 && items.length > capacity) {
            shrink();
        }
    }
    /**shrink current storage array*/
    private void shrink() { //shrink by half
        int  newCapacity = items.length / 2;
        T[] tmp = (T[]) new Object[newCapacity];
        int start = (newCapacity - size) / 2;
        for (int i = start; i < (start + size); i++) {
            tmp[i] = this.get(i - start);
        }
        items = tmp;
        last = plusOne(start + size - 1);
        first = minusOne(start);
    }
    /**Expand current storage array*/
    private void expand() {
        int newCapacity = items.length * 2;
        T[] tmp = (T[]) new Object[newCapacity];
        int start = (newCapacity - size) / 2;
        for (int i = start; i < (start + size); i++) {
            tmp[i] = this.get(i - start);
        }
        items = tmp;
        last = plusOne(start + size - 1);
        first = minusOne(start);
    }
    /**Constructor*/
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[capacity];
        first = 2;
        last = 3;
    }
    /**Add an element to the front.*/
    public void addFirst(T input) {
        resize();
        items[first] = input;
        first = minusOne(first);
        size++;
    }
    /**Add an element to the end*/
    public void addLast(T input) {
        resize();
        if (size == 0) {
            addFirst(input);
            return;
        }
        items[last] = input;
        last = plusOne(last);
        size++;
    }
    /**Check if it's empty*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /**Return the size of our array*/
    public int size() {
        return size;
    }
    /**Print the ArrayDequeList*/
    public void printDeque() {
        System.out.println("======Printing process Start======");
        int counter = plusOne(first);
        while (counter != minusOne(last)) {
            System.out.print(items[counter] + " ");
            counter = plusOne(counter);
        }
        System.out.print(items[counter]);
        System.out.println("\n======Printing process End======");
    }
    /**Get the index-th element*/
    public T get(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error: The given index is out of bound");
            return (T) null;
        }
        int outIndex = (index + first+1) % items.length;
        return items[outIndex];
    }
    /**Remove the first element*/
    public T removeFirst() {
        if (size == 0) {
            return (T) null;
        }
        T outPut;
        first = plusOne(first);
        outPut = items[first];
        size--;
        resize();
        return outPut;
    }
    /**Remove the last element*/
    public T removeLast() {
        if (size == 0) {
            return (T) null;
        }
        T outPut;
        last = minusOne(last);
        outPut = items[last];
        size--;
        resize();
        return outPut;
    }
}
