package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Clasificaciontextura;

public interface IClasificacionTexturaDAO {
	
	public void persist(Clasificaciontextura transientInstance);

	public void attachDirty(Clasificaciontextura instance);

	public void attachClean(Clasificaciontextura instance);

	public void delete(Clasificaciontextura persistentInstance);

	public Clasificaciontextura merge(Clasificaciontextura detachedInstance);

	public Clasificaciontextura findById(BigDecimal id);

	public List<Clasificaciontextura> findByExample(Clasificaciontextura instance);

}
