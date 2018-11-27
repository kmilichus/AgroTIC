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

import co.edu.icesi.demo.modelo.Medicionelemento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Medicionelemento.
 * @see co.edu.icesi.demo.modelo.Medicionelemento
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class MedicionelementoHome implements IMedicionElementoDAO{

	private static final Log log = LogFactory.getLog(MedicionelementoHome.class);

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

	public void persist(Medicionelemento transientInstance) {
		log.debug("persisting Medicionelemento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Medicionelemento instance) {
		log.debug("attaching dirty Medicionelemento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Medicionelemento instance) {
		log.debug("attaching clean Medicionelemento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Medicionelemento persistentInstance) {
		log.debug("deleting Medicionelemento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Medicionelemento merge(Medicionelemento detachedInstance) {
		log.debug("merging Medicionelemento instance");
		try {
			Medicionelemento result = (Medicionelemento) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Medicionelemento findById(BigDecimal id) {
		log.debug("getting Medicionelemento instance with id: " + id);
		try {
			Medicionelemento instance = (Medicionelemento) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Medicionelemento", id);
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

	public List<Medicionelemento> findByExample(Medicionelemento instance) {
		log.debug("finding Medicionelemento instance by example");
		try {
			List<Medicionelemento> results = (List<Medicionelemento>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Medicionelemento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
