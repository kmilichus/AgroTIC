package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Caracterizacionlote;

public interface ICaracterizacionLoteDAO {
	
	public void persist(Caracterizacionlote transientInstance);

	public void attachDirty(Caracterizacionlote instance);

	public void attachClean(Caracterizacionlote instance);

	public void delete(Caracterizacionlote persistentInstance);

	public Caracterizacionlote merge(Caracterizacionlote detachedInstance);

	public Caracterizacionlote findById(BigDecimal id);

	public List<Caracterizacionlote> findByExample(Caracterizacionlote instance);

}
