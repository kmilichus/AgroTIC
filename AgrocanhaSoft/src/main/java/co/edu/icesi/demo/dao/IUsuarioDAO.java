package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Usuario;

public interface IUsuarioDAO {
	
	public void persist(Usuario transientInstance);

	public void attachDirty(Usuario instance);

	public void attachClean(Usuario instance);

	public void delete(Usuario persistentInstance);

	public Usuario merge(Usuario detachedInstance);

	public Usuario findById(BigDecimal id);

	public List<Usuario> findByExample(Usuario instance);

}
