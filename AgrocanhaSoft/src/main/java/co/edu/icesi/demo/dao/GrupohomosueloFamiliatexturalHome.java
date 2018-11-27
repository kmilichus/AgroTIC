package co.edu.icesi.demo.dao;
// Generated 10/04/2017 03:19:48 PM by Hibernate Tools 5.1.0.Beta1

import java.math.BigDecimal;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.GrupohomosueloFamiliatextural;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class GrupohomosueloFamiliatextural.
 * @see co.edu.icesi.demo.modelo.GrupohomosueloFamiliatextural
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class GrupohomosueloFamiliatexturalHome
	implements IGrupoHomoSuelo_FamiliaTexturalDAO{

	private static final Log log = LogFactory.getLog(GrupohomosueloFamiliatexturalHome.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(GrupohomosueloFamiliatextural transientInstance) {
		log.debug("persisting GrupohomosueloFamiliatextural instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(GrupohomosueloFamiliatextural instance) {
		log.debug("attaching dirty GrupohomosueloFamiliatextural instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GrupohomosueloFamiliatextural instance) {
		log.debug("attaching clean GrupohomosueloFamiliatextural instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(GrupohomosueloFamiliatextural persistentInstance) {
		log.debug("deleting GrupohomosueloFamiliatextural instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GrupohomosueloFamiliatextural merge(GrupohomosueloFamiliatextural detachedInstance) {
		log.debug("merging GrupohomosueloFamiliatextural instance");
		try {
			GrupohomosueloFamiliatextural result = (GrupohomosueloFamiliatextural) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public GrupohomosueloFamiliatextural findById(BigDecimal id) {
		log.debug("getting GrupohomosueloFamiliatextural instance with id: " + id);
		try {
			GrupohomosueloFamiliatextural instance = (GrupohomosueloFamiliatextural) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.GrupohomosueloFamiliatextural", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GrupohomosueloFamiliatextural> findByExample(GrupohomosueloFamiliatextural instance) {
		log.debug("finding GrupohomosueloFamiliatextural instance by example");
		try {
			List<GrupohomosueloFamiliatextural> results = (List<GrupohomosueloFamiliatextural>) sessionFactory
					.getCurrentSession().createCriteria("co.edu.icesi.modelo.GrupohomosueloFamiliatextural")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
