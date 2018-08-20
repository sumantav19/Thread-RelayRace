package org.executor.relay;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;

public class RaceFutureExecutor {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(3);
		CountDownLatch latch = new CountDownLatch(3);
		List<Future> futureList = new LinkedList<Future>(); 
		for(int i = 0; i < 3 ; i++  ){
			futureList.add(executor.submit( new CountryRacer(Country.values()[i].name(),latch)));
		}
		
		executor.shutdown();
//		try {
//			executor.awaitTermination(1,TimeUnit.DAYS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Iterator it = futureList.iterator();
		while(it.hasNext()){
			try {
				System.out.println(((Future)it.next()).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//catches exception caught in callable
				e.printStackTrace();
			}
		}
		
		
		System.out.println("End of race");
	}

}
