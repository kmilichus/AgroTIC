package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Lote;

public interface ILoteDAO {
	
	public void persist(Lote transientInstance);

	public void attachDirty(Lote instance);

	public void attachClean(Lote instance);

	public void delete(Lote persistentInstance);

	public Lote merge(Lote detachedInstance);

	public Lote findById(BigDecimal id);

	public List<Lote> findByExample(Lote instance);

}
