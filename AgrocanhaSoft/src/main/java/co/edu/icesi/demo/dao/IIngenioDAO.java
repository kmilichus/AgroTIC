package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Ingenio;

public interface IIngenioDAO {
	
	public void persist(Ingenio transientInstance);

	public void attachDirty(Ingenio instance);

	public void attachClean(Ingenio instance);

	public void delete(Ingenio persistentInstance);

	public Ingenio merge(Ingenio detachedInstance);

	public Ingenio findById(BigDecimal id);

	public List<Ingenio> findByExample(Ingenio instance);

}
