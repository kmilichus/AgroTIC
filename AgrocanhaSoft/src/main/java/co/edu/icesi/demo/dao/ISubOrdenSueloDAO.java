package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Subordensuelo;

public interface ISubOrdenSueloDAO {
	
	public void persist(Subordensuelo transientInstance);

	public void attachDirty(Subordensuelo instance);

	public void attachClean(Subordensuelo instance);

	public void delete(Subordensuelo persistentInstance);

	public Subordensuelo merge(Subordensuelo detachedInstance);

	public Subordensuelo findById(BigDecimal id);

	public List<Subordensuelo> findByExample(Subordensuelo instance);

}
