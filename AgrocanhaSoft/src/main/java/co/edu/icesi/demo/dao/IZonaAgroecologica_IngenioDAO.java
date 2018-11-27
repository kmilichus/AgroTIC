package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.ZonaagroecologicaIngenio;

public interface IZonaAgroecologica_IngenioDAO {
	
	public void persist(ZonaagroecologicaIngenio transientInstance);

	public void attachDirty(ZonaagroecologicaIngenio instance);

	public void attachClean(ZonaagroecologicaIngenio instance);

	public void delete(ZonaagroecologicaIngenio persistentInstance);

	public ZonaagroecologicaIngenio merge(ZonaagroecologicaIngenio detachedInstance);

	public ZonaagroecologicaIngenio findById(BigDecimal id);

	public List<ZonaagroecologicaIngenio> findByExample(ZonaagroecologicaIngenio instance);

}
