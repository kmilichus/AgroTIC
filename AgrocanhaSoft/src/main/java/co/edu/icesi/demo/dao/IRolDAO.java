package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Rol;

public interface IRolDAO {
	
	public void persist(Rol transientInstance);

	public void attachDirty(Rol instance);

	public void attachClean(Rol instance);

	public void delete(Rol persistentInstance);

	public Rol merge(Rol detachedInstance);

	public Rol findById(BigDecimal id);

	public List<Rol> findByExample(Rol instance);

}
