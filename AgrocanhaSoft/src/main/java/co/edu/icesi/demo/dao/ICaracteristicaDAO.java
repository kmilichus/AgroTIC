package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Caracteristica;

public interface ICaracteristicaDAO {
	
	public void persist(Caracteristica transientInstance);

	public void attachDirty(Caracteristica instance);

	public void attachClean(Caracteristica instance);

	public void delete(Caracteristica persistentInstance);

	public Caracteristica merge(Caracteristica detachedInstance);

	public Caracteristica findById(BigDecimal id);

	public List<Caracteristica> findByExample(Caracteristica instance);

}
