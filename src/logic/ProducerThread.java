package logic;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class ProducerThread implements Runnable {

    private Buffer buffer;
    private int numberOfThread;

    public ProducerThread(Buffer buffer, int numberOfThread) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;

    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while(iterator > 0){
            try {
   //             System.out.println("Producer Thread "+numberOfThread + " is running.");
                buffer.putElement(iterator);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iterator --;
        }


    }
}
