package java8.thread.examples;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

  public static void main(String[] args) throws Exception {

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);

    new Thread(producer).start();
    new Thread(consumer).start();

    Thread.sleep(4000);
  }
}
