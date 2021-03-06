package synthesizer;
import java.util.Iterator;
public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); //return size of the buffer.
    int fillCount(); //return current number of items.
    void enqueue(T x); //add item x to the end
    T dequeue(); //remove and return item from the front
    T peek(); //return but not delete item from the front
    default boolean isEmpty() {
        return fillCount() == 0;
    }
    default  boolean isFull() {
        return fillCount() == capacity();
    }
    Iterator<T> iterator();


}
