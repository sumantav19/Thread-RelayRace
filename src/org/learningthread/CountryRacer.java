package org.learningthread;

public class CountryRacer  {
	public CountryRacer(String countryName) {
		// TODO Auto-generated constructor stub
		this.countryName = countryName;
	}
	private String countryName;
	private long countryTime;
	
	public void runRace() {
		// TODO Auto-generated method stub
		RaceRunner raceRunner = new RaceRunner();		
		ThreadGroup countryNameGroup = new ThreadGroup(this.countryName);
		
		// same instance of race runner is shared in both the thread. Two threads cannot access race runner as it is synchronized 
		Thread runner1 = new Thread(countryNameGroup, raceRunner,this.countryName+" Runner 1");
		Thread runner2 = new Thread(countryNameGroup, raceRunner,this.countryName+" Runner 2");
		runner1.start();
		runner2.start();		
	}

}
