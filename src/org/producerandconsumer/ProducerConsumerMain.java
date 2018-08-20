package org.producerandconsumer;

import java.util.LinkedList;

public class ProducerConsumerMain {

	LinkedList<Integer> list = new LinkedList<Integer>();
	int limit = 10;
	Object lock = new Object();

	public void produce() {
		int value = 0;
		while (true) {
			synchronized (lock) {
				while (list.size() == limit) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				list.add(value++);
				lock.notify();
			}
		}
	}

	public void consume() {
		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.print("List size is: " + list.size());
				System.out.println("Removing " + list.removeFirst());
				lock.notify();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		ProducerConsumerMain producerConsumer = new ProducerConsumerMain();

		Thread producerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				producerConsumer.produce();
			}
		});

		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				producerConsumer.consume();
			}
		});
		producerThread.start();
		consumerThread.start();

	}
}
