package co.edu.icesi.demo.daow;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rangometricas;

@Repository
@Scope("singleton")
public class RangoMetricaDAOW implements IRangoMetricaDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre darClaMetrica
	 * Descripcion Metodo que consulta y trae de la BD un rango de metrica determinado
	 * @param Metrica m
	 * @param valor
	 * @return Rangometricas
	 */
	@Override
	public Rangometricas darClaMetrica(Metrica m, double valor) {

		String hql = "SELECT rm FROM Rangometricas rm " + "WHERE rm.metrica.nombremet = :nombreM AND "
				+ "rm.valormaximo >= :valorM AND rm.valorminimo <= :valorV";
		try {
			return (Rangometricas) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("nombreM", m.getNombremet()).setParameter("valorM", valor)
					.setParameter("valorV", valor).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Nombre darValoresExtremos
	 * Descripcion Metodo que consulta y trae de la BD los valores maximos y minimos de una metrica
	 * @param metrica
	 * @return String
	 */
	@Override
	public String darValoresExtremos(Metrica metrica) {

		try {

			String minimo, maximo, rta;

			String hqlMinimo = "SELECT MIN(rm.valorminimo) FROM Rangometricas rm "
					+ "WHERE rm.metrica.metrid = :idMetrica";

			String hqlMaximo = "SELECT MAX(rm.valormaximo) FROM Rangometricas rm "
					+ "WHERE rm.metrica.metrid = :idMetrica";

			minimo = sessionFactory.getCurrentSession().createQuery(hqlMinimo)
					.setParameter("idMetrica", metrica.getMetrid()).getSingleResult() + "";

			maximo = sessionFactory.getCurrentSession().createQuery(hqlMaximo)
					.setParameter("idMetrica", metrica.getMetrid()).getSingleResult() + "";
			
			rta = "[" + minimo + "," + maximo + "]";

			return rta;
			
		} catch (NoResultException e) {
			return null;
		}

	}

}
