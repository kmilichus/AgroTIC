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

import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Grupohomogeneosuelo.
 * @see co.edu.icesi.demo.modelo.Grupohomogeneosuelo
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class GrupohomogeneosueloHome implements IGrupoHomogeneoSueloDAO {

	private static final Log log = LogFactory.getLog(GrupohomogeneosueloHome.class);

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

	public void persist(Grupohomogeneosuelo transientInstance) {
		log.debug("persisting Grupohomogeneosuelo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Grupohomogeneosuelo instance) {
		log.debug("attaching dirty Grupohomogeneosuelo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grupohomogeneosuelo instance) {
		log.debug("attaching clean Grupohomogeneosuelo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Grupohomogeneosuelo persistentInstance) {
		log.debug("deleting Grupohomogeneosuelo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grupohomogeneosuelo merge(Grupohomogeneosuelo detachedInstance) {
		log.debug("merging Grupohomogeneosuelo instance");
		try {
			Grupohomogeneosuelo result = (Grupohomogeneosuelo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Grupohomogeneosuelo findById(BigDecimal id) {
		log.debug("getting Grupohomogeneosuelo instance with id: " + id);
		try {
			Grupohomogeneosuelo instance = (Grupohomogeneosuelo) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Grupohomogeneosuelo", id);
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

	public List<Grupohomogeneosuelo> findByExample(Grupohomogeneosuelo instance) {
		log.debug("finding Grupohomogeneosuelo instance by example");
		try {
			List<Grupohomogeneosuelo> results = (List<Grupohomogeneosuelo>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Grupohomogeneosuelo").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
