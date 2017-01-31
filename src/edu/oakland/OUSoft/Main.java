package edu.oakland.OUSoft;

import edu.oakland.OUSoft.database.OUSoft;

public class Main {
	
	public static void main(String[] args) {
		OUSoft people = new OUSoft();
		
		TextInterface ti = new TextInterface(people);
		
		ti.startup();
		
	}
}
