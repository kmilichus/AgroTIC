package co.edu.icesi.demo.daow;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;

@Repository
@Scope("singleton")
public class EstrategiaMuestreoDAOW implements IEstrategiaMuestreoDAOW{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Nombre buscarEstrategiaMuestreo
	 * Descripcion Metodo que consulta y trae de la BD una estrategia de muestreo
	 * @param terreno
	 * @param codigoEstrategia
	 * @return Estrategiamuestreo
	 */
	@Override
	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia) {

		String hql = "SELECT e FROM Estrategiamuestreo e "
				+ "WHERE e.terreno.terrid = :idTerreno AND e.codigoest = :codigoPlan";
		
		try {
		return (Estrategiamuestreo)
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("idTerreno", terreno.getTerrid())
				.setParameter("codigoPlan", codigoEstrategia).
				getSingleResult();
	
		}

		catch (NoResultException e) {
			return null;
		}
	
	}

	/**
	 * Nombre darPuntosRecoleccion
	 * Descripcion Metodo que consulta y trae de la BD todos los puntos de recoleccion
	 * @return List<Puntorecoleccion>
	 */
	@Override
	public List<Puntorecoleccion> darPuntosRecoleccion() {
		String hql = "SELECT p FROM Puntorecoleccion p ";
		
		
		return sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	
	}


}
