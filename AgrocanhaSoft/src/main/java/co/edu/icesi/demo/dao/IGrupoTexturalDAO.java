package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Grupotextural;

public interface IGrupoTexturalDAO {
	
	public void persist(Grupotextural transientInstance);

	public void attachDirty(Grupotextural instance);

	public void attachClean(Grupotextural instance);

	public void delete(Grupotextural persistentInstance);

	public Grupotextural merge(Grupotextural detachedInstance);

	public Grupotextural findById(BigDecimal id);

	public List<Grupotextural> findByExample(Grupotextural instance);

}
