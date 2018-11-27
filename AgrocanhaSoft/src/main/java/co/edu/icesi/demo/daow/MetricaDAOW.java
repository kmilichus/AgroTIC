package co.edu.icesi.demo.daow;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Metrica;

@Repository
@Scope("singleton")
public class MetricaDAOW implements IMetricaDAOW{

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre darTodasLasMetricas
	 * Descripcion Metodo que consulta y trae de la BD todas las metricas 
	 * @return List<Metrica>
	 */
	@Override
	public List<Metrica> darTodasLasMetricas() {
		
		String hql = "SELECT m FROM Metrica m";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}

}
