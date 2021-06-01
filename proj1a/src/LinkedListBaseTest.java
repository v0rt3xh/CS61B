public class LinkedListBaseTest {
    /**We create a class to test the basic list*/
    @org.junit.Test
    /**Test if we successfully build a base linked list*/
    /**Also test if we define addFirst correctly*/
    public void testConstructAdd(){
        LinkedListDeque<Integer> l1=new LinkedListDeque<>();
        l1.addLast(6);
        l1.addFirst(2);
        l1.addFirst(8);
        l1.addLast(1);
        l1.addLast(5);
        l1.printDeque();
        int actual=l1.get(2);
        org.junit.Assert.assertEquals(6,actual);
        int actual2=l1.get(4);
        org.junit.Assert.assertEquals(5,actual2);
    }
    /**Test if remove first is correct.*/
    @org.junit.Test
    public void testRemoveFirst(){
        LinkedListDeque<Integer> l1=new LinkedListDeque<>(5);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(8);
        int actual=l1.removeFirst();
        //int actual=l1.get(0);
        org.junit.Assert.assertEquals(5,actual);
        actual=l1.removeFirst();
        org.junit.Assert.assertEquals(3,actual);
        actual=l1.removeFirst();
        org.junit.Assert.assertEquals(4,actual);
        actual=l1.removeFirst();
        org.junit.Assert.assertEquals(8,actual);
        l1.removeFirst();
        l1.removeFirst();
        boolean actualStatus=l1.isEmpty();
        org.junit.Assert.assertEquals(true,actualStatus);
    }
    @org.junit.Test
    public void testRemoveLast(){
        LinkedListDeque<Integer> l1=new LinkedListDeque<>(5);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(8);
        l1.addLast(9);
        l1.printDeque();
        int actual=l1.removeLast();
        //int actual=l1.get(0);
        org.junit.Assert.assertEquals(9,actual);
        l1.removeLast();
        l1.removeLast();
        l1.removeLast();
        l1.removeLast();
        l1.removeLast();
        l1.isEmpty();
    }
    @org.junit.Test
    public void testIsEmpty(){
        LinkedListDeque<String> l2=new LinkedListDeque<>();
        boolean actual= l2.isEmpty();
        org.junit.Assert.assertEquals(true,actual);
    }
    @org.junit.Test
    public void testGet(){
        LinkedListDeque<Integer> l1=new LinkedListDeque<>(5);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(8);
        l1.addLast(9);
        for(int i=0;i<l1.size();i++){
            System.out.println(l1.get(i));
        }

    }
    @org.junit.Test
    public void testGetRecursive(){
        LinkedListDeque<Integer> l1=new LinkedListDeque<>(5);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(8);
        l1.addLast(9);
        int actual=l1.getRecursive(3);
        org.junit.Assert.assertEquals(8,actual);
    }
}
