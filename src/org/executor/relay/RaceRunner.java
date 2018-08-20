package org.executor.relay;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch; // not required temporary till refactor

public class RaceRunner implements Callable<Long> {
	private long totalTime;	
	private String runner;
	Object lock = new Object();
	private CountDownLatch runnerLatch; // not required temporary till refactor
	
	RaceRunner(String runner, CountDownLatch latch){
		this.runner = runner;
		runnerLatch = latch;
	}
	@Override
	public Long call() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.runRace();
	}
	private Long runRace(){
		long startTime = System.currentTimeMillis();
		runFirst200();
		runNext200();
//		System.out.println(runner+" finished Race");
		runnerLatch.countDown(); // not required temporary till refactor
		return System.currentTimeMillis() -  startTime;
	}
	
	// wait for the first racer to complete the first 200
	public void runFirst200(){
		synchronized (lock) {
			for(int i = 1 ; i <= 200; i++){
				
			}
		}
		
	}
	public void runNext200(){
		for(int i = 1 ; i <= 200; i++){
			
		}
	}
	
}
