package co.edu.icesi.demo.dao;
// Generated 10/04/2017 03:19:48 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Zonaagroecologica;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Zonaagroecologica.
 * @see co.edu.icesi.demo.modelo.Zonaagroecologica
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class ZonaagroecologicaHome implements IZonaAgroecologicaDAO{

	private static final Log log = LogFactory.getLog(ZonaagroecologicaHome.class);

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

	public void persist(Zonaagroecologica transientInstance) {
		log.debug("persisting Zonaagroecologica instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zonaagroecologica instance) {
		log.debug("attaching dirty Zonaagroecologica instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zonaagroecologica instance) {
		log.debug("attaching clean Zonaagroecologica instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zonaagroecologica persistentInstance) {
		log.debug("deleting Zonaagroecologica instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zonaagroecologica merge(Zonaagroecologica detachedInstance) {
		log.debug("merging Zonaagroecologica instance");
		try {
			Zonaagroecologica result = (Zonaagroecologica) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zonaagroecologica findById(java.lang.String id) {
		log.debug("getting Zonaagroecologica instance with id: " + id);
		try {
			Zonaagroecologica instance = (Zonaagroecologica) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Zonaagroecologica", id);
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

	public List<Zonaagroecologica> findByExample(Zonaagroecologica instance) {
		log.debug("finding Zonaagroecologica instance by example");
		try {
			List<Zonaagroecologica> results = (List<Zonaagroecologica>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Zonaagroecologica").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
