package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;

public interface IGrupoHomogeneoSueloDAO {
	
	public void persist(Grupohomogeneosuelo transientInstance);

	public void attachDirty(Grupohomogeneosuelo instance);

	public void attachClean(Grupohomogeneosuelo instance);

	public void delete(Grupohomogeneosuelo persistentInstance);

	public Grupohomogeneosuelo merge(Grupohomogeneosuelo detachedInstance);

	public Grupohomogeneosuelo findById(BigDecimal id);

	public List<Grupohomogeneosuelo> findByExample(Grupohomogeneosuelo instance);

}
