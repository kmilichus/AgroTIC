package co.edu.icesi.demo.modelo;
// Generated 18/05/2017 01:24:35 PM by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;

/**
 * Fotografia generated by hbm2java
 */
public class Fotografia implements java.io.Serializable {

	private BigDecimal fotoid;
	private Puntorecoleccion puntorecoleccion;
	private byte[] fotografia;

	public Fotografia() {
	}

	public Fotografia(BigDecimal fotoid, Puntorecoleccion puntorecoleccion, byte[] fotografia) {
		this.fotoid = fotoid;
		this.puntorecoleccion = puntorecoleccion;
		this.fotografia = fotografia;
	}

	public BigDecimal getFotoid() {
		return this.fotoid;
	}

	public void setFotoid(BigDecimal fotoid) {
		this.fotoid = fotoid;
	}

	public Puntorecoleccion getPuntorecoleccion() {
		return this.puntorecoleccion;
	}

	public void setPuntorecoleccion(Puntorecoleccion puntorecoleccion) {
		this.puntorecoleccion = puntorecoleccion;
	}

	public byte[] getFotografia() {
		return this.fotografia;
	}

	public void setFotografia(byte[] fotografia) {
		this.fotografia = fotografia;
	}

}