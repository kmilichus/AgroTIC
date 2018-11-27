package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.EstrategiaLote;

public interface IEstrategiaLoteDAO {
	
	public void persist(EstrategiaLote transientInstance);

	public void attachDirty(EstrategiaLote instance);

	public void attachClean(EstrategiaLote instance);

	public void delete(EstrategiaLote persistentInstance);

	public EstrategiaLote merge(EstrategiaLote detachedInstance);

	public EstrategiaLote findById(BigDecimal id);

	public List<EstrategiaLote> findByExample(EstrategiaLote instance);

}
