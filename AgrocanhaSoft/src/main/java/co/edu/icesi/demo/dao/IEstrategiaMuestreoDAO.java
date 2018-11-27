package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;

public interface IEstrategiaMuestreoDAO {
	
	public void persist(Estrategiamuestreo transientInstance);

	public void attachDirty(Estrategiamuestreo instance);

	public void attachClean(Estrategiamuestreo instance);

	public void delete(Estrategiamuestreo persistentInstance);

	public Estrategiamuestreo merge(Estrategiamuestreo detachedInstance);

	public Estrategiamuestreo findById(BigDecimal id);

	public List<Estrategiamuestreo> findByExample(Estrategiamuestreo instance);

}
