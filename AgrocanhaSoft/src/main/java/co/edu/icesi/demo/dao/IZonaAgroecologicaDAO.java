package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Zonaagroecologica;

public interface IZonaAgroecologicaDAO {
	
	public void persist(Zonaagroecologica transientInstance);

	public void attachDirty(Zonaagroecologica instance);

	public void attachClean(Zonaagroecologica instance);

	public void delete(Zonaagroecologica persistentInstance);

	public Zonaagroecologica merge(Zonaagroecologica detachedInstance);

	public Zonaagroecologica findById(String id);

	public List<Zonaagroecologica> findByExample(Zonaagroecologica instance);

}
