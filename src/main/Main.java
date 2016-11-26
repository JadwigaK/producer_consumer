package main;

import logic.Buffer;
import logic.ConsumerThread;
import logic.ProducerThread;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class Main {
    public static void main(String[] arg){
        long startTime = System.nanoTime();

        Buffer buffer = new Buffer(20);
        Thread producer1 = new Thread(new ProducerThread(buffer, 1));
        Thread producer2 = new Thread(new ProducerThread(buffer, 2));
        Thread producer3 = new Thread(new ProducerThread(buffer, 3));
//        Thread producer4 = new Thread(new ProducerThread(buffer, 4));
//        Thread producer5 = new Thread(new ProducerThread(buffer, 5));

        Thread consumer1 = new Thread(new ConsumerThread(buffer, 1));
        Thread consumer2 = new Thread(new ConsumerThread(buffer, 2));
        Thread consumer3 = new Thread(new ConsumerThread(buffer, 3));
//        Thread consumer4 = new Thread(new ConsumerThread(buffer, 4));
//        Thread consumer5 = new Thread(new ConsumerThread(buffer, 5));
//        Thread consumer6 = new Thread(new ConsumerThread(buffer, 6));
//        Thread consumer7 = new Thread(new ConsumerThread(buffer, 7));
//        Thread consumer8 = new Thread(new ConsumerThread(buffer, 8));
//        Thread consumer9 = new Thread(new ConsumerThread(buffer, 9));
//        Thread consumer10 = new Thread(new ConsumerThread(buffer, 10));
        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
//        consumer4.start();
//        consumer5.start();
//        consumer6.start();
//        consumer7.start();
//        consumer8.start();
//        consumer9.start();
//        consumer10.start();

//        producer4.start();
//        producer5.start();

        try {
            consumer1.join();
            consumer2.join();
            consumer3.join();
//            consumer4.join();
//            consumer5.join();
//            consumer6.join();
//            consumer7.join();
//            consumer8.join();
//            consumer9.join();
//            consumer10.join();
            producer1.join();
            producer2.join();
            producer3.join();
//            producer4.join();
//            producer5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("duration "+duration);

    }
}
