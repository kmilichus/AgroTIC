package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Nivelhumedad;

public interface INivelHumedadDAO {
	
	public void persist(Nivelhumedad transientInstance);

	public void attachDirty(Nivelhumedad instance);

	public void attachClean(Nivelhumedad instance);

	public void delete(Nivelhumedad persistentInstance);

	public Nivelhumedad merge(Nivelhumedad detachedInstance);

	public Nivelhumedad findById(BigDecimal id);

	public List<Nivelhumedad> findByExample(Nivelhumedad instance);

}
