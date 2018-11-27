package co.edu.icesi.demo.daow;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Clasificaciontextura;

@Repository
@Scope("singleton")
public class TipoTexturaDAOW implements ITipoTexturaDAOW{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Clasificaciontextura> darTodosLosTiposTextura() {
		
		String hql = "SELECT tx FROM Clasificaciontextura tx";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}

	

}
