package co.edu.icesi.demo.daow;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

@Repository
@Scope("singleton")
public class LoteDAOW implements ILoteDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Nombre buscarLote
	 * Descripcion Metodo que consulta y trae de la BD un lote 
	 * @param Terreno t
	 * @param nombre
	 * @return Lote
	 */
	@Override
	public Lote buscarLote(Terreno t, String nombre) {
		String hql = "SELECT l FROM Lote l " + "WHERE l.terreno.nombreterr = :nombreTerreno"
	+ " AND l.nombrelote = :nombreLote";

		try {
			return (Lote) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("nombreTerreno", t.getNombreterr()).setParameter("nombreLote", nombre)
					.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Nombre darTodosLosLotes
	 * Descripcion Metodo que consulta y trae de la BD todos los lotes
	 * @return List<Lote>
	 */
	@Override
	public List<Lote> darTodosLosLotes() {

		String hql = "SELECT lo FROM Lote lo";
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();

	}

}
