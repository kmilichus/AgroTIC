package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IGrupoHomogeneoSueloDAO;
import co.edu.icesi.demo.dao.IGrupoHumedadDAO;
import co.edu.icesi.demo.dao.INivelHumedadDAO;
import co.edu.icesi.demo.dao.ITipoPermeabilidadDAO;
import co.edu.icesi.demo.dao.IZonaAgroecologicaDAO;
import co.edu.icesi.demo.daow.IZonaAgroecologicaDAOW;
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

@Scope("singleton")
@Service("agroecologicaLogic")
public class AgroecologicaLogic implements IAgroecologicaLogic {

	@Autowired
	private IZonaAgroecologicaDAOW agroecologicaDAOW;

	@Autowired
	private IGrupoHumedadDAO grupoHumedadDAO;

	@Autowired
	private IGrupoHomogeneoSueloDAO grupoSueloDAO;

	@Autowired
	private IZonaAgroecologicaDAOW zonaAgroDAOW;

	@Autowired
	private IZonaAgroecologicaDAO zonaAgroDAO;
	
	@Autowired
	private ITipoPermeabilidadDAO tipoPermeabilidadDAO;

	@Autowired
	private INivelHumedadDAO nivelHumedadDAO;

	
	/**
	 * Nombre: buscarZonaAgroecologica
	 * Descripcion: Entrega una ZonaAgroecologica, buscandola por medio del grupo de Suelo y
	 * 				el grupo de Humedad que conforman dicha Zona
	 * @param - grupoHSuelo : Instancia del grupo Homogeneo de Suelos
	 * @param - grupoHumedad : Instancia del grupo de Humedad
	 * @return - Zonaagroecologica : Instancia de la zona agroecologica que está conformada
	 * 			por el grupo de suelos y el grupo de humedad descritos anteriormente.
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Zonaagroecologica buscarZonaAgroecologica(Grupohomogeneosuelo grupoHSuelo, Grupohumedad grupoHumedad)
			throws Exception {

		Zonaagroecologica zonaEncontrada = null;

		if (grupoHSuelo == null)
			throw new Exception("El Grupo Homogeneo de Suelos es Nulo!");

		if (grupoHumedad == null)
			throw new Exception("El Grupo Humedad es Nulo!");

		Grupohomogeneosuelo ghSuelo = grupoSueloDAO.findById(grupoHSuelo.getGrhomosueloid());

		if (ghSuelo == null)
			throw new Exception("El Grupo Homogeneo Suelo no Existe!");

		Grupohumedad gHumedad = grupoHumedadDAO.findById(grupoHumedad.getGrupohumid());

		if (gHumedad == null)
			throw new Exception("El Grupo Humedad no Existe!");

		int numeroGrupoSuelos = grupoHSuelo.getNumerogrupo();
		String siglasGrupoHumedad = grupoHumedad.getSiglasgrhum();

		zonaEncontrada = zonaAgroDAOW.darZonaAgroecologica(numeroGrupoSuelos, siglasGrupoHumedad);

		return zonaEncontrada;
	}

	/**
	 * Nombre: darIngenios
	 * Descripcion: Entrega los ingenios, en donde la ZonaAgroecologica está
	 * 				presente
	 * @param - nZonaAgroecologica : Instancia de la ZonaAgroecologica, a la que se
	 * 			quieren consultar los ingenios
	 * @return - List<Ingenios> : Lista de los ingenios, en donde se encuentra la 
	 * 			 zona agroecologica
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<Ingenio> darIngenios(Zonaagroecologica nZonaAgroecologica) throws Exception {

		List<Ingenio> ingenios = null;

		if (nZonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(nZonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		ingenios = zonaAgroDAOW.consultarIngenios(nZonaAgroecologica);

		return ingenios;
	}
	
	/**
	 * Nombre: darZonasVariedad
	 * Descripcion: Entrega las Zonas-Variedad que caracterizan a una Zona Agroecologica
	 * @param - nZonaAgroecologica : Instancia de la ZonaAgroecologica, a la que se
	 * 			quieren consultar sus Zonas de Variedad
	 * @return - List<ZonaVariedad> : Lista de las Zonas de Variedad, pertenecientes a la 
	 * 			 zona agroecologica
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<Zonavariedad> darZonasVariedad(Zonaagroecologica nZonaAgroecologica) throws Exception {

		List<Zonavariedad> zonasVar = null;

		if (nZonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(nZonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		zonasVar = zonaAgroDAOW.consultarZonasVariedad(nZonaAgroecologica);

		return zonasVar;
	}
	
	/**
	 * Nombre: darFamiliasTexturales
	 * Descripcion: Entrega las Familias Texturales que caracterizan a una Zona Agroecologica
	 * @param - nZonaAgroecologica : Instancia de la ZonaAgroecologica, a la que se
	 * 			quieren consultar sus familias texturales
	 * @return - List<ZonaVariedad> : Lista de las familias texturales, pertenecientes a la 
	 * 			 zona agroecologica
	 * **/

