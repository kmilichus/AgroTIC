package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Tipopermeabilidad;

public interface ITipoPermeabilidadDAO {
	
	public void persist(Tipopermeabilidad transientInstance);

	public void attachDirty(Tipopermeabilidad instance);

	public void attachClean(Tipopermeabilidad instance);

	public void delete(Tipopermeabilidad persistentInstance);

	public Tipopermeabilidad merge(Tipopermeabilidad detachedInstance);

	public Tipopermeabilidad findById(BigDecimal id);

	public List<Tipopermeabilidad> findByExample(Tipopermeabilidad instance);

}
