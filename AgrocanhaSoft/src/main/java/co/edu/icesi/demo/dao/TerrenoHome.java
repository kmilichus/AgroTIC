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

import co.edu.icesi.demo.modelo.Terreno;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Terreno.
 * @see co.edu.icesi.demo.modelo.Terreno
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class TerrenoHome implements ITerrenoDAO{

	private static final Log log = LogFactory.getLog(TerrenoHome.class);

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

	public void persist(Terreno transientInstance) {
		log.debug("persisting Terreno instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Terreno instance) {
		log.debug("attaching dirty Terreno instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Terreno instance) {
		log.debug("attaching clean Terreno instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Terreno persistentInstance) {
		log.debug("deleting Terreno instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Terreno merge(Terreno detachedInstance) {
		log.debug("merging Terreno instance");
		try {
			Terreno result = (Terreno) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Terreno findById(BigDecimal id) {
		log.debug("getting Terreno instance with id: " + id);
		try {
			Terreno instance = (Terreno) sessionFactory.getCurrentSession().get("co.edu.icesi.demo.modelo.Terreno", id);
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

	public List<Terreno> findByExample(Terreno instance) {
		log.debug("finding Terreno instance by example");
		try {
			List<Terreno> results = (List<Terreno>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Terreno").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
