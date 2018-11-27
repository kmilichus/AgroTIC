package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Fotografia;

public interface IFotografiaDAO {
	
	public void persist(Fotografia transientInstance);

	public void attachDirty(Fotografia instance);

	public void attachClean(Fotografia instance);

	public void delete(Fotografia persistentInstance);

	public Fotografia merge(Fotografia detachedInstance);

	public Fotografia findById(BigDecimal id);

	public List<Fotografia> findByExample(Fotografia instance);

}
