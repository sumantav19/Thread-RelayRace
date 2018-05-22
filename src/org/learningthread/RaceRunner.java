package org.learningthread;

public class RaceRunner implements Runnable {
	private long finishTime;	
//	public RaceRunner(){
//		
//	}
	public long getFinishTime() {
		return finishTime;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.finishTime = this.runRace();
	}
	private long runRace(){		
		runFirst200();
		runNext200();
		System.out.println(Thread.currentThread().getName()+" finished Race");
		return System.currentTimeMillis();		
	}
	public synchronized void runFirst200(){
		
		for(int i = 1 ; i <= 200; i++){
			
		}
	}
	public void runNext200(){
		for(int i = 1 ; i <= 200; i++){
			
		}
	}
	
}
