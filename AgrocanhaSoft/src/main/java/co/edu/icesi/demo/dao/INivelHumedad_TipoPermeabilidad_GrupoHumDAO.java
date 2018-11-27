package co.edu.icesi.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;

public interface INivelHumedad_TipoPermeabilidad_GrupoHumDAO {
	
	public void persist(NivelhumTipopermeaGrupohum transientInstance);

	public void attachDirty(NivelhumTipopermeaGrupohum instance);

	public void attachClean(NivelhumTipopermeaGrupohum instance);

	public void delete(NivelhumTipopermeaGrupohum persistentInstance);

	public NivelhumTipopermeaGrupohum merge(NivelhumTipopermeaGrupohum detachedInstance);

	public NivelhumTipopermeaGrupohum findById(BigDecimal id);

	public List<NivelhumTipopermeaGrupohum> findByExample(NivelhumTipopermeaGrupohum instance);

}
