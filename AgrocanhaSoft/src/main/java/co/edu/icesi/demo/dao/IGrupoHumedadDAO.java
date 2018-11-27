package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Grupohumedad;

public interface IGrupoHumedadDAO {
	
	public void persist(Grupohumedad transientInstance);

	public void attachDirty(Grupohumedad instance);

	public void attachClean(Grupohumedad instance);

	public void delete(Grupohumedad persistentInstance);

	public Grupohumedad merge(Grupohumedad detachedInstance);

	public Grupohumedad findById(BigDecimal id);

	public List<Grupohumedad> findByExample(Grupohumedad instance);

}
