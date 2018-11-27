package co.edu.icesi.demo.daow;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Fotografia;

@Repository
@Scope("singleton")
public class FotografiaDAOW implements IFotografiaDAOW{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Fotografia> darTodasFotos() {
		
		String hql = "SELECT f FROM Fotografia f";

		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();

	}

	@Override
	public Fotografia darFotografia(BigDecimal id) {
		
		String hql = "SELECT f FROM Fotografia f "
				+ "WHERE f.puntorecoleccion.puntoid = :idPunto";
		
		try {
		return (Fotografia)
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idPunto", id).
				getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	
	}

	
}
