package co.edu.icesi.demo.daow;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Elemento;

@Repository
@Scope("singleton")
public class ElementoDAOW implements IElementoDAOW{
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre darTodosLosElementos
	 * Descripcion Metodo que consulta y trae de la BD todos los elementos
	 * @return List<Elemento>
	 */
	@Override
	public List<Elemento> darTodosLosElementos() {
		
		String hql = "SELECT e FROM Elemento e";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}

}
