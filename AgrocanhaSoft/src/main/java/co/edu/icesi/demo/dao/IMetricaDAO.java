package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Metrica;

public interface IMetricaDAO {
	
	public void persist(Metrica transientInstance);

	public void attachDirty(Metrica instance);

	public void attachClean(Metrica instance);

	public void delete(Metrica persistentInstance);

	public Metrica merge(Metrica detachedInstance);

	public Metrica findById(BigDecimal id);

	public List<Metrica> findByExample(Metrica instance);

}
