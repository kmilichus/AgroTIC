package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Familiatextural;

public interface IFamiliaTexturalDAO {
	
	public void persist(Familiatextural transientInstance);

	public void attachDirty(Familiatextural instance);

	public void attachClean(Familiatextural instance);

	public void delete(Familiatextural persistentInstance);

	public Familiatextural merge(Familiatextural detachedInstance);

	public Familiatextural findById(BigDecimal id);

	public List<Familiatextural> findByExample(Familiatextural instance);

}
