public class ArrayDequeTest {
    @org.junit.Test
    public void TestAdd(){
        ArrayDeque<Integer> l1=new ArrayDeque<>();
        l1.addFirst(4);
        l1.addLast(7);
        l1.addFirst(8);
        l1.addFirst(9);
        l1.addLast(5);
        l1.addFirst(10);
        l1.addLast(6);
        l1.addLast(0);
        l1.printDeque();
        int actual=l1.get(0);
        org.junit.Assert.assertEquals(10,actual);
    }
    @org.junit.Test
    public void testRemove(){
        ArrayDeque<Integer> l1=new ArrayDeque<>();
        l1.addFirst(4);
        l1.addLast(7);
        l1.addFirst(8);
        l1.addFirst(9);
        l1.addLast(5);
        l1.addFirst(10);
        l1.removeFirst();
        l1.removeLast();
        int actual1=l1.get(0);
        int actual2=l1.get(3);
        org.junit.Assert.assertEquals(9,actual1);
        org.junit.Assert.assertEquals(7,actual2);
        l1.removeLast();
        l1.removeLast();
        l1.removeFirst();
        l1.removeFirst();
        l1.removeFirst();
        boolean actualEmpty=l1.isEmpty();
        org.junit.Assert.assertEquals(true,actualEmpty);
        l1.addFirst(9);
        l1.addLast(5);
        l1.addFirst(10);
        l1.printDeque();
    }
    @org.junit.Test
    public void testGet(){
        ArrayDeque<Integer> l1=new ArrayDeque<>(12);
        l1.addFirst(4);
        l1.addLast(7);
        l1.addFirst(8);
        l1.addFirst(9);
        l1.addLast(5);
        l1.addFirst(10);
        l1.get(10);
        int actual=l1.get(4);
        org.junit.Assert.assertEquals(12,actual);
    }
    @org.junit.Test
    public void testExpand(){
        ArrayDeque<Integer> l1=new ArrayDeque<>(12);
        l1.addFirst(3);
        l1.addLast(1);
        l1.addFirst(2);
        l1.addFirst(9);
        l1.addLast(5);
        l1.addFirst(10);
        l1.addFirst(8);
        l1.addFirst(7);
        int actual1=l1.size;
        org.junit.Assert.assertEquals(9,actual1);
        int actual2=l1.get(0);
        org.junit.Assert.assertEquals(7,actual2);
    }
    @org.junit.Test
    public void testShrink(){
        ArrayDeque<Integer> l1=new ArrayDeque<>(12);
        for(int i=0;i<18;i++){
            l1.addFirst(i);
        }
        for(int j=0;j<12;j++){
            l1.removeLast();
        }
        int actual1=l1.size();
        org.junit.Assert.assertEquals(7,actual1);
    }
}
