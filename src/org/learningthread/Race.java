package org.learningthread;

public class Race {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountryRacer kenya =  new CountryRacer("Kenya");
		CountryRacer zambia = new CountryRacer("Zambia");
		CountryRacer morocco = new CountryRacer("Morocco");
		Thread kenyaRacers = new Thread( kenya );		
		Thread zambiaRacers = new Thread( zambia );		
		Thread moroccoRacers = new Thread( morocco );
		kenyaRacers.start();
		zambiaRacers.start();
		moroccoRacers.start();
		
		try {
			kenyaRacers.join();
			zambiaRacers.join();
			moroccoRacers.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(kenya.getCountryName()+" ====== " + kenya.getCountryTime() );
		System.out.println(zambia.getCountryName()+" ====== " + zambia.getCountryTime() );
		System.out.println(morocco.getCountryName()+" ====== " + morocco.getCountryTime() );
		
		System.out.println("End of race");
	}

}
