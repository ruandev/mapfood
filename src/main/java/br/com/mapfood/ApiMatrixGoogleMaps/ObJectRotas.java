package br.com.mapfood.ApiMatrixGoogleMaps;

public class ObJectRotas {

	public  Long id;
    public  String distancia ;
    public  Integer distanciaMetros ;
    public  String tempoHorasMinutos;
    public  Integer tempoSegundos;


    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Integer getDistanciaMetros() {
        return distanciaMetros;
    }

    public void setDistanciaMetros(Integer distanciaMetros) {
        this.distanciaMetros = distanciaMetros;
    }

    public String getTempoHorasMinutos() {
        return tempoHorasMinutos;
    }

    public void setTempoHorasMinutos(String tempoHorasMinutos) {
        this.tempoHorasMinutos = tempoHorasMinutos;
    }

    public Integer getTempoSegundos() {
        return tempoSegundos;
    }

    public void setTempoSegundos(Integer tempoSegundos) {
        this.tempoSegundos = tempoSegundos;
    }

    @Override
    public String toString() {
        return "ObJectRotas { "
                + " distancia: '" + distancia + '\''
                + ", distanciaMetros: " + distanciaMetros
                + ", tempoHorasMinutos: '" + tempoHorasMinutos + '\''
                + ", tempoSegundos: " + tempoSegundos
                + " }";
    }
}
