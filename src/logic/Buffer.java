package logic;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class Buffer {

    private int size;
    private List<Integer> bufferList = new LinkedList<Integer>();

    private ConsumerSemaphore consumerSem;
    private ProducerSemaphore producerSem;
    private Semaphore mutex = new Semaphore(1);

    public Buffer(int size) {
        this.size=size;
        consumerSem = new ConsumerSemaphore(size);
        producerSem = new ProducerSemaphore(size);
    }

    public void  putElement(Integer element) throws InterruptedException {

            producerSem.acquire(); //gdzie ja mam ten semafor zajmowac tu??
            mutex.acquire();
            bufferList.add(element);
            System.out.println("Producer gave number.");
            System.out.println("Now buffer contains:" + bufferList);
            mutex.release();
            consumerSem.release(); //a gdzie zwalniac????
    }

    public Integer getFirstElement() throws InterruptedException {

        consumerSem.acquire();
        mutex.acquire();
        Integer firstElement = bufferList.get(0);
        bufferList.remove(firstElement);
        //Thread.sleep(400);
        System.out.println("Consumer took number.");
        System.out.println("Now buffer contains:"+bufferList);
        mutex.release();
        producerSem.release();
        return firstElement;

    }

}
