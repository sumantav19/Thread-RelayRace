package org.executor.relay;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountryRacer  implements Callable<Long> {
	private String countryName;
	private long countryTime;
	private CountDownLatch countryLatch; // not required temporary till refactor
	private CountDownLatch runnerLatch; // not required temporary till refactor
	RaceRunner raceRunner;
//	private Thread runner1;
//	private Thread runner2;
	ExecutorService executor;
	public String getCountryName() {
		return countryName;
	}

	public long getCountryTime() {
		return countryTime;
	}
	
	public CountryRacer(String countryName,CountDownLatch latch) {
		// TODO Auto-generated constructor stub
		this.countryName = countryName;
		// TODO Auto-generated method stub
		runnerLatch = new CountDownLatch(2); // not required temporary till refactor
		raceRunner = new RaceRunner(countryName+" Runner",runnerLatch);	
		executor  = Executors.newFixedThreadPool(2);
		countryLatch =  latch;
	}
	
	@Override
	public Long call() {
		long startTime = System.currentTimeMillis();
		List<Future<Long>> runnerFutureList =  new LinkedList<Future<Long>>();
		// same instance of race runner is shared in both the thread. Two threads cannot access race runner as it is synchronized 
		for(int i = 0 ; i< 2 ; i++){
			runnerFutureList.add(executor.submit(raceRunner));
		}
		executor.shutdown();
		
//		try {
////			executor.awaitTermination(1, TimeUnit.DAYS);
//			runnerLatch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Iterator it = runnerFutureList.iterator();
		while(it.hasNext()){
			try {
				System.out.println(getCountryName()+" runner finished in "+((Future)it.next()).get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getCountryName());
		countryLatch.countDown(); // not required temporary till refactor
		return System.currentTimeMillis() - startTime;
	}


}
