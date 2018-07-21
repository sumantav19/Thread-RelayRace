package org.executor.relay;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountryRacer  implements Runnable {
	private String countryName;
	private long countryTime;
	private CountDownLatch countryLatch;
	private CountDownLatch runnerLatch;
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
		runnerLatch = new CountDownLatch(2);
		raceRunner = new RaceRunner(countryName+" Runner",runnerLatch);	
		executor  = Executors.newFixedThreadPool(2);
		countryLatch =  latch;
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		// same instance of race runner is shared in both the thread. Two threads cannot access race runner as it is synchronized 
		for(int i = 0 ; i< 2 ; i++){
			executor.submit(raceRunner);
		}
		executor.shutdown();
		
		try {
//			executor.awaitTermination(1, TimeUnit.DAYS);
			runnerLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getCountryName());
		countryLatch.countDown();
	}


}
