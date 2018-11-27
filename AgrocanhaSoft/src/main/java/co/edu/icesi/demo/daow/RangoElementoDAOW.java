package co.edu.icesi.demo.daow;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rangoelementos;
import co.edu.icesi.demo.modelo.Rangometricas;

@Repository
	@Scope("singleton")
public class RangoElementoDAOW implements IRangoElementoDAOW{


		@Autowired
		private SessionFactory sessionFactory;
		
		@Override
		public Rangoelementos darClaElemento(Elemento e, double ppm) {
			
			
			String hql = "SELECT re FROM Rangoelementos re " + 
			"WHERE re.elemento.nombreelem = :nombreE AND "
					+ "re.ppmmaximo >= :valorE AND re.ppmminimo <= :valorX";
			try {
				return (Rangoelementos) sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter("nombreE", e.getNombreelem()).setParameter("valorX", ppm)
						.setParameter("valorE", ppm).getSingleResult();

			} catch (NoResultException ex) {
				return null;
			}
			
		}

		@Override
		public String darValoresExtremosPPM(Elemento elemento) {

		try {

			String minimo, maximo, rta;

			String hqlMinimo = "SELECT MIN(rm.ppmminimo) FROM Rangoelementos rm "
					+ "WHERE rm.elemento.elemid = :idElemento";

			String hqlMaximo = "SELECT MAX(rm.ppmmaximo) FROM Rangoelementos rm "
					+ "WHERE rm.elemento.elemid = :idElemento";

			minimo = sessionFactory.getCurrentSession().createQuery(hqlMinimo)
					.setParameter("idElemento", elemento.getElemid()).getSingleResult() + "";

			maximo = sessionFactory.getCurrentSession().createQuery(hqlMaximo)
					.setParameter("idElemento", elemento.getElemid()).getSingleResult() + "";

			rta = "[" + minimo + "," + maximo + "]";

			return rta;

		} catch (NoResultException e) {
			return null;
		}

		}

}
