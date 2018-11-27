package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Tipocaracteristica;

public interface ITipoCaracteristicaDAO {
	
	public void persist(Tipocaracteristica transientInstance);

	public void attachDirty(Tipocaracteristica instance);

	public void attachClean(Tipocaracteristica instance);

	public void delete(Tipocaracteristica persistentInstance);

	public Tipocaracteristica merge(Tipocaracteristica detachedInstance);

	public Tipocaracteristica findById(BigDecimal id);

	public List<Tipocaracteristica> findByExample(Tipocaracteristica instance);

}
