package co.edu.icesi.demo.daow;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Faseaplicada;

@Repository
@Scope("singleton")
public class FaseDAOW implements IFaseDAOW {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Faseaplicada> darTodasLasFases() {
		
		String hql = "SELECT f FROM Faseaplicada f";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}

	@Override
	public Faseaplicada darFase(String nombreFaseAplicada) {
		
		try{
		String hql = "SELECT f FROM Faseaplicada f "
				+ "WHERE f.nombrefase = :nombreFase";
		
		return (Faseaplicada)sessionFactory.getCurrentSession().
				createQuery(hql).setParameter("nombreFase", nombreFaseAplicada).getSingleResult();
		
		}catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	public Clasificaciontextura darTipoTextura(String tipoTextura) {
		
		try{
			String hql = "SELECT tx FROM Clasificaciontextura tx "
					+ "WHERE tx.nombrecla = :nombreTx";
			
			return (Clasificaciontextura)sessionFactory.getCurrentSession().
					createQuery(hql).setParameter("nombreTx", tipoTextura).getSingleResult();
			
			}catch (NoResultException e) {
				return null;
			}
		
		
	}

}
