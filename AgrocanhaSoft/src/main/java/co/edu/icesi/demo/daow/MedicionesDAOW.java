package co.edu.icesi.demo.daow;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Medicionelemento;
import co.edu.icesi.demo.modelo.Medicionmetricas;
import co.edu.icesi.demo.modelo.Metrica;

@Repository
@Scope("singleton")
public class MedicionesDAOW implements IMedicionesDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre darMedidaMetrica
	 * Descripcion Metodo que consulta en la BD una medicion de una metrica dada 
	 * @param planMuestreo
	 * @param lote
	 * @param metrica
	 * @return Medicionmetricas
	 */
	@Override
	public Medicionmetricas darMedidaMetrica(Estrategiamuestreo
			planMuestreo, Lote lote, Metrica metrica) {

		String hql = "SELECT mm FROM Medicionmetricas mm " +
		"WHERE mm.caracterizacionlote.estrategiaLote.estrategiamuestreo.planmid = :idPlan "
		+ "AND  mm.caracterizacionlote.estrategiaLote.lote.loteid = :idLote "
		+ "AND mm.rangometricas.metrica.metrid = :idMetrica";

		try {
			return (Medicionmetricas) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("idPlan", planMuestreo.getPlanmid())
					.setParameter("idLote", lote.getLoteid())
					.setParameter("idMetrica", metrica.getMetrid())
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Nombre darMedidaElementos
	 * Descripcion Metodo que consulta en la BD una medicion de un elemento dado
	 * @param planMuestreo
	 * @param lote
	 * @param elemento
	 * @return Medicionelemento
	 */
	@Override
	public Medicionelemento darMedidaElementos(Estrategiamuestreo planMuestreo, Lote lote, 
			Elemento elemento) {
		
		String hql = "SELECT mm FROM Medicionelemento mm " +
				"WHERE mm.caracterizacionlote.estrategiaLote.estrategiamuestreo.planmid = :idPlan "
				+ "AND  mm.caracterizacionlote.estrategiaLote.lote.loteid = :idLote "
				+ "AND mm.rangoelementos.elemento.elemid = :idElemento";

				try {
					return (Medicionelemento) sessionFactory.getCurrentSession().createQuery(hql)
							.setParameter("idPlan", planMuestreo.getPlanmid())
							.setParameter("idLote", lote.getLoteid())
							.setParameter("idElemento", elemento.getElemid())
							.getSingleResult();
				}

				catch (NoResultException e) {
					return null;
				}
		
		
	}

}
