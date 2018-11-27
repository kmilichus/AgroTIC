package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Regimenhumedad;

public interface IRegimenHumedadDAO {
	
	public void persist(Regimenhumedad transientInstance);

	public void attachDirty(Regimenhumedad instance);

	public void attachClean(Regimenhumedad instance);

	public void delete(Regimenhumedad persistentInstance);

	public Regimenhumedad merge(Regimenhumedad detachedInstance);

	public Regimenhumedad findById(BigDecimal id);

	public List<Regimenhumedad> findByExample(Regimenhumedad instance);

}
