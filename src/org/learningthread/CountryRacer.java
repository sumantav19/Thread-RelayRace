package org.learningthread;

public class CountryRacer  implements Runnable {
	private String countryName;
	private long countryTime;
	private Thread runner1;
	private Thread runner2;
	
	public String getCountryName() {
		return countryName;
	}

	public long getCountryTime() {
		return countryTime;
	}
	
	public CountryRacer(String countryName) {
		// TODO Auto-generated constructor stub
		this.countryName = countryName;
		// TODO Auto-generated method stub
		RaceRunner raceRunner = new RaceRunner();	
		
		
		// same instance of race runner is shared in both the thread. Two threads cannot access race runner as it is synchronized 
		runner1 = new Thread(raceRunner,this.countryName+" Runner 1");
		runner2 = new Thread(raceRunner,this.countryName+" Runner 2");
	}

	public void run() {
//		long startTime = System.currentTimeMillis();
		runner1.start();
		runner2.start();
		try {
			runner1.join();
			runner2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countryTime = System.currentTimeMillis();
	}


}
