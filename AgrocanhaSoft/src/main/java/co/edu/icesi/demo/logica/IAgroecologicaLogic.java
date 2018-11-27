package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Nivelhumedad;
import co.edu.icesi.demo.modelo.Tipopermeabilidad;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;

public interface IAgroecologicaLogic {

	public Zonaagroecologica buscarZonaAgroecologica(Grupohomogeneosuelo grupoHSuelo,
			Grupohumedad grupoHumedad) throws Exception;

	public List<Ingenio> darIngenios(Zonaagroecologica nZonaAgroecologica)
			throws Exception;

	public List<Zonavariedad> darZonasVariedad(Zonaagroecologica nZonaAgroecologica)
			throws Exception;

	public List<Familiatextural> darFamiliasTexturales(Zonaagroecologica nZonaAgroecologica)
			throws Exception;

	public Zonaagroecologica buscarZonaAgroecologica(String codigoZona)
			throws Exception;

	public List<MapeoCaracteristica> darCaracteristicasZonaAgroecologica
	(Zonaagroecologica zonaAgroecologica) throws Exception;

	public List<Grupotextural> darGruposTexturales(Zonaagroecologica zonaAgroecologica)
			throws Exception;

	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica)
			throws Exception;

	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica)
			throws Exception;

	public List<NivelhumTipopermeaGrupohum> darDatosHumedad
	(Zonaagroecologica zonaAgroecologica) throws Exception;
	
	public List<Zonaagroecologica> darTodasZonasAgroecologicas();

	public Grupohumedad darGrupoHumedadPorID(BigDecimal idGrupoHumedad);

	public Nivelhumedad darNivelHumedadPorID(BigDecimal idNivelHumedad);

	public Tipopermeabilidad darTipoPermeabilidadPorID(BigDecimal idTipoPermeabilidad);
	
}
