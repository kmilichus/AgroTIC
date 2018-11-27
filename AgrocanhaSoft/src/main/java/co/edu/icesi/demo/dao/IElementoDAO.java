package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Elemento;

public interface IElementoDAO {
	
	public void persist(Elemento transientInstance);

	public void attachDirty(Elemento instance);

	public void attachClean(Elemento instance);

	public void delete(Elemento persistentInstance);

	public Elemento merge(Elemento detachedInstance);

	public Elemento findById(BigDecimal id);

	public List<Elemento> findByExample(Elemento instance);

}
