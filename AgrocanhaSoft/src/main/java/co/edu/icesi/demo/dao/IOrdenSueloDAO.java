package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Ordensuelo;

public interface IOrdenSueloDAO {
	
	public void persist(Ordensuelo transientInstance);

	public void attachDirty(Ordensuelo instance);

	public void attachClean(Ordensuelo instance);

	public void delete(Ordensuelo persistentInstance);

	public Ordensuelo merge(Ordensuelo detachedInstance);

	public Ordensuelo findById(BigDecimal id);

	public List<Ordensuelo> findByExample(Ordensuelo instance);

}
