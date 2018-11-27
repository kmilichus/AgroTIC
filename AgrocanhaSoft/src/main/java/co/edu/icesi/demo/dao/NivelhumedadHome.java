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

import co.edu.icesi.demo.modelo.Nivelhumedad;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Nivelhumedad.
 * @see co.edu.icesi.demo.modelo.Nivelhumedad
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class NivelhumedadHome implements INivelHumedadDAO{

	private static final Log log = LogFactory.getLog(NivelhumedadHome.class);

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

	public void persist(Nivelhumedad transientInstance) {
		log.debug("persisting Nivelhumedad instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Nivelhumedad instance) {
		log.debug("attaching dirty Nivelhumedad instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Nivelhumedad instance) {
		log.debug("attaching clean Nivelhumedad instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Nivelhumedad persistentInstance) {
		log.debug("deleting Nivelhumedad instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Nivelhumedad merge(Nivelhumedad detachedInstance) {
		log.debug("merging Nivelhumedad instance");
		try {
			Nivelhumedad result = (Nivelhumedad) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Nivelhumedad findById(BigDecimal id) {
		log.debug("getting Nivelhumedad instance with id: " + id);
		try {
			Nivelhumedad instance = (Nivelhumedad) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Nivelhumedad", id);
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

	public List<Nivelhumedad> findByExample(Nivelhumedad instance) {
		log.debug("finding Nivelhumedad instance by example");
		try {
			List<Nivelhumedad> results = (List<Nivelhumedad>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Nivelhumedad").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
