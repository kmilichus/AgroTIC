package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.GrupohomosueloCaracteristica;

public interface IGrupoHomoSuelo_CaracteristicaDAO {
	
	public void persist(GrupohomosueloCaracteristica transientInstance);

	public void attachDirty(GrupohomosueloCaracteristica instance);

	public void attachClean(GrupohomosueloCaracteristica instance);

	public void delete(GrupohomosueloCaracteristica persistentInstance);

	public GrupohomosueloCaracteristica merge(GrupohomosueloCaracteristica detachedInstance);

	public GrupohomosueloCaracteristica findById(BigDecimal id);

	public List<GrupohomosueloCaracteristica> findByExample(GrupohomosueloCaracteristica instance);

}
