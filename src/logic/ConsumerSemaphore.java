package logic;

/**
 * Created by Jadwiga on 2016-11-24.
 */
public class ConsumerSemaphore {

    private int size;
    private int counter = 0; // ile zajetego miejsca w buforze

    public ConsumerSemaphore(int size) {
        this.size = size;
    }

    public void release(){
        if (counter == 0) {
            counter++;
        }
    }

    public void acquire() throws InterruptedException {
        if (counter >0) {
            counter--;
        }
    }

}
