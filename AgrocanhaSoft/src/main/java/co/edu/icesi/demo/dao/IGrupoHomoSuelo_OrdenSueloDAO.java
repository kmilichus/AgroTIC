package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.GrupohomosueloOrdensuelo;

public interface IGrupoHomoSuelo_OrdenSueloDAO {
	
	public void persist(GrupohomosueloOrdensuelo transientInstance);

	public void attachDirty(GrupohomosueloOrdensuelo instance);

	public void attachClean(GrupohomosueloOrdensuelo instance);

	public void delete(GrupohomosueloOrdensuelo persistentInstance);

	public GrupohomosueloOrdensuelo merge(GrupohomosueloOrdensuelo detachedInstance);

	public GrupohomosueloOrdensuelo findById(BigDecimal id);

	public List<GrupohomosueloOrdensuelo> findByExample(GrupohomosueloOrdensuelo instance);

}
