package co.edu.icesi.demo.daow;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

@Repository
@Scope("singleton")
public class TerrenoDAOW implements ITerrenoDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Terreno> darTodosLosTerrenos() {

		String hql = "SELECT terr FROM Terreno terr";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();

	}

	@Override
	public List<Lote> darLotes(Terreno terreno) {

		String hql = "SELECT l FROM Lote l " + "WHERE l.terreno.terrid = :idTerreno";

		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("idTerreno", terreno.getTerrid())
				.getResultList();

	}

	@Override
	public Terreno buscarTerreno(String nombreTerreno) {

		String hql = "SELECT t FROM Terreno t " + "WHERE t.nombreterr = :nombreTerreno";

		try {
			return (Terreno) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("nombreTerreno", nombreTerreno).getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public List<Estrategiamuestreo> darEstrategiasMuestreo(Terreno terreno) {

		String hql = "SELECT e FROM Estrategiamuestreo e "
				+ "WHERE e.terreno.terrid = :idTerreno";

		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("idTerreno", terreno.getTerrid())
				.getResultList();
	}

}
