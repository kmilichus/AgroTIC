package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Subgruposuelo;

public interface ISubGrupoSueloDAO {
	
	public void persist(Subgruposuelo transientInstance);

	public void attachDirty(Subgruposuelo instance);

	public void attachClean(Subgruposuelo instance);

	public void delete(Subgruposuelo persistentInstance);

	public Subgruposuelo merge(Subgruposuelo detachedInstance);

	public Subgruposuelo findById(BigDecimal id);

	public List<Subgruposuelo> findByExample(Subgruposuelo instance);

}
