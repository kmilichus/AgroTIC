package co.edu.icesi.demo.daow;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Zonaagroecologica;

@Repository
@Scope("singleton")
public class DiagnosticoAgronomoDAOW implements IDiagnosticoAgronomoDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre consultarDiagnosticoAgronomo
	 * Descripcion Metodo que consulta y trae de la BD un diagnostico de agronomo
	 * @param codigoEstrategiaMuestreo
	 * @return Diagnosticoagronomo
	 */
	@Override
	public Diagnosticoagronomo consultarDiagnosticoAgronomo(String codigoEstrategiaMuestreo) {
		String hql = "SELECT d FROM Diagnosticoagronomo d " + "WHERE d.estrategiamuestreo.codigoest = :codigoEsMu";
		try {
			return (Diagnosticoagronomo) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("codigoEsMu", codigoEstrategiaMuestreo).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Nombre darTodosLosDiagnosticos
	 * Descripcion Metodo que consulta y trae de la BD todos los diagnosticos de agronomo
	 * @return List<Diagnosticoagronomo>
	 */
	@Override
	public List<Diagnosticoagronomo> darTodosLosDiagnosticos() {

		String hql = "SELECT da FROM Diagnosticoagronomo da";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();

	}

	/**
	 * Nombre consultarZonaDiagnostico
	 * Descripcion Metodo que consulta y trae de la BD una zona agroecologica asociada a un diagnostico dado
	 * @param diagnostico
	 * @return Zonaagroecologica
	 */
	@Override
	public Zonaagroecologica consultarZonaDiagnostico(Diagnosticoagronomo diagnostico) {

		String hql = "SELECT gd.zonaagroecologica FROM Diagnosticoagronomo gd " + "WHERE gd.diagnosticid = :codigoGd";
		try {
			return (Zonaagroecologica) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("codigoGd", diagnostico.getDiagnosticid()).getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Nombre darDiagnosticoDelPlan
	 * Descripcion Metodo que consulta y trae de la BD un diagnostico de agronomo
	 * @param plan
	 * @return Diagnosticoagronomo
	 */
	@Override
	public Diagnosticoagronomo darDiagnosticoDelPlan(Estrategiamuestreo plan) {

		String hql = "SELECT d FROM Diagnosticoagronomo d " + "WHERE d.estrategiamuestreo.planmid = :idPlan";
		try {
			return (Diagnosticoagronomo) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("idPlan", plan.getPlanmid()).getSingleResult();
		}

		catch (NoResultException e) {
			return null;

		}
	}

}
