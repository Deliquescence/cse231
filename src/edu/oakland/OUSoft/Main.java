package edu.oakland.OUSoft;

import edu.oakland.OUSoft.database.OUPeople;

public class Main {
	
	public static void main(String[] args) {
		OUPeople people = new OUPeople();
		
		TextInterface ti = new TextInterface(people);
		
		ti.startup();
		
	}
}
