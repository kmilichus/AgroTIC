package co.edu.icesi.demo.daow;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Metodologia;

@Repository
@Scope("singleton")
public class MetodologiaDAOW implements IMetodologiaDAOW {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre darTodasLasMetodologias
	 * Descripcion Metodo que consulta y trae de la BD todas las metodologias
	 * @return List<Metodologia>
	 */
	@Override
	public List<Metodologia> darTodasLasMetodologias(){
		
		String hql = "SELECT met FROM Metodologia met";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	}

}
