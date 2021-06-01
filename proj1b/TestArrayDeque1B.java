import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
public class TestArrayDeque1B {
    @Test
    public void testInput(){
        StudentArrayDeque<Integer> a1= new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a2= new ArrayDequeSolution<>();
        //We will test a case:
        //Adding elements -> removing all -> adding new ones
        int testNum=StdRandom.poisson(5f);
        a1.addFirst(6);
        a2.addFirst(6);
        assertEquals(a1.removeLast(),a2.removeLast());
        for(int i=0;i<testNum;i++){
            int addElement=StdRandom.poisson(40.0);
            a1.addFirst(addElement);
            a2.addFirst(addElement);
            addElement=StdRandom.poisson(40.0);
            a1.addLast(addElement);
            a2.addLast(addElement);
        }
        assertEquals(a1.removeLast(),a2.removeLast());//seems to work just fine
        //consider resizing
        for(int i=0;i<50;i++){
            int addElement=StdRandom.poisson(40.0);
            a1.addFirst(addElement);
            a2.addFirst(addElement);
        }
        assertEquals(a1.removeLast(),a2.removeLast());
    }

}
