package logic;

/**
 * Created by Jadwiga on 2016-11-24.
 */
public class ProducerSemaphore {

    private int size;
    private int counter; //ile jeszcze wolnego miejsca

    public ProducerSemaphore(int size) {
        this.size = size;
        this.counter=size;
    }

    public synchronized void release(){
            counter++;
            notifyAll();
    }

    public synchronized void acquire() throws InterruptedException {
        while (counter == 0){
            wait();
        }
        if (counter > 0) {
            counter--;
        }
    }
}
