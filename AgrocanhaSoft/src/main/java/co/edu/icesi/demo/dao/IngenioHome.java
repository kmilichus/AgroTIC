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

import co.edu.icesi.demo.modelo.Ingenio;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Ingenio.
 * @see co.edu.icesi.demo.modelo.Ingenio
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class IngenioHome implements IIngenioDAO {

	private static final Log log = LogFactory.getLog(IngenioHome.class);

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

	public void persist(Ingenio transientInstance) {
		log.debug("persisting Ingenio instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ingenio instance) {
		log.debug("attaching dirty Ingenio instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ingenio instance) {
		log.debug("attaching clean Ingenio instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Ingenio persistentInstance) {
		log.debug("deleting Ingenio instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ingenio merge(Ingenio detachedInstance) {
		log.debug("merging Ingenio instance");
		try {
			Ingenio result = (Ingenio) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ingenio findById(BigDecimal id) {
		log.debug("getting Ingenio instance with id: " + id);
		try {
			Ingenio instance = (Ingenio) sessionFactory.getCurrentSession().get("co.edu.icesi.demo.modelo.Ingenio", id);
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

	public List<Ingenio> findByExample(Ingenio instance) {
		log.debug("finding Ingenio instance by example");
		try {
			List<Ingenio> results = (List<Ingenio>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Ingenio").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
