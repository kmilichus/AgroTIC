package co.edu.icesi.demo.daow;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Rol;

@Repository
@Scope("singleton")
public class RolDAOW implements IRolDAOW{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Rol> darTodosLosRoles() {
		
		String hql = "SELECT rol FROM Rol rol";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}

	

}
