import java.io.ObjectStreamException;

public class ArrayDeque<YourType> {
    private final int capacity=8;
    YourType[] items;
    int size;
    int first;
    int last;

    /**Make it easier to find "before"*/
    public int minusOne(int index){
        if(index>0){
            return index-1;
        }
        return items.length-1;
    }
    /**Make it easier to move "pointer"*/
    public int plusOne(int index){
        return (index+1)%items.length;
    }
    /**Resize function*/
    public void resize(){
        if(size==items.length) expand();
        float usage=(float) size/ (float) items.length;
        if(usage < 0.25 && items.length>capacity) shrink();
    }
    /**shrink current storage array*/
    public void shrink(){//shrink by half
        int nextCapacity=items.length/2;
        YourType[] tmp=(YourType[]) new Object[nextCapacity];
        int start=(nextCapacity-size)/2;
        for(int i=start;i<(start+size);i++){
            tmp[i]=this.get(i%start);
        }
        items=tmp;
        last=plusOne(start+size-1);
        first=minusOne(start);
    }
    /**Expand current storage array*/
    public void expand(){
        int nextCapacity=items.length*2;
        YourType[] tmp=(YourType[]) new Object[nextCapacity];
        int start=(nextCapacity-size)/2;
        for(int i=start;i<(start+size);i++){
            tmp[i]=this.get(i%start);
        }
        items=tmp;
        last=plusOne(start+size-1);
        first=minusOne(start);
    }

    /**Add a sentinel node*/
    public ArrayDeque(){
        size=0;
        items=(YourType[]) new Object[capacity];
        first=3;
        last=3;
    }
    public ArrayDeque(YourType x){
        first=3;
        last=3;
        items=(YourType[]) new Object[capacity];
        items[first]=x;
        first=minusOne(first);
        last=plusOne(last);
        size=1;
    }
    /**Add an element to the front.*/
    public void addFirst(YourType input){
        resize();
        items[first]=input;
        first=minusOne(first);
        if(size==0) last=plusOne(last);
        size++;
    }
    /**Add an element to the end*/
    public void addLast(YourType input){
        resize();
        items[last]=input;
        last=plusOne(last);
        if(size==0) first=minusOne(first);
        size++;
    }
    /**Check if it's empty*/
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    /**Return the size of our array*/
    public int size(){
        System.out.println(items.length);
        return size;
    }
    /**Print the ArrayDequeList*/
    public void printDeque(){
        System.out.println("======Printing process Start======");
        int counter=plusOne(first);
        while(counter!=minusOne(last)){
            System.out.print(items[counter]);
            System.out.print(" ");
            counter=plusOne(counter);
        }
        System.out.print(items[counter]);
        System.out.println("\n======Printing process End======");
    }
    /**Get the index-th element*/
    public YourType get(int index){
        if(index >=size||index<0){
            System.out.println("Error: The given index is out of bound");
            return (YourType) null;
        }
        int outIndex=(index+plusOne(first))% items.length;
        return items[outIndex];
    }
    /**Remove the first element*/
    public YourType removeFirst(){
        if(size==0) return (YourType) null;
        YourType outPut;
        first=plusOne(first);
        outPut=items[first];
        size--;
        if(size==0) last=minusOne(last);
        resize();
        return outPut;
    }
    /**Remove the last element*/
    public YourType removeLast(){
        if(size==0) return (YourType) null;
        YourType outPut;
        last=minusOne(last);
        outPut=items[last];
        size--;
        if(size==0) first=plusOne(first);
        resize();
        return outPut;
    }



}
