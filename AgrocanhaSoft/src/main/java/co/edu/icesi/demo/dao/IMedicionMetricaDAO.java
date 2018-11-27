package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Medicionmetricas;

public interface IMedicionMetricaDAO {
	
	public void persist(Medicionmetricas transientInstance);

	public void attachDirty(Medicionmetricas instance);

	public void attachClean(Medicionmetricas instance);

	public void delete(Medicionmetricas persistentInstance);

	public Medicionmetricas merge(Medicionmetricas detachedInstance);

	public Medicionmetricas findById(BigDecimal id);

	public List<Medicionmetricas> findByExample(Medicionmetricas instance);

}
