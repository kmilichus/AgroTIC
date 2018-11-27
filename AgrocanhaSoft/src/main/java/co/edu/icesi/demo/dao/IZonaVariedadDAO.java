package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Zonavariedad;

public interface IZonaVariedadDAO {
	
	public void persist(Zonavariedad transientInstance);

	public void attachDirty(Zonavariedad instance);

	public void attachClean(Zonavariedad instance);

	public void delete(Zonavariedad persistentInstance);

	public Zonavariedad merge(Zonavariedad detachedInstance);

	public Zonavariedad findById(BigDecimal id);

	public List<Zonavariedad> findByExample(Zonavariedad instance);

}
