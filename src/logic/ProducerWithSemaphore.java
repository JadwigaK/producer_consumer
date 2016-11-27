package logic;

import static java.lang.Thread.sleep;

public class ProducerWithSemaphore implements Runnable {

    private final Buffer buffer;
    private int numberOfThread;
    private ConsumerSemaphore consumerSem;
    private ProducerSemaphore producerSem;

    public ProducerWithSemaphore(Buffer buffer, int numberOfThread, ConsumerSemaphore consumerSem,
                                 ProducerSemaphore producerSem) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;
        this.consumerSem = consumerSem;

        this.producerSem = producerSem;
    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while(iterator > 0){
            try {
                producerSem.acquire();
                produce();
                buffer.putElement(iterator);
                consumerSem.release();
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
