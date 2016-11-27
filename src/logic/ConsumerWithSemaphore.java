package logic;

import static java.lang.Thread.sleep;

public class ConsumerWithSemaphore implements Runnable {

    private final Buffer buffer;
    private int numberOfThread;
    private ConsumerSemaphore consumerSem;
    private ProducerSemaphore producerSem;

    public ConsumerWithSemaphore(Buffer buffer, int numberOfThread, ConsumerSemaphore consumerSem,
                                 ProducerSemaphore producerSem) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;
        this.consumerSem = consumerSem;
        this.producerSem = producerSem;
    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while (iterator > 0) {
            try {
                consumerSem.acquire();
                buffer.getFirstElement();
                producerSem.release();
                consume();
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
