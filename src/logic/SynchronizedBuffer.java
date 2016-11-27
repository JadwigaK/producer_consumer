
package logic;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SynchronizedBuffer implements Buffer {

    private int size;
    private List<Integer> bufferList = new LinkedList<Integer>();


    public SynchronizedBuffer(int size) {
        this.size = size;
    }

    public synchronized void putElement(Integer element) throws InterruptedException {
        bufferList.add(element);
    }

    public synchronized Integer getFirstElement() throws InterruptedException {
        Integer firstElement = bufferList.get(0);
        bufferList.remove(firstElement);
        return firstElement;

    }

    public synchronized boolean isFull(){
        return bufferList.size() >= size;
    }

    public synchronized boolean isEmpty(){
        return bufferList.isEmpty();
    }

}