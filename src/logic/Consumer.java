package logic;

import static java.lang.Thread.sleep;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class Consumer implements Runnable {

    private final Buffer buffer;
    private int numberOfThread;

    public Consumer(Buffer buffer, int numberOfThread) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;
    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while (iterator > 0) {
            try {
                synchronized(buffer) {
                    while (buffer.isEmpty()) {
                        buffer.wait();
                    }
                    buffer.getFirstElement();
                    buffer.notifyAll();
                    consume();
                    //    System.out.println("It was Consumer Thread "+numberOfThread + "\n" );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iterator --;
        }
    }

    public void consume() throws InterruptedException {
        sleep(3);
    }
}
