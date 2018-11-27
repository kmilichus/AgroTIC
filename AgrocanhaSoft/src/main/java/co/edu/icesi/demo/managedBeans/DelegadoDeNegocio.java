package co.edu.icesi.demo.managedBeans;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.icesi.demo.logica.IAccesoLogic;
import co.edu.icesi.demo.logica.IAdministracionLogic;
import co.edu.icesi.demo.logica.IAgroecologicaLogic;
import co.edu.icesi.demo.logica.IDiagnosticoAgroLogic;
import co.edu.icesi.demo.logica.IEstrategiaLogic;
import co.edu.icesi.demo.logica.ILaboratorioLogic;
import co.edu.icesi.demo.logica.ITerrenoLogic;
import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.mapeo.MapeoMedicionLAB;
import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Faseaplicada;
import co.edu.icesi.demo.modelo.Fotografia;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Informelaboratorio;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Metodologia;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Nivelhumedad;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.modelo.Tipopermeabilidad;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;
import co.edu.icesi.demo.servicios.IAdministradorCadenas;

@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {

	@Autowired
	private IAccesoLogic accesoLogica;

	@Autowired
	private IAdministracionLogic administracionLogica;

	@Autowired
	private IAgroecologicaLogic agroecologicaLogica;

	@Autowired
	private IDiagnosticoAgroLogic diagnosticoAgroLogica;

	@Autowired
	private IEstrategiaLogic estrategiaLogica;

	@Autowired
	private ILaboratorioLogic laboratorioLogica;

	@Autowired
	private ITerrenoLogic terrenoLogica;

	@Autowired
	private IAdministradorCadenas administradorCadenas;

	@Override
	public boolean iniciarSesion(String cedulaUsuario, String password) throws Exception {

		return accesoLogica.iniciarSesion(cedulaUsuario, password);
	}

	@Override
	public String cambiarContraseña(String nuevoPassword, Usuario usuario) throws Exception {

		return accesoLogica.cambiarContrasenha(nuevoPassword, usuario);
	}

	@Override
	public String registrarUsuario(Usuario nuevoUsuario) throws Exception {

		return administracionLogica.registrarUsuario(nuevoUsuario);
	}

	@Override
	public String eliminarUsuario(String cedulaUsuario) throws Exception {

		return administracionLogica.eliminarUsuario(cedulaUsuario);
	}

	@Override
	public String cambiarNombreUsuario(String nuevoNombre, Usuario usuarioAModificar) throws Exception {

		return administracionLogica.cambiarNombreUsuario(nuevoNombre, usuarioAModificar);
	}

	@Override
	public Usuario buscarUsuario(String cedula) throws Exception {

		return administracionLogica.buscarUsuario(cedula);
	}

	@Override
	public Zonaagroecologica buscarZonaAgroecologica(Grupohomogeneosuelo grupoHSuelo, Grupohumedad grupoHumedad)
			throws Exception {

		return agroecologicaLogica.buscarZonaAgroecologica(grupoHSuelo, grupoHumedad);
	}

	@Override
	public List<Ingenio> darIngenios(Zonaagroecologica nZonaAgroecologica) throws Exception {

		return agroecologicaLogica.darIngenios(nZonaAgroecologica);
	}

	@Override
	public List<Zonavariedad> darZonasVariedad(Zonaagroecologica nZonaAgroecologica) throws Exception {

		return agroecologicaLogica.darZonasVariedad(nZonaAgroecologica);
	}

	@Override
	public List<Familiatextural> darFamiliasTexturales(Zonaagroecologica nZonaAgroecologica) throws Exception {

		return agroecologicaLogica.darFamiliasTexturales(nZonaAgroecologica);
	}

	@Override
	public Zonaagroecologica buscarZonaAgroecologica(String codigoZona) throws Exception {

		return agroecologicaLogica.buscarZonaAgroecologica(codigoZona);
	}

	@Override
	public List<MapeoCaracteristica> darCaracteristicasZonaAgroecologica(Zonaagroecologica zonaAgroecologica)
			throws Exception {

		return agroecologicaLogica.darCaracteristicasZonaAgroecologica(zonaAgroecologica);
	}

	@Override
	public List<Grupotextural> darGruposTexturales(Zonaagroecologica zonaAgroecologica) throws Exception {

		return agroecologicaLogica.darGruposTexturales(zonaAgroecologica);
	}

	@Override
	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica) throws Exception {

		return agroecologicaLogica.darGrupoHomogeneoSuelo(zonaAgroecologica);
	}

	@Override
	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica) throws Exception {

		return agroecologicaLogica.darGrupoHumedad(zonaAgroecologica);
	}

	@Override
	public List<NivelhumTipopermeaGrupohum> darDatosHumedad(Zonaagroecologica zonaAgroecologica) throws Exception {

		return agroecologicaLogica.darDatosHumedad(zonaAgroecologica);
	}

	@Override
	public String registrarDiagnostico(Diagnosticoagronomo diagnosticoAgronomo) throws Exception {

		return diagnosticoAgroLogica.registrarDiagnostico(diagnosticoAgronomo);
	}

	@Override
	public Diagnosticoagronomo darDiagnosticoDeEstrategia(Terreno terreno, String codigoPlanMuestreo) throws Exception {

		return diagnosticoAgroLogica.darDiagnosticoDeEstrategia(terreno, codigoPlanMuestreo);
	}

	@Override
	public String registrarEstrategiaMuestreo(Estrategiamuestreo nEstrategiaMuestreo) throws Exception {

		return estrategiaLogica.registrarEstrategiaMuestreo(nEstrategiaMuestreo);
	}

	@Override
	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia) throws Exception {

		return estrategiaLogica.buscarEstrategiaMuestreo(terreno, codigoEstrategia);
	}

	@Override
	public String actualizarCantidadPuntosRecoleccion(Estrategiamuestreo planMuestreo, Lote lote,
			int cantidadPuntosRecoleccion) throws Exception {

		return estrategiaLogica.actualizarCantidadPuntosRecoleccion(planMuestreo, lote, cantidadPuntosRecoleccion);
	}

	@Override
	public List<Estrategiamuestreo> darEstrategiasMuestro(String nombreTerreno) throws Exception {

		return estrategiaLogica.darEstrategiasMuestro(nombreTerreno);
	}


	@Override
	public String registrarPuntoRecoleccion(Puntorecoleccion nPuntoRecoleccion,
			Lote lote, Estrategiamuestreo planMuestreo, byte[] bytesFotografia) throws Exception {

		return estrategiaLogica.registrarPuntoRecoleccion(nPuntoRecoleccion,lote,planMuestreo
				,bytesFotografia);
	}

	@Override
	public String registrarValorMetrica(Estrategiamuestreo planMuestreo, Lote lote,
			BigDecimal codigoMetrica,
			BigDecimal codigoMetodologia, double magnitudValor) throws Exception {

		return laboratorioLogica.registrarValorMetrica(planMuestreo, lote, codigoMetrica, codigoMetodologia,
				magnitudValor);
	}

	@Override
	public String registrarMedicionElemento(Estrategiamuestreo planMuestreo,
			Lote lote, BigDecimal codigoElemento,
			 BigDecimal codigoMetodologia, double valorPPM,
			 double porcentajeSolubilidad) throws Exception {

		return laboratorioLogica.registrarMedicionElemento(planMuestreo, lote, codigoElemento, codigoMetodologia,
				valorPPM, porcentajeSolubilidad);
	}

	@Override
	public String actualizarClasificacionTextura(Estrategiamuestreo planMuestreo, Lote lote, String tipoTextura)
			throws Exception {

		return laboratorioLogica.actualizarClasificacionTextura(planMuestreo, lote, tipoTextura);
	}

	@Override
	public String actualizarFaseAplicada(Estrategiamuestreo planMuestreo, Lote lote, String nombreFaseAplicada)
			throws Exception {

		return laboratorioLogica.actualizarFaseAplicada(planMuestreo, lote, nombreFaseAplicada);
	}

	@Override
	public List<MapeoMedicionLAB> darAnalisisLaboratorio(Estrategiamuestreo planMuestreo, Lote lote) 
	throws Exception{

		return laboratorioLogica.darAnalisisLaboratorio(planMuestreo, lote);
	}

	@Override
	public Terreno buscarTerreno(String nombreTerreno) throws Exception {

		return terrenoLogica.buscarTerreno(nombreTerreno);
	}

	@Override
	public String registrarTerreno(Terreno nuevoTerreno) throws Exception {

		return terrenoLogica.registrarTerreno(nuevoTerreno);
	}

	@Override
	public String eliminarTerreno(Terreno terrenoABorrar) throws Exception {

		return terrenoLogica.eliminarTerreno(terrenoABorrar);
	}

	@Override
	public String actualizarNombreTerreno(Terreno terreno, String nuevoNombre) throws Exception {

		return terrenoLogica.actualizarNombreTerreno(terreno, nuevoNombre);
	}

	@Override
	public String registrarLote(Lote nuevoLote) throws Exception {

		return terrenoLogica.registrarLote(nuevoLote);

	}

	@Override
	public Lote buscarLote(Terreno terreno, String nombreLote) throws Exception {

		return terrenoLogica.buscarLote(terreno, nombreLote);
	}

	@Override
	public String actualizarNombreLote(Lote lote, String nuevoNombre) throws Exception {

		return terrenoLogica.actualizarNombreLote(lote, nuevoNombre);
	}

	@Override
	public List<Lote> darLotes(String nombreTerreno) throws Exception {

		return terrenoLogica.darLotes(nombreTerreno);

	}

	@Override
	public List<Rol> darTodosLosRoles() {
		return accesoLogica.darTodosLosRoles();
	}

	@Override
	public List<Zonaagroecologica> darTodasZonasAgroecologicas() {

		return agroecologicaLogica.darTodasZonasAgroecologicas();
	}

	@Override
	public List<Elemento> darTodosLosElementos() {

		return laboratorioLogica.darTodosLosElementos();
	}

	@Override
	public List<Faseaplicada> darTodasLasFases() {

		return laboratorioLogica.darTodasLasFases();
	}

	@Override
	public List<Metodologia> darTodasLasMetodologias() {

		return laboratorioLogica.darTodasLasMetodologias();
	}

	@Override
	public List<Metrica> darTodasLasMetricas() {

		return laboratorioLogica.darTodasLasMetricas();
	}


	@Override
	public List<Clasificaciontextura> darTodosLosTiposTextura() {

		return laboratorioLogica.darTodosLosTiposTextura();
	}

	@Override
	public List<Terreno> darTodosLosTerrenos() {

		return terrenoLogica.darTodosLosTerrenos();
	}

	@Override
	public boolean soloContieneLetrasYEspacios(String cadena) {

		return administradorCadenas.soloContieneLetrasYEspacios(cadena);
	}

	@Override
	public boolean soloContieneNumeros(String cadena) {

		return administradorCadenas.soloContieneNumeros(cadena);
	}

	@Override
	public boolean longitudPasswordValida(String password) {

		return administradorCadenas.longitudPasswordValida(password);
	}

	@Override
	public boolean tieneFormatoDecimal(String cadena) {

		return administradorCadenas.tieneFormatoDecimal(cadena);

	}

	@Override
	public String actualizarNombreLaboratorio(Estrategiamuestreo planMuestreo, String nombreLAB)
			throws Exception {
		return laboratorioLogica.actualizarNombreLaboratorio(planMuestreo, nombreLAB);
	}

	@Override
	public String actualizarNombreHacienda(Estrategiamuestreo planMuestreo, String nombreHacienda)
			throws Exception {

		return laboratorioLogica.actualizarNombreHacienda(planMuestreo, nombreHacienda);
	}

	@Override
	public Rol buscarRol(BigDecimal idRol) throws Exception {
		return administracionLogica.buscarRol(idRol);
	}

	@Override
	public String eliminarLote(Lote lote) throws Exception {
		return terrenoLogica.eliminarLote(lote);
	}

	@Override
	public void actualizarDescripcionLote(Lote lote, String nuevaDescripcion) throws Exception {

		terrenoLogica.actualizarDescripcionLote(lote, nuevaDescripcion);

	}

	@Override
	public Grupohumedad darGrupoHumedadPorID(BigDecimal idGrupoHumedad) {

		return agroecologicaLogica.darGrupoHumedadPorID(idGrupoHumedad);
	}

	@Override
	public Nivelhumedad darNivelHumedadPorID(BigDecimal idNivelHumedad) {

		return agroecologicaLogica.darNivelHumedadPorID(idNivelHumedad);
	}

	@Override
	public Tipopermeabilidad darTipoPermeabilidadPorID(BigDecimal idTipoPermeabilidad) {

		return agroecologicaLogica.darTipoPermeabilidadPorID(idTipoPermeabilidad);
	}

	@Override
	public String eliminarEstrategiaMuestreo(Estrategiamuestreo planMuestreo) throws Exception {
		
		return estrategiaLogica.eliminarEstrategiaMuestreo(planMuestreo);
	}

	@Override
	public List<Puntorecoleccion> darPuntosDeRecoleccion(Estrategiamuestreo 
			planMuestreo, Lote lote) throws Exception {
		
		return estrategiaLogica.darPuntosDeRecoleccion(planMuestreo, lote);
	}

	@Override
	public Zonaagroecologica darZonaDiagnostico(Diagnosticoagronomo diagnostico) throws Exception {
		return diagnosticoAgroLogica.darZonaDiagnostico(diagnostico);
	}

	@Override
	public boolean usoFaseSoluble(Estrategiamuestreo planMuestreo, Lote lote) {
		
		return laboratorioLogica.usoFaseSoluble(planMuestreo, lote);
		
	}

	@Override
	public Usuario darUsuarioLogueado() {
		
		return accesoLogica.darUsuarioLogueado();
	}

	@Override
	public Informelaboratorio darInformeLaboratorio(Estrategiamuestreo plan) {
		
		return estrategiaLogica.darInformeLaboratorio(plan);
	}

	@Override
	public Usuario consultarAgronomo(Diagnosticoagronomo diagnostico) {
		
		return diagnosticoAgroLogica.consultarAgronomo(diagnostico);
		
	}

	@Override
	public int darCanticadPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo) throws Exception {
		
		return estrategiaLogica.darCanticadPuntosRecoleccion(lote, planMuestreo);
	}

	@Override
	public List<Fotografia> darFotos() {
		
		return estrategiaLogica.darFotosPuntosRecoleccion();
	}

	@Override
	public List<Puntorecoleccion> darTodospuntosRecoleccion() {
		
		return estrategiaLogica.darTodospuntosRecoleccion();
	}

	@Override
	public Fotografia darFotografia(BigDecimal id) {
		return estrategiaLogica.darFotografia(id);
	}
	

}
