package co.edu.icesi.demo.mapeo;

public class MapeoMedicionLAB {

	private String nombreDato;
	private String valorMedido;
	private String metodologiaAplicada;

	public MapeoMedicionLAB(String nombreDato,
			String valorMedido, String metodologiaAplicada) {
		
		this.nombreDato = nombreDato;
		this.valorMedido = valorMedido;
		this.metodologiaAplicada = metodologiaAplicada;
		
	}

	public String getNombreDato() {
		return nombreDato;
	}

	public void setNombreDato(String nombreDato) {
		this.nombreDato = nombreDato;
	}

	public String getValorMedido() {
		return valorMedido;
	}

	public void setValorMedido(String valorMedido) {
		this.valorMedido = valorMedido;
	}

	public String getMetodologiaAplicada() {
		return metodologiaAplicada;
	}

	public void setMetodologiaAplicada(String metodologiaAplicada) {
		this.metodologiaAplicada = metodologiaAplicada;
	}

}
