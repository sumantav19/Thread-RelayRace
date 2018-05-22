package org.learningthread;

public class Race {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountryRacer kenyaRacers = new CountryRacer("Kenya");		
		CountryRacer zambiaRacers = new CountryRacer("Zambia");		
		CountryRacer moroccoRacers = new CountryRacer("Morocco");
		kenyaRacers.runRace();
		zambiaRacers.runRace();
		moroccoRacers.runRace();
	}

}
