package br.com.mapfood.ApiMatrixGoogleMaps;

public class MotoboyRotas {

	private Long id;
	private double distancia;
	
	
	public MotoboyRotas() {}
	
	public MotoboyRotas(Long id, double distancia) {
		super();
		this.id = id;
		this.distancia = distancia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	
}
