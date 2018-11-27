package co.edu.icesi.demo.managedBeans;

import java.math.BigDecimal;
import java.util.List;

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

public interface IDelegadoDeNegocio {

	// Logica de Acceso
	public boolean iniciarSesion(String cedulaUsuario, String password) throws Exception;

	public String cambiarContraseña(String nuevoPassword, Usuario usuario) throws Exception;

	public List<Rol> darTodosLosRoles();

	// Logica de Administracion
	public String registrarUsuario(Usuario nuevoUsuario) throws Exception;

	public String eliminarUsuario(String cedulaUsuario) throws Exception;

	public String cambiarNombreUsuario(String nuevoNombre, Usuario usuarioAModificar) throws Exception;

	public Usuario buscarUsuario(String cedula) throws Exception;

	public Rol buscarRol(BigDecimal idRol) throws Exception;

	// Logica Agroecologica

	public Zonaagroecologica buscarZonaAgroecologica(Grupohomogeneosuelo grupoHSuelo, Grupohumedad grupoHumedad)
			throws Exception;

	public List<Ingenio> darIngenios(Zonaagroecologica nZonaAgroecologica) throws Exception;

	public List<Zonavariedad> darZonasVariedad(Zonaagroecologica nZonaAgroecologica) throws Exception;

	public List<Familiatextural> darFamiliasTexturales(Zonaagroecologica nZonaAgroecologica) throws Exception;

	public Zonaagroecologica buscarZonaAgroecologica(String codigoZona) throws Exception;

	public List<MapeoCaracteristica> darCaracteristicasZonaAgroecologica(Zonaagroecologica zonaAgroecologica)
			throws Exception;

	public List<Grupotextural> darGruposTexturales(Zonaagroecologica zonaAgroecologica) throws Exception;

	public Grupohomogeneosuelo darGrupoHomogeneoSuelo(Zonaagroecologica zonaAgroecologica) throws Exception;

	public Grupohumedad darGrupoHumedad(Zonaagroecologica zonaAgroecologica) throws Exception;

	public List<NivelhumTipopermeaGrupohum> darDatosHumedad(Zonaagroecologica zonaAgroecologica) throws Exception;

	public List<Zonaagroecologica> darTodasZonasAgroecologicas();

	// Logica Agronoma
	public String registrarDiagnostico(Diagnosticoagronomo diagnosticoAgronomo) throws Exception;

	public Diagnosticoagronomo darDiagnosticoDeEstrategia(Terreno terreno, String codigoPlanMuestreo) throws Exception;

	// Logica Plan Muestreo
	public String registrarEstrategiaMuestreo(Estrategiamuestreo nEstrategiaMuestreo) throws Exception;

	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia) throws Exception;

	public String actualizarCantidadPuntosRecoleccion(Estrategiamuestreo planMuestreo, Lote lote,
			int cantidadPuntosRecoleccion) throws Exception;

	public List<Estrategiamuestreo> darEstrategiasMuestro(String nombreTerreno) throws Exception;

	public String registrarPuntoRecoleccion(Puntorecoleccion nPuntoRecoleccion, Lote lote,
			Estrategiamuestreo planMuestreo, byte[] bytesFotografia) throws Exception;

	public int darCanticadPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo) throws Exception;

	// Logica Laboratorio
	public String registrarValorMetrica(Estrategiamuestreo planMuestreo, Lote lote, BigDecimal codigoMetrica,
			BigDecimal codigoMetodologia, double magnitudValor) throws Exception;

	public String registrarMedicionElemento(Estrategiamuestreo planMuestreo, Lote lote, BigDecimal codigoElemento,
			BigDecimal codigoMetodologia, double valorPPM, double porcentajeSolubilidad) throws Exception;

	public String actualizarClasificacionTextura(Estrategiamuestreo planMuestreo, Lote lote, String tipoTextura)
			throws Exception;

	public String actualizarNombreLaboratorio(Estrategiamuestreo planMuestreo, String nombreLAB) throws Exception;

	public String actualizarNombreHacienda(Estrategiamuestreo planMuestreo, String nombreHacienda) throws Exception;

	public String actualizarFaseAplicada(Estrategiamuestreo planMuestreo, Lote lote, String nombreFaseAplicada)
			throws Exception;

	public List<MapeoMedicionLAB> darAnalisisLaboratorio(Estrategiamuestreo planMuestreo, Lote lote) throws Exception;

	public List<Elemento> darTodosLosElementos();

	public List<Faseaplicada> darTodasLasFases();

	public List<Metodologia> darTodasLasMetodologias();

	public List<Metrica> darTodasLasMetricas();

	public List<Clasificaciontextura> darTodosLosTiposTextura();

	// Logica Terreno
	public Terreno buscarTerreno(String nombreTerreno) throws Exception;

	public String registrarTerreno(Terreno nuevoTerreno) throws Exception;

	public String eliminarTerreno(Terreno terrenoABorrar) throws Exception;

	public String actualizarNombreTerreno(Terreno terreno, String nuevoNombre) throws Exception;

	public String registrarLote(Lote nuevoLote) throws Exception;

	public Lote buscarLote(Terreno terreno, String nombreLote) throws Exception;

	public String actualizarNombreLote(Lote lote, String nuevoNombre) throws Exception;

	public List<Lote> darLotes(String nombreTerreno) throws Exception;

	public List<Terreno> darTodosLosTerrenos();

	// Administrador de Cadenas

	public boolean soloContieneLetrasYEspacios(String cadena);

	public boolean soloContieneNumeros(String cadena);

	public boolean longitudPasswordValida(String password);

	public boolean tieneFormatoDecimal(String cadena);

	public String eliminarLote(Lote lote) throws Exception;

	public void actualizarDescripcionLote(Lote lote, String nuevaDescripcion) throws Exception;

	public Grupohumedad darGrupoHumedadPorID(BigDecimal idGrupoHumedad);

	public Nivelhumedad darNivelHumedadPorID(BigDecimal idNivelHumedad);

	public Tipopermeabilidad darTipoPermeabilidadPorID(BigDecimal idTipoPermeabilidad);

	public String eliminarEstrategiaMuestreo(Estrategiamuestreo planMuestreo) throws Exception;

	public List<Puntorecoleccion> darPuntosDeRecoleccion(Estrategiamuestreo planMuestreo, Lote lote) throws Exception;

	public Zonaagroecologica darZonaDiagnostico(Diagnosticoagronomo diagnostico) throws Exception;

	public boolean usoFaseSoluble(Estrategiamuestreo planMuestreo, Lote lote);

	public Usuario darUsuarioLogueado();

	public Informelaboratorio darInformeLaboratorio(Estrategiamuestreo plan);

	public Usuario consultarAgronomo(Diagnosticoagronomo diagnostico);

	public List<Fotografia> darFotos();

	public List<Puntorecoleccion> darTodospuntosRecoleccion();

	public Fotografia darFotografia(BigDecimal id);

}
