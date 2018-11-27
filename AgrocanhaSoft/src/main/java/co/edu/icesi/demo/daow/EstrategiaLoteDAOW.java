package co.edu.icesi.demo.daow;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.dao.ICaracterizacionLoteDAO;
import co.edu.icesi.demo.dao.IEstrategiaLoteDAO;
import co.edu.icesi.demo.dao.IMedicionElementoDAO;
import co.edu.icesi.demo.dao.IMedicionMetricaDAO;
import co.edu.icesi.demo.dao.IPuntoRecoleccionDAO;
import co.edu.icesi.demo.modelo.Caracterizacionlote;
import co.edu.icesi.demo.modelo.EstrategiaLote;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Medicionelemento;
import co.edu.icesi.demo.modelo.Medicionmetricas;
import co.edu.icesi.demo.modelo.Puntorecoleccion;

@Repository
@Scope("singleton")
public class EstrategiaLoteDAOW implements IEstrategiaLoteDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ICaracterizacionLoteDAO caracterizacionLoteDAO;

	@Autowired
	private IEstrategiaLoteDAO estrategiaLoteDAO;

	@Autowired
	private IMedicionElementoDAO medicionElementoDAO;

	@Autowired
	private IMedicionMetricaDAO medicionMetricaDAO;
	
	@Autowired 
	private IPuntoRecoleccionDAO puntosDAO;

	@Override
	public EstrategiaLote darEstrategiaLote(Estrategiamuestreo plan, Lote lote) {

		String hql = "SELECT el FROM EstrategiaLote el " + "WHERE el.lote.loteid = :idLote AND "
				+ "el.estrategiamuestreo.planmid = :idPlan";

		try {
			return (EstrategiaLote) sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter("idLote", lote.getLoteid()).setParameter("idPlan", plan.getPlanmid())
					.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public int darTotalEstrategiasLotes() {

		String hql = "SELECT Count(*) FROM EstrategiaLote";

		long cantidad = (Long) sessionFactory.getCurrentSession().createQuery(hql).getSingleResult();

		return Integer.parseInt(cantidad + "");
	}

	@Override
	public List<Puntorecoleccion> darPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo) {

		String hql = "SELECT p FROM Puntorecoleccion p " + "WHERE p.estrategiaLote.lote.loteid = :idLote AND "
				+ "p.estrategiaLote.estrategiamuestreo.planmid = :idPlan";

		return sessionFactory.getCurrentSession().createQuery(hql).setParameter("idLote", lote.getLoteid())
				.setParameter("idPlan", planMuestreo.getPlanmid()).getResultList();

	}

	@Override
	public void eliminarEstrategiaLote(Estrategiamuestreo plan, Lote lote) {

		EstrategiaLote planLote = darEstrategiaLote(plan, lote);

		Caracterizacionlote carLote = planLote.getCaracterizacionlote();
		
		Set<Medicionelemento> medicionesElemento = carLote.getMedicionelementos();

		Set<Medicionmetricas> medicionesMetrica = carLote.getMedicionmetricases();

		for (Medicionelemento medicionEActual : medicionesElemento) {
			medicionElementoDAO.delete(medicionEActual);
		}

		for (Medicionmetricas medicionMActual : medicionesMetrica) {
			medicionMetricaDAO.delete(medicionMActual);
		}
		
		Set<Puntorecoleccion> puntos = planLote.getPuntorecoleccions();
		
		for (Puntorecoleccion puntoActual : puntos) {
			puntosDAO.delete(puntoActual);
		}
		
		caracterizacionLoteDAO.delete(carLote);

		estrategiaLoteDAO.delete(planLote);
		
	}

}
