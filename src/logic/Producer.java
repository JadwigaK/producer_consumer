package logic;

import static java.lang.Thread.sleep;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class Producer implements Runnable {

    private final Buffer buffer;
    private int numberOfThread;

    public Producer(Buffer buffer, int numberOfThread) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;

    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while(iterator > 0){
            try {
                synchronized(buffer) {
                    while (buffer.isFull()) {
                        buffer.wait();
                    }
                    produce();
                    buffer.putElement(iterator);
                    buffer.notifyAll();
                    //     System.out.println("It was Producer Thread "+numberOfThread+ "\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iterator --;
        }
    }

    public void produce() throws InterruptedException {
        sleep(4);
    }
}