	@Override
	@Transactional(readOnly = true)
	public List<Familiatextural> darFamiliasTexturales(Zonaagroecologica nZonaAgroecologica) throws Exception {

		List<Familiatextural> familiasTx = null;

		if (nZonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(nZonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		familiasTx = zonaAgroDAOW.consultarFamiliasTexturales(nZonaAgroecologica);

		return familiasTx;
	}

	/**
	 * Nombre: buscarZonaAgroecologica
	 * Descripcion: Entrega una Zona Agroecologica, buscandola por su codigo
	 * @param - codigoZona - El codigo (2H0, 22H6, etc...) de la Zona que se quiere
	 * 			consultar
	 * @return - ZonaAgroecologica - Instancia de la Zona Agroecologica que se encontró 
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Zonaagroecologica buscarZonaAgroecologica(String codigoZona) throws Exception {

		Zonaagroecologica zonaAgroEncontrada = null;

		if (codigoZona == null || codigoZona.trim().equals(" "))
			throw new Exception("El codigo de la Zona es vacío!");

		zonaAgroEncontrada = zonaAgroDAOW.consultarZonaPorCodigo(codigoZona);

		return zonaAgroEncontrada;

	}
	
	/**
	 * Nombre:  darCaracteristicasZonaAgroecologica
	 * Descripcion: Entrega las caracteristicas de una Zona Agroecologica. Cada caracteristica
	 * 				esta representada en un objeto de mapeo llamado "Mapeo Caracteristica".
	 * 				Cada instancia de los objetos anteriores poseen un titulo que describe la
	 * 				respectiva caracteristica y la descripción detallada de dicha caracteristica.
	 * @param - zonaAgroecologica : Nombre de la zona agroecologica a la que se le quieren
	 * 			consultar sus caracteristicas
	 * @return - List<MapeoCaracteristica> - Lista de caracteristicas de la Zona Agroecologica
	 * **/

	@Override
	@Transactional(readOnly = true)
	public List<MapeoCaracteristica> darCaracteristicasZonaAgroecologica(Zonaagroecologica zonaAgroecologica)
			throws Exception {

		List<MapeoCaracteristica> caracteristicasZona = null;

		if (zonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(zonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		caracteristicasZona = zonaAgroDAOW.consultarCaracteristicasZona(zonaAgroecologica);

		return caracteristicasZona;

	}

	/**
	 * Nombre: zonaAgroecologica
	 * Descripcion: Entrega los Grupos Texturales que perteneces a una Zona Agroecologica
	 * @param - zonaAgroecologica : La zonaAgroecologica a la que se le quieren
	 * 			consultar sus grupos texturales
	 * @return - List<GrupoTextural> - Lista de los grupos texturales a la que pertenece la
	 * 			 zona agroecologica
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<Grupotextural> darGruposTexturales(Zonaagroecologica zonaAgroecologica) throws Exception {

		List<Grupotextural> gruposTexturales = null;

		if (zonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(zonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		gruposTexturales = agroecologicaDAOW.consultarGruposTexturales(zonaAgroecologica);

		return gruposTexturales;
	}
	
	/**
	 * Nombre: darGrupoHomogeneoSuelo
	 * Descripcion: Entrega el grupo Homogeneo que conforma la zona Agroecologica dada
	 * @param - zonaAgroecologica - Zona a la que se le quiere consultar su grupo Homogeneo
	 * @return - GrupoHomogeneoSuelo - Instancia del grupo homogeneo de la zona agroecologica
	 * 			 dada
	 * **/

	@Override
	@Transactional(readOnly = true)
	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica) throws Exception {

		if (zonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(zonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		return agroecologicaDAOW.darGrupoHomogeneoSuelo(zonaAgroecologica);
	}

	/**
	 * Nombre: darGrupoHumedad
	 * Descripcion: Entrega el grupo de humedad que conforma la zona Agroecologica dada
	 * @param - zonaAgroecologica - Zona a la que se le quiere consultar su grupo de Humedad
	 * @return - GrupoHomogeneoSuelo - Instancia del grupo de humedad de la zona agroecologica
	 * 			 dada
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica) throws Exception {

		if (zonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(zonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		return agroecologicaDAOW.darGrupoHumedad(zonaAgroecologica);
	}

	/**
	 * Nombre: darDatosHumedad
	 * Descripcion: Entrega los datos de humedad de la Zona Agroecologica dada
	 * @param - zonaAgroecologica - Zona a la que se le quiere consultar sus datos de Humedad
	 * @return - List<NivelhumTipopermeaGrupohum> - Lista de los datos
	 * 			 de humedad de la zona agroecologica dada
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<NivelhumTipopermeaGrupohum> darDatosHumedad
	(Zonaagroecologica zonaAgroecologica) throws Exception {

		if (zonaAgroecologica == null)
			throw new Exception("La Zona Agroecologica es nula!");

		Zonaagroecologica zonaBuscada = zonaAgroDAO.findById(zonaAgroecologica.getZonaagroid());

		if (zonaBuscada == null)
			throw new Exception("La Zona Agroecologica no está registrada!");

		return agroecologicaDAOW.darDatosHumedad(zonaAgroecologica);
		
	}

	/**
	 * Nombre: darTodasZonasAgroecologicas
	 * Descripcion: Entrega todas las zonas agroecologicas registradas por Cenicaña
	 * @return - List<ZonaAgroecologica> - Lista de todas las zonas agroecologicas
	 * 			 registradas por Cenicaña
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<Zonaagroecologica> darTodasZonasAgroecologicas() {
		return agroecologicaDAOW.darTodasLasZonasAgroecologicas();
	}

	/**
	 * Nombre: darGrupoHumedadPorID
	 * Descripcion: Entrega un Grupo de Humedad, consultandolo por medio de su identificador
	 * @param - idGrupoHumedad : Identificador del Grupo de Humedad que se quiere
	 * 			consultar
	 * @return - GrupoHumedad : Instancia del Grupo de Humedad encontrado
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Grupohumedad darGrupoHumedadPorID(BigDecimal idGrupoHumedad) {
		return grupoHumedadDAO.findById(idGrupoHumedad);
	}

	/**
	 * Nombre: darNivelHumedadPorID
	 * Descripcion: Entrega un Nivel de Humedad, consultandolo por medio de su identificador
	 * @param - idNivelHumedad : Identificador del Nivel de Humedad que se quiere
	 * 			consultar
	 * @return - NivelHumedad : Instancia del Nivel de Humedad encontrado
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Nivelhumedad darNivelHumedadPorID(BigDecimal idNivelHumedad) {
		return nivelHumedadDAO.findById(idNivelHumedad);
	}
	
	/**
	 * Nombre: darTipoPermeabilidadPorID
	 * Descripcion: Entrega un Tipo de Permeabilidad, consultandolo por medio de su identificador
	 * @param - idTipoPermeabilidad : Identificador del Tipo de Permeabilidad que se quiere
	 * 			consultar
	 * @return - TipoPermeabilidad : Instancia del tipo de Permeabilidad encontrado
	 * **/

	@Override
	@Transactional(readOnly = true)
	public Tipopermeabilidad darTipoPermeabilidadPorID(BigDecimal idTipoPermeabilidad) {
		return tipoPermeabilidadDAO.findById(idTipoPermeabilidad);
	}

}
