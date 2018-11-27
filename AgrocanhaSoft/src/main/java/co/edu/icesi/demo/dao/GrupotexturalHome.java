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

import co.edu.icesi.demo.modelo.Grupotextural;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Grupotextural.
 * @see co.edu.icesi.demo.modelo.Grupotextural
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class GrupotexturalHome implements IGrupoTexturalDAO{

	private static final Log log = LogFactory.getLog(GrupotexturalHome.class);

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

	public void persist(Grupotextural transientInstance) {
		log.debug("persisting Grupotextural instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Grupotextural instance) {
		log.debug("attaching dirty Grupotextural instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grupotextural instance) {
		log.debug("attaching clean Grupotextural instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Grupotextural persistentInstance) {
		log.debug("deleting Grupotextural instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grupotextural merge(Grupotextural detachedInstance) {
		log.debug("merging Grupotextural instance");
		try {
			Grupotextural result = (Grupotextural) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Grupotextural findById(BigDecimal id) {
		log.debug("getting Grupotextural instance with id: " + id);
		try {
			Grupotextural instance = (Grupotextural) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Grupotextural", id);
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

	public List<Grupotextural> findByExample(Grupotextural instance) {
		log.debug("finding Grupotextural instance by example");
		try {
			List<Grupotextural> results = (List<Grupotextural>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Grupotextural").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
