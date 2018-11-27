package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Rangometricas;
import co.edu.icesi.demo.modelo.Subordensuelo;

public interface IRangoMetricaDAO {
	
	public void persist(Rangometricas transientInstance);

	public void attachDirty(Rangometricas instance);

	public void attachClean(Rangometricas instance);

	public void delete(Rangometricas persistentInstance);

	public Rangometricas merge(Rangometricas detachedInstance);

	public Rangometricas findById(BigDecimal id);

	public List<Rangometricas> findByExample(Rangometricas instance);

}
