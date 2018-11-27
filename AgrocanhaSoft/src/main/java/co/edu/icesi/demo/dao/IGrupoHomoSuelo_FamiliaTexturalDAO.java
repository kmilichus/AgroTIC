package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.GrupohomosueloFamiliatextural;

public interface IGrupoHomoSuelo_FamiliaTexturalDAO {
	
	public void persist(GrupohomosueloFamiliatextural transientInstance);

	public void attachDirty(GrupohomosueloFamiliatextural instance);

	public void attachClean(GrupohomosueloFamiliatextural instance);

	public void delete(GrupohomosueloFamiliatextural persistentInstance);

	public GrupohomosueloFamiliatextural merge(GrupohomosueloFamiliatextural detachedInstance);

	public GrupohomosueloFamiliatextural findById(BigDecimal id);

	public List<GrupohomosueloFamiliatextural> findByExample(GrupohomosueloFamiliatextural instance);

}
