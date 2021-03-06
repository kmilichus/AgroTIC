package co.edu.icesi.demo.modelo;
// Generated 18/05/2017 01:24:35 PM by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Terreno generated by hbm2java
 */
public class Terreno implements java.io.Serializable {

	private BigDecimal terrid;
	private String nombreterr;
	private Set<Estrategiamuestreo> estrategiamuestreos = new HashSet<Estrategiamuestreo>(0);
	private Set<Lote> lotes = new HashSet<Lote>(0);

	public Terreno() {
	}

	public Terreno(BigDecimal terrid, String nombreterr) {
		this.terrid = terrid;
		this.nombreterr = nombreterr;
	}

	public Terreno(BigDecimal terrid, String nombreterr, Set<Estrategiamuestreo> estrategiamuestreos, Set<Lote> lotes) {
		this.terrid = terrid;
		this.nombreterr = nombreterr;
		this.estrategiamuestreos = estrategiamuestreos;
		this.lotes = lotes;
	}

	public BigDecimal getTerrid() {
		return this.terrid;
	}

	public void setTerrid(BigDecimal terrid) {
		this.terrid = terrid;
	}

	public String getNombreterr() {
		return this.nombreterr;
	}

	public void setNombreterr(String nombreterr) {
		this.nombreterr = nombreterr;
	}

	public Set<Estrategiamuestreo> getEstrategiamuestreos() {
		return this.estrategiamuestreos;
	}

	public void setEstrategiamuestreos(Set<Estrategiamuestreo> estrategiamuestreos) {
		this.estrategiamuestreos = estrategiamuestreos;
	}

	public Set<Lote> getLotes() {
		return this.lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}

}
