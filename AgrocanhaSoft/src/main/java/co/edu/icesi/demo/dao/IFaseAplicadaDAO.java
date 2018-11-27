package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Faseaplicada;

public interface IFaseAplicadaDAO {
	
	public void persist(Faseaplicada transientInstance);

	public void attachDirty(Faseaplicada instance);

	public void attachClean(Faseaplicada instance);

	public void delete(Faseaplicada persistentInstance);

	public Faseaplicada merge(Faseaplicada detachedInstance);

	public Faseaplicada findById(BigDecimal id);

	public List<Faseaplicada> findByExample(Faseaplicada instance);

}
