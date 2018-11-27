package co.edu.icesi.demo.mapeo;

public class MapeoCaracteristica {

	private String nombreCaract;
	private String descripcionCaract;

	public MapeoCaracteristica(String nombreCaract, String descripcionCaract) {

		this.nombreCaract = nombreCaract;
		this.descripcionCaract = descripcionCaract;

	}

	public MapeoCaracteristica() {

	}

	public String getNombreCaract() {
		return nombreCaract;
	}

	public void setNombreCaract(String nombreCaract) {
		this.nombreCaract = nombreCaract;
	}

	public String getDescripcionCaract() {
		return descripcionCaract;
	}

	public void setDescripcionCaract(String descripcionCaract) {
		this.descripcionCaract = descripcionCaract;
	}

}
