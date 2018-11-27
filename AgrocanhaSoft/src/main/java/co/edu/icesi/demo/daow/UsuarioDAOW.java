package co.edu.icesi.demo.daow;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Usuario;

@Repository
@Scope("singleton")
public class UsuarioDAOW implements IUsuarioDAOW {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Usuario buscarUsuarioPorCedula(String cedula) {

		String hql = "SELECT u FROM Usuario u WHERE u.cedulausu = :cedula";

		try {
			return (Usuario) sessionFactory.getCurrentSession().createQuery(hql).setParameter("cedula", cedula)
					.getSingleResult();
			
		} catch (NoResultException e) {
			return null;

		}
	}

}
