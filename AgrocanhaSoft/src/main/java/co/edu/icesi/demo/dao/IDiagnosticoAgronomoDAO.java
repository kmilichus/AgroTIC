package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Diagnosticoagronomo;

public interface IDiagnosticoAgronomoDAO {
	
	public void persist(Diagnosticoagronomo transientInstance);

	public void attachDirty(Diagnosticoagronomo instance);

	public void attachClean(Diagnosticoagronomo instance);

	public void delete(Diagnosticoagronomo persistentInstance);

	public Diagnosticoagronomo merge(Diagnosticoagronomo detachedInstance);

	public Diagnosticoagronomo findById(BigDecimal id);

	public List<Diagnosticoagronomo> findByExample(Diagnosticoagronomo instance);

}
