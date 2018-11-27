package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Terreno;

public interface ITerrenoDAO {
	
	public void persist(Terreno transientInstance);

	public void attachDirty(Terreno instance);

	public void attachClean(Terreno instance);

	public void delete(Terreno persistentInstance);

	public Terreno merge(Terreno detachedInstance);

	public Terreno findById(BigDecimal id);

	public List<Terreno> findByExample(Terreno instance);

}
