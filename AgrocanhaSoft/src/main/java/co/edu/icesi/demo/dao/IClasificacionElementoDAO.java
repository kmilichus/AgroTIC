package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Clasificacionelemento;

public interface IClasificacionElementoDAO {
	
	public void persist(Clasificacionelemento transientInstance);

	public void attachDirty(Clasificacionelemento instance);

	public void attachClean(Clasificacionelemento instance);

	public void delete(Clasificacionelemento persistentInstance);

	public Clasificacionelemento merge(Clasificacionelemento detachedInstance);

	public Clasificacionelemento findById(BigDecimal id);

	public List<Clasificacionelemento> findByExample(Clasificacionelemento instance);

}
