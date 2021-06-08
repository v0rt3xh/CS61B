package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < 3 ; i++) {
            arb.enqueue(3);
            arb.dequeue();
            arb.enqueue(i);
        }
        arb.enqueue(6);
        assertEquals(1 ,(int) arb.dequeue());
        assertEquals(3 ,(int) arb.dequeue());
        assertEquals(2 ,(int) arb.dequeue());
        assertEquals(6 ,(int) arb.dequeue());
    }
    @Test
    public void OtherTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < 100 ; i++) {
            arb.enqueue(i);
            arb.dequeue();
        }
        arb.enqueue(7);
        assertEquals(7, (int) arb.peek());
    }
    @Test
    public void testIterator(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(5);
        for (int i = 0; i < 5 ; i++) {
            arb.enqueue(i);
        }
        for (Integer x : arb) {
            for (Integer y:arb) {
                System.out.println(x + " o_O " + y);
            }
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
