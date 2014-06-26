package ar.com.historiasocial.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class DistanceToCoordMap {
	
	/**
	 * Constructor por defecto privado 
	 */
	public DistanceToCoordMap() {
	}
	
	public static Map<Double, String> getMap(){
		LinkedHashMap<Double, String> map = new LinkedHashMap<Double, String>();
		map.put(0.01d, "Hasta 1 kilometro aprox.");
		map.put(0.05d, "Hasta 5 Kilometros aprox.");
		map.put(0.09d, "Hasta 10 Kilometros aprox.");
		map.put(0.894d, "Hasta 100 Kilometros aprox.");
		map.put(1.788d, "Hasta 200 Kilometros aprox.");
		map.put(8.941d, "Hasta 1000 Kilometros aprox.");
		
		return map;
	}
}
