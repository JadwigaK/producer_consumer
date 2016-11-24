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

    private ConsumerSemaphore consumerSem = new ConsumerSemaphore(size);
    private ProducerSemaphore producerSem = new ProducerSemaphore(size);

    public Buffer(int size) {
        this.size=size;
    }

    public synchronized void  putElement(Integer element) throws InterruptedException {
        while (bufferList.size() >= size){
            wait();
        }

           // producerSem.acquire();
            bufferList.add(element);
            System.out.println("Producer gave number.");
            System.out.println("Now buffer contains:" + bufferList);
           // consumerSem.release();
            notifyAll();

    }

    public synchronized Integer getFirstElement() throws InterruptedException {
        while(bufferList.isEmpty()){
            wait();
        }

       // consumerSem.acquire();
        Integer firstElement = bufferList.get(0);
        bufferList.remove(firstElement);
        System.out.println("Consumer took number.");
        System.out.println("Now buffer contains:"+bufferList);
       // producerSem.release();
        notifyAll();
        return firstElement;

    }

}
