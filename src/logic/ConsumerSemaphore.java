package logic;

/**
 * Created by Jadwiga on 2016-11-24.
 */
public class ConsumerSemaphore {

    private int size;
    private int counter; // ile zajetego miejsca w buforze

    public ConsumerSemaphore(int size) {
        this.counter=0;
        this.size = size;
    }

    public synchronized void release(){
        counter++;
        notifyAll();
    }

    public synchronized void acquire() throws InterruptedException {
        while (counter == 0){
            wait();
        }

        //tu jest cos zle bo to acquire tworzy tak jakby jedena tylko cyfre, tylko gdzie ja to własciwie tworze te cyfry,
        //bo nie w semforze, Kuba ratuj, mózg paruje;p
        if (counter >0) {
            counter--;
        }
    }

}
