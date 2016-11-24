package logic;

/**
 * Created by Jadwiga on 2016-11-24.
 */
public class ProducerSemaphore {

    private int size ;
    private int counter = size; //ile jeszcze wolnego miejsca

    public ProducerSemaphore(int size) {
        this.size = size;
    }

    public void release(){
        if (counter ==0) {
            counter++;
        }

    }

    public void acquire() throws InterruptedException {
        if (counter > 0) {
            counter--;
        }
    }
}
