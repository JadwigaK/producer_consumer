package main;

import logic.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jadwiga on 2016-11-19.
 */
public class Main {
    public static void main(String[] arg) {

        int bufferSize = 20;
        int numberOfConsumers = 3;
        int numberOfProducers = 3;

        Buffer buffer1 = new SynchronizedBuffer(bufferSize);
        List<Thread> producers = simpleProducers(numberOfProducers, buffer1);
        List<Thread> consumers = simpleConsumers(numberOfConsumers, buffer1);
        performExperiment(producers, consumers);

        Buffer simpleBuffer = new SynchronizedBuffer(bufferSize);
        ProducerSemaphore producerSemaphore = new ProducerSemaphore(bufferSize);
        ConsumerSemaphore consumerSemaphore = new ConsumerSemaphore(0);
        List<Thread> producersWithSempahores = producersWithSemaphores(numberOfProducers, simpleBuffer, producerSemaphore, consumerSemaphore);
        List<Thread> consumentsWithSemaphores = consumentsWithSemaphores(numberOfConsumers, simpleBuffer, producerSemaphore, consumerSemaphore);
        performExperiment(producersWithSempahores, consumentsWithSemaphores);
    }

    private static List<Thread> simpleProducers(int numberOfProducers, Buffer simpleBuffer) {
        List<Thread> producers = new ArrayList<>(numberOfProducers);
        for (int i = 0; i < numberOfProducers; i++) {
            producers.add(new Thread(new Producer(simpleBuffer, i)));
        }
        return producers;
    }

    private static List<Thread> simpleConsumers(int numberOfConsumers, Buffer simpleBuffer) {
        List<Thread> consumers = new ArrayList<>(numberOfConsumers);
        for (int i = 0; i < numberOfConsumers; i++) {
            consumers.add(new Thread(new Consumer(simpleBuffer, i)));
        }
        return consumers;
    }

    private static List<Thread> producersWithSemaphores(int numberOfProducers, Buffer simpleBuffer,
                                                        ProducerSemaphore producerSemaphore, ConsumerSemaphore consumerSemaphore) {
        List<Thread> producers = new ArrayList<>(numberOfProducers);
        for (int i = 0; i < numberOfProducers; i++) {
            producers.add(new Thread(new ProducerWithSemaphore(simpleBuffer, i, consumerSemaphore, producerSemaphore)));
        }
        return producers;

    }

    private static List<Thread> consumentsWithSemaphores(int numberOfConsumers, Buffer simpleBuffer,
                                                         ProducerSemaphore producerSemaphore, ConsumerSemaphore consumerSemaphore) {
        List<Thread> consumers = new ArrayList<>(numberOfConsumers);
        for (int i = 0; i < numberOfConsumers; i++) {
            consumers.add(new Thread(new ConsumerWithSemaphore(simpleBuffer, i, consumerSemaphore, producerSemaphore)));
        }
        return consumers;

    }

    private static void performExperiment(List<Thread> producers, List<Thread> consumers) {
        long startTime = System.currentTimeMillis();
        for (Thread producer : producers) {
            producer.start();
        }
        for (Thread consumer : consumers) {
            consumer.start();
        }
        try {
            for (Thread producer : producers) {
                producer.join();
            }
            for (Thread consumer : consumers) {
                consumer.join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Result for " + consumers.size() + " consumers and " + producers.size() + " producers with final duration: " + duration);
    }
}
