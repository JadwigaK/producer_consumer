package logic;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class ConsumerThread implements Runnable {

    private Buffer buffer;
    private int numberOfThread;

    public ConsumerThread(Buffer buffer, int numberOfThread) {
        this.buffer = buffer;
        this.numberOfThread=numberOfThread;
    }

    @Override
    public void run() {
        Integer iterator = 1000;
        while (iterator > 0) {
            try {
                buffer.getFirstElement();
                System.out.println("It was Consumer Thread "+numberOfThread + "\n" );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iterator --;
        }
    }
}
