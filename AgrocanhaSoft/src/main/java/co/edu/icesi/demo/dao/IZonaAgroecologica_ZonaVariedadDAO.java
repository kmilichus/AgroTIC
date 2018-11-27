package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.ZonaagroZonavariedad;

public interface IZonaAgroecologica_ZonaVariedadDAO {
	
	public void persist(ZonaagroZonavariedad transientInstance);

	public void attachDirty(ZonaagroZonavariedad instance);

	public void attachClean(ZonaagroZonavariedad instance);

	public void delete(ZonaagroZonavariedad persistentInstance);

	public ZonaagroZonavariedad merge(ZonaagroZonavariedad detachedInstance);

	public ZonaagroZonavariedad findById(BigDecimal id);

	public List<ZonaagroZonavariedad> findByExample(ZonaagroZonavariedad instance);

}
