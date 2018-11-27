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

import co.edu.icesi.demo.modelo.Subgruposuelo;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Subgruposuelo.
 * @see co.edu.icesi.demo.modelo.Subgruposuelo
 * @author Hibernate Tools
 */

@Repository
@Scope("singleton")
public class SubgruposueloHome implements ISubGrupoSueloDAO {

	private static final Log log = LogFactory.getLog(SubgruposueloHome.class);
	
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

	public void persist(Subgruposuelo transientInstance) {
		log.debug("persisting Subgruposuelo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Subgruposuelo instance) {
		log.debug("attaching dirty Subgruposuelo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subgruposuelo instance) {
		log.debug("attaching clean Subgruposuelo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Subgruposuelo persistentInstance) {
		log.debug("deleting Subgruposuelo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subgruposuelo merge(Subgruposuelo detachedInstance) {
		log.debug("merging Subgruposuelo instance");
		try {
			Subgruposuelo result = (Subgruposuelo) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subgruposuelo findById(BigDecimal id) {
		log.debug("getting Subgruposuelo instance with id: " + id);
		try {
			Subgruposuelo instance = (Subgruposuelo) sessionFactory.getCurrentSession()
					.get("co.edu.icesi.demo.modelo.Subgruposuelo", id);
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

	public List<Subgruposuelo> findByExample(Subgruposuelo instance) {
		log.debug("finding Subgruposuelo instance by example");
		try {
			List<Subgruposuelo> results = (List<Subgruposuelo>) sessionFactory.getCurrentSession()
					.createCriteria("co.edu.icesi.modelo.Subgruposuelo").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
