package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Metodologia;

public interface IMetodologiaDAO {
	
	public void persist(Metodologia transientInstance);

	public void attachDirty(Metodologia instance);

	public void attachClean(Metodologia instance);

	public void delete(Metodologia persistentInstance);

	public Metodologia merge(Metodologia detachedInstance);

	public Metodologia findById(BigDecimal id);

	public List<Metodologia> findByExample(Metodologia instance);

}
