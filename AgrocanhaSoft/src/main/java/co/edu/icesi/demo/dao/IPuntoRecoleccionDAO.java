package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Puntorecoleccion;

public interface IPuntoRecoleccionDAO {
	
	public void persist(Puntorecoleccion transientInstance);

	public void attachDirty(Puntorecoleccion instance);

	public void attachClean(Puntorecoleccion instance);

	public void delete(Puntorecoleccion persistentInstance);

	public Puntorecoleccion merge(Puntorecoleccion detachedInstance);

	public Puntorecoleccion findById(BigDecimal id);

	public List<Puntorecoleccion> findByExample(Puntorecoleccion instance);

}
