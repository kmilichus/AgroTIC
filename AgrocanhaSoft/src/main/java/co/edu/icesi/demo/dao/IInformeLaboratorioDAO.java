package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Informelaboratorio;

public interface IInformeLaboratorioDAO {
	
	public void persist(Informelaboratorio transientInstance);

	public void attachDirty(Informelaboratorio instance);

	public void attachClean(Informelaboratorio instance);

	public void delete(Informelaboratorio persistentInstance);

	public Informelaboratorio merge(Informelaboratorio detachedInstance);

	public Informelaboratorio findById(BigDecimal id);

	public List<Informelaboratorio> findByExample(Informelaboratorio instance);

}
