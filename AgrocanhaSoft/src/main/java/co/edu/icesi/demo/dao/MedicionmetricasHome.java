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

import co.edu.icesi.demo.modelo.Medicionmetricas;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Medicionmetricas.
 * @see co.edu.icesi.demo.modelo.Medicionmetricas
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class MedicionmetricasHome implements IMedicionMetricaDAO{

	private static final Log log = LogFactory.getLog(MedicionmetricasHome.class);

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

	public void persist(Medicionmetricas transientInstance) {
		log.debug("persisting Medicionmetricas instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Medicionmetricas instance) {
		log.debug("attaching dirty Medicionmetricas instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Medicionmetricas instance) {
		log.debug("attaching clean Medicionmetricas instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Medicionmetricas persistentInstance) {
		log.debug("deleting Medicionmetricas instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Medicionmetricas merge(Medicionmetricas detachedInstance) {
		log.debug("merging Medicionmetricas instance");
		try {
			Medicionmetricas result = (Medicionmetricas) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Medicionmetricas findById(BigDecimal id) {
		log.debug("getting Medicionmetricas instance with id: " + id);
		try {
			Medicionmetricas instance = (Medicionmetricas) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Medicionmetricas", id);
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

	public List<Medicionmetricas> findByExample(Medicionmetricas instance) {
		log.debug("finding Medicionmetricas instance by example");
		try {
			List<Medicionmetricas> results = (List<Medicionmetricas>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Medicionmetricas").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
