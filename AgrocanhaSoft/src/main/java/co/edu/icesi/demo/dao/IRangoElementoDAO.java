package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Rangoelementos;

public interface IRangoElementoDAO {
	
	public void persist(Rangoelementos transientInstance);

	public void attachDirty(Rangoelementos instance);

	public void attachClean(Rangoelementos instance);

	public void delete(Rangoelementos persistentInstance);

	public Rangoelementos merge(Rangoelementos detachedInstance);

	public Rangoelementos findById(BigDecimal id);

	public List<Rangoelementos> findByExample(Rangoelementos instance);

}
