package co.edu.icesi.demo.modelo;
// Generated 18/05/2017 01:24:35 PM by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;

/**
 * Medicionmetricas generated by hbm2java
 */
public class Medicionmetricas implements java.io.Serializable {

	private BigDecimal mmetid;
	private Caracterizacionlote caracterizacionlote;
	private Metodologia metodologia;
	private Rangometricas rangometricas;
	private double magnitud;

	public Medicionmetricas() {
	}

	public Medicionmetricas(BigDecimal mmetid, Rangometricas rangometricas, double magnitud) {
		this.mmetid = mmetid;
		this.rangometricas = rangometricas;
		this.magnitud = magnitud;
	}

	public Medicionmetricas(BigDecimal mmetid, Caracterizacionlote caracterizacionlote, Metodologia metodologia,
			Rangometricas rangometricas, double magnitud) {
		this.mmetid = mmetid;
		this.caracterizacionlote = caracterizacionlote;
		this.metodologia = metodologia;
		this.rangometricas = rangometricas;
		this.magnitud = magnitud;
	}

	public BigDecimal getMmetid() {
		return this.mmetid;
	}

	public void setMmetid(BigDecimal mmetid) {
		this.mmetid = mmetid;
	}

	public Caracterizacionlote getCaracterizacionlote() {
		return this.caracterizacionlote;
	}

	public void setCaracterizacionlote(Caracterizacionlote caracterizacionlote) {
		this.caracterizacionlote = caracterizacionlote;
	}

	public Metodologia getMetodologia() {
		return this.metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public Rangometricas getRangometricas() {
		return this.rangometricas;
	}

	public void setRangometricas(Rangometricas rangometricas) {
		this.rangometricas = rangometricas;
	}

	public double getMagnitud() {
		return this.magnitud;
	}

	public void setMagnitud(double magnitud) {
		this.magnitud = magnitud;
	}

}
