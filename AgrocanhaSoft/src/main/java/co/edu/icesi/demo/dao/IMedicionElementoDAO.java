package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Medicionelemento;

public interface IMedicionElementoDAO {
	
	public void persist(Medicionelemento transientInstance);

	public void attachDirty(Medicionelemento instance);

	public void attachClean(Medicionelemento instance);

	public void delete(Medicionelemento persistentInstance);

	public Medicionelemento merge(Medicionelemento detachedInstance);

	public Medicionelemento findById(BigDecimal id);

	public List<Medicionelemento> findByExample(Medicionelemento instance);

}
