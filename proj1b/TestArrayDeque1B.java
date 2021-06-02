import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDeque1B {
    @Test
    public void testInput() {
        StudentArrayDeque<Integer> a1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a2 = new ArrayDequeSolution<>();
        OperationSequence OpRecord = new OperationSequence();
        //We will test a case:
        //Adding elements -> removing all -> adding new ones
        int testNum = 8;
        for (int i = 0; i < testNum; i++) {
            double randomChoice = StdRandom.uniform();
            if (randomChoice > .5) {
                a1.addFirst(i);
                DequeOperation a1Record = new DequeOperation("addFirst",i);
                a2.addFirst(i);
                OpRecord.addOperation(a1Record);
            }
            else {
                a1.addLast(i);
                DequeOperation a1Record = new DequeOperation("addLast",i);
                a2.addLast(i);
                OpRecord.addOperation(a1Record);
            }
        }
        for (int j = 0; j < testNum; j++) {
            double randomChoice2 = StdRandom.uniform();
            if (randomChoice2 > .5) {
                Integer studentSol = a1.removeFirst();
                DequeOperation a1Record = new DequeOperation("removeFirst");
                Integer officialSol = a2.removeFirst();
                OpRecord.addOperation(a1Record);
                assertEquals(officialSol, studentSol);
            }
            else {
                Integer studentSol = a1.removeLast();
                DequeOperation a1Record = new DequeOperation("removeLast");
                Integer officialSol = a2.removeLast();
                OpRecord.addOperation(a1Record);
                assertEquals(OpRecord.toString(),officialSol, studentSol);
            }
        }

    }

}
