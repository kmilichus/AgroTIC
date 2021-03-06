package co.edu.icesi.demo.modelo;
// Generated 18/05/2017 01:24:35 PM by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Subordensuelo generated by hbm2java
 */
public class Subordensuelo implements java.io.Serializable {

	private BigDecimal subordenid;
	private Ordensuelo ordensuelo;
	private Regimenhumedad regimenhumedad;
	private String nombresuborden;
	private String descripcionsubord;
	private Set<Subgruposuelo> subgruposuelos = new HashSet<Subgruposuelo>(0);

	public Subordensuelo() {
	}

	public Subordensuelo(BigDecimal subordenid, String nombresuborden, String descripcionsubord) {
		this.subordenid = subordenid;
		this.nombresuborden = nombresuborden;
		this.descripcionsubord = descripcionsubord;
	}

	public Subordensuelo(BigDecimal subordenid, Ordensuelo ordensuelo, Regimenhumedad regimenhumedad,
			String nombresuborden, String descripcionsubord, Set<Subgruposuelo> subgruposuelos) {
		this.subordenid = subordenid;
		this.ordensuelo = ordensuelo;
		this.regimenhumedad = regimenhumedad;
		this.nombresuborden = nombresuborden;
		this.descripcionsubord = descripcionsubord;
		this.subgruposuelos = subgruposuelos;
	}

	public BigDecimal getSubordenid() {
		return this.subordenid;
	}

	public void setSubordenid(BigDecimal subordenid) {
		this.subordenid = subordenid;
	}

	public Ordensuelo getOrdensuelo() {
		return this.ordensuelo;
	}

	public void setOrdensuelo(Ordensuelo ordensuelo) {
		this.ordensuelo = ordensuelo;
	}

	public Regimenhumedad getRegimenhumedad() {
		return this.regimenhumedad;
	}

	public void setRegimenhumedad(Regimenhumedad regimenhumedad) {
		this.regimenhumedad = regimenhumedad;
	}

	public String getNombresuborden() {
		return this.nombresuborden;
	}

	public void setNombresuborden(String nombresuborden) {
		this.nombresuborden = nombresuborden;
	}

	public String getDescripcionsubord() {
		return this.descripcionsubord;
	}

	public void setDescripcionsubord(String descripcionsubord) {
		this.descripcionsubord = descripcionsubord;
	}

	public Set<Subgruposuelo> getSubgruposuelos() {
		return this.subgruposuelos;
	}

	public void setSubgruposuelos(Set<Subgruposuelo> subgruposuelos) {
		this.subgruposuelos = subgruposuelos;
	}

}
