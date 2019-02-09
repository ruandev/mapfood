package br.com.mapfood.util;


public class DistanciaEmKm {

	
	public static int EARTH_RADIUS_KM = 6371;
	
	public static double distanciaEmKM(
				  double firstLatitude,
			  	  double firstLongitude, 			
				  double secondLatitude, 
				  double secondLongitude) {

		// Conversao de graus pra radianos das latitudes
		double firstLatToRad = Math.toRadians(firstLatitude);
		double secondLatToRad = Math.toRadians(secondLatitude);

		// Diferensa das longitudes
		double deltaLongitudeInRad = Math.toRadians(secondLongitude
				- firstLongitude);

		// Calcula a distancia entre dois pontos
		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
				* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
				* Math.sin(secondLatToRad))
				* EARTH_RADIUS_KM;
	}

	
}
