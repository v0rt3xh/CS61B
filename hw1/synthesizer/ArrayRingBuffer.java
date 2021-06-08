package synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("The ring buffer is full QAQ \n");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // Add exception
        if (fillCount == 0) {
            throw new RuntimeException("Bruh, the buffer is empty.");
        }
        T tmp = rb[first];
        fillCount -= 1;
        first = (first + 1) % capacity;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Bruh, the buffer is empty.");
        }
        return rb[first];
    }


    private class IterWizard implements Iterator<T> {
        private int wizardPos;
        private int secretCounter;
        public IterWizard() {
            wizardPos = first;
            secretCounter = 0;
        }

        public boolean hasNext() {
            return secretCounter < fillCount();
        }
        public T next() {
            T returnValue = rb[wizardPos];
            wizardPos = (wizardPos + 1) % capacity;
            secretCounter += 1;
            return returnValue;
        }

    }
    @Override
    public Iterator<T> iterator() {
        return new IterWizard();
    }

}
