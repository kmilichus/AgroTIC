package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;

public interface IZonaAgroecologicaDAOW {
	
	public List<Zonaagroecologica> darTodasLasZonasAgroecologicas ();
	
	public Zonaagroecologica darZonaAgroecologica(int grupoSuelo, String grupoHumedad);

	public List<Ingenio> consultarIngenios(Zonaagroecologica nZonaAgroecologica);
	
	public List<Zonavariedad> consultarZonasVariedad(Zonaagroecologica nZonaAgroecologica);

	public List<Familiatextural> consultarFamiliasTexturales(Zonaagroecologica nZonaAgroecologica);

	public Zonaagroecologica consultarZonaPorCodigo(String codigoZona);

	public List<MapeoCaracteristica> consultarCaracteristicasZona(Zonaagroecologica zonaAgroecologica);
	
	public List<Grupotextural> consultarGruposTexturales(Zonaagroecologica zonaAgroecologica);

	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica);
	
	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica);
	
	public List<NivelhumTipopermeaGrupohum> darDatosHumedad(Zonaagroecologica zonaAgroecologica);
	
}

