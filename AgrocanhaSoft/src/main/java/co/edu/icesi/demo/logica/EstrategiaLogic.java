package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ICaracterizacionLoteDAO;
import co.edu.icesi.demo.dao.IDiagnosticoAgronomoDAO;
import co.edu.icesi.demo.dao.IEstrategiaLoteDAO;
import co.edu.icesi.demo.dao.IEstrategiaMuestreoDAO;
import co.edu.icesi.demo.dao.IFotografiaDAO;
import co.edu.icesi.demo.dao.IInformeLaboratorioDAO;
import co.edu.icesi.demo.dao.ILoteDAO;
import co.edu.icesi.demo.dao.IPuntoRecoleccionDAO;
import co.edu.icesi.demo.dao.ITerrenoDAO;
import co.edu.icesi.demo.daow.IDiagnosticoAgronomoDAOW;
import co.edu.icesi.demo.daow.IEstrategiaLoteDAOW;
import co.edu.icesi.demo.daow.IEstrategiaMuestreoDAOW;
import co.edu.icesi.demo.daow.IFotografiaDAOW;
import co.edu.icesi.demo.daow.ITerrenoDAOW;
import co.edu.icesi.demo.modelo.Caracterizacionlote;
import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.EstrategiaLote;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Fotografia;
import co.edu.icesi.demo.modelo.Informelaboratorio;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.servicios.IGeneradorID;

@Scope("singleton")
@Service("estrategiaLogic")
public class EstrategiaLogic implements IEstrategiaLogic {

	@Autowired
	private IEstrategiaMuestreoDAO estrategiaMuestreoDAO;

	@Autowired
	private IEstrategiaMuestreoDAOW estrategiaMuestreoDAOW;

	@Autowired
	private ITerrenoDAO terrenoDAO;

	@Autowired
	private ITerrenoDAOW terrenoDAOW;

	@Autowired
	ILoteDAO loteDAO;

	@Autowired
	private IGeneradorID generadorID;

	@Autowired
	private IEstrategiaLoteDAOW estrategiaLoteDAOW;

	@Autowired
	private IEstrategiaLoteDAO estrategiaLoteDAO;

	@Autowired
	private IPuntoRecoleccionDAO puntoRecoleccionDAO;

	@Autowired
	private ICaracterizacionLoteDAO caracterizacionLoteDAO;

	@Autowired
	private IDiagnosticoAgronomoDAO diagnosticoDAO;

	@Autowired
	private IDiagnosticoAgronomoDAOW diagnosticoAgronomoDAOW;

	@Autowired
	private IInformeLaboratorioDAO informeDAO;

	@Autowired
	private IFotografiaDAO fotografiaDAO;

	@Autowired IFotografiaDAOW fotografiaDAOW;
	
	private final static int LONGITUD_MAXIMA_NOMBRE_PLAN_MUESTREO = 10;

	
	/**
	 * Nombre registrarEstrategiaMuestreo
	 * Descripcion Metodo que realiza toda la lógica para el registro una estrategia de muestreo al sistema
	 * @Param nEstrategiaMuestreo
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.IEstrategiaLogic#registrarEstrategiaMuestreo(co.edu.icesi.demo.modelo.Estrategiamuestreo)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarEstrategiaMuestreo(Estrategiamuestreo nEstrategiaMuestreo) throws Exception {

		if (nEstrategiaMuestreo == null)
			throw new Exception("El Plan de Muestreo es nulo!");

		if (nEstrategiaMuestreo.getCodigoest() == null || nEstrategiaMuestreo.getCodigoest().trim().equals(""))
			throw new Exception("El Codigo de la Estrategia no puede ser vacio!");

		if (nEstrategiaMuestreo.getCodigoest().length() > LONGITUD_MAXIMA_NOMBRE_PLAN_MUESTREO)
			throw new Exception("El Codigo de la Estrategia no puede tener " + "mas de 10 caracteres!");

		if (nEstrategiaMuestreo.getTerreno() == null)
			throw new Exception("La estrategia debe estar asociada a un terreno!");

		Estrategiamuestreo buscada = buscarEstrategiaMuestreo(nEstrategiaMuestreo.getTerreno(),
				nEstrategiaMuestreo.getCodigoest());

		if (buscada != null)
			throw new Exception("Ya hay una Estrategia con el mismo Codigo/Nombre!");

		nEstrategiaMuestreo.setPlanmid(generadorID.generarID());
		nEstrategiaMuestreo.setFechacreacion(Calendar.getInstance().getTime());

		estrategiaMuestreoDAO.persist(nEstrategiaMuestreo);

		Informelaboratorio informelaboratorio = new Informelaboratorio();
		informelaboratorio.setInformeid(generadorID.generarID());
		informelaboratorio.setEstrategiamuestreo(nEstrategiaMuestreo);

		informeDAO.persist(informelaboratorio);

		nEstrategiaMuestreo.setInformelaboratorio(informelaboratorio);

		estrategiaMuestreoDAO.merge(nEstrategiaMuestreo);

		List<Lote> lotesDelTerreno = terrenoDAOW.darLotes(nEstrategiaMuestreo.getTerreno());

		for (int i = 0; i < lotesDelTerreno.size(); i++) {

			Lote loteActual = lotesDelTerreno.get(i);

			EstrategiaLote estrategiaLote = new EstrategiaLote();

			estrategiaLote.setNumeropuntos(0L);
			estrategiaLote.setLote(loteActual);
			estrategiaLote.setEstrategiamuestreo(nEstrategiaMuestreo);

			Thread.sleep(15);

			estrategiaLote.setEstrategialoteid(generadorID.generarID());

			estrategiaLoteDAO.persist(estrategiaLote);

			Caracterizacionlote car = new Caracterizacionlote();
			car.setCarid(generadorID.generarID());
			car.setEstrategiaLote(estrategiaLote);
			estrategiaLote.setCaracterizacionlote(car);

			caracterizacionLoteDAO.persist(car);

		}

		return "La Estrategia fue registrada exitosamente!";
	}

	/**
	 * Nombre buscarEstrategiaMuestreo
	 * Descripcion Metodo que realiza la busqueda de una estrategia de muestreo
	 * @Param terreno, codigoEstrategia
	 * @Return Estrategiamuestreo
	 * @see co.edu.icesi.demo.logica.IEstrategiaLogic#buscarEstrategiaMuestreo(co.edu.icesi.demo.modelo.Terreno, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia) throws Exception {

		if (terreno == null)
			throw new Exception("El Terreno no puede ser nulo!");

		if (terreno.getTerrid().compareTo(new BigDecimal("0")) == 0)
			throw new Exception("El Id del Terreno no puede ser nulo!");

		Terreno encontrado = terrenoDAO.findById(terreno.getTerrid());

		if (encontrado == null)
			throw new Exception("El Terreno especificado no esta registrado!");

		if (codigoEstrategia == null || codigoEstrategia.trim().equals(""))
			throw new Exception("El Codigo de la Estrategia no puede ser vacio!");

		return estrategiaMuestreoDAOW.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

	}

	/**
	 * Nombre actualizarCantidadPuntosRecoleccion
	 * Descripcion Metodo que realiza la actualizacion de la cantidad de puntos de recolección
	 * @Param planMuestreo, lote, cantidadPuntosRecoleccion
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.IEstrategiaLogic#actualizarCantidadPuntosRecoleccion(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote, int)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarCantidadPuntosRecoleccion(Estrategiamuestreo planMuestreo, Lote lote,
			int cantidadPuntosRecoleccion) throws Exception {

		if (planMuestreo == null)
			throw new Exception("El Plan de Muestreo especificado es nulo!");

		if (lote == null)
			throw new Exception("El Lote especificado es nulo!");

		Estrategiamuestreo planBuscado = estrategiaMuestreoDAO.findById(planMuestreo.getPlanmid());

		if (planBuscado == null)
			throw new Exception("El Plan de Muestreo no esta registrado!");

		Lote loteBuscado = loteDAO.findById(lote.getLoteid());

		if (loteBuscado == null)
			throw new Exception("El Lote no esta registrado!");

		if (cantidadPuntosRecoleccion < 0)
			throw new Exception("La cantidad de Puntos debe ser al menos 1!");

		EstrategiaLote planLote = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		planLote.setNumeropuntos(Long.parseLong(cantidadPuntosRecoleccion + ""));

		estrategiaLoteDAO.merge(planLote);

		return "Estrategia de Muestro actualizada para el lote '" + lote.getNombrelote() + "'!";
	}

	/**
	 * Nombre darEstrategiasMuestro
	 * Descripcion Metodo que consulta las estrategias de muestreo
	 * @Param nombreTerreno
	 * @Return List<Estrategiamuestreo>
	 * @see co.edu.icesi.demo.logica.IEstrategiaLogic#darEstrategiasMuestro(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Estrategiamuestreo> darEstrategiasMuestro(String nombreTerreno) throws Exception {

		if (nombreTerreno == null || nombreTerreno.trim().equals(""))
			throw new Exception("El nombre del Terreno es vacio!");

		Terreno terreno = terrenoDAOW.buscarTerreno(nombreTerreno);

		if (terreno == null)
			throw new Exception("No hay un terreno registrado con el nombre especificado!");

		return terrenoDAOW.darEstrategiasMuestreo(terreno);
	}

	/**
	 * Nombre seHanRegistradoTodosLosPuntos
	 * Descripcion Metodo que verifica si se han registrado todos los puntos de recoleccion
	 * @param lote
	 * @param planMuestreo
	 * @return boolean
	 * @throws Exception
	 */
	private boolean seHanRegistradoTodosLosPuntos(Lote lote, Estrategiamuestreo planMuestreo) throws Exception {

		if (planMuestreo == null)
			throw new Exception("El Plan de Muestreo especificado es nulo!");

		if (lote == null)
			throw new Exception("El Lote especificado es nulo!");

		Estrategiamuestreo planBuscado = estrategiaMuestreoDAO.findById(planMuestreo.getPlanmid());

		if (planBuscado == null)
			throw new Exception("El Plan de Muestreo no esta registrado!");

		Lote loteBuscado = loteDAO.findById(lote.getLoteid());

		if (loteBuscado == null)
			throw new Exception("El Lote no está registrado!");

		EstrategiaLote estrategiaLote = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		List<Puntorecoleccion> puntos = estrategiaLoteDAOW.darPuntosRecoleccion(lote, planMuestreo);

		int cantidadPuntosPlaneados = Integer.parseInt(estrategiaLote.getNumeropuntos() + "");

		if (puntos.size() == cantidadPuntosPlaneados)
			return true;

		else
			return false;
	}

	/**
	 * Nombre registrarPuntoRecoleccion
	 * Descripcion Metodo que realiza la logica para el registro de un punto de recoleccion
	 * @param nPuntoRecoleccion
	 * @param lote
	 * @param planMuestreo
	 * @param bytesFotografia
	 * @return String
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarPuntoRecoleccion(Puntorecoleccion nPuntoRecoleccion, Lote lote,
			Estrategiamuestreo planMuestreo, byte[] bytesFotografia) throws Exception {

		if (nPuntoRecoleccion == null)
			throw new Exception("El Punto de recolección es nulo!");

		if (planMuestreo == null)
			throw new Exception("El Plan de Muestreo no puede ser nulo!");

		if (lote == null)
			throw new Exception("El Lote no puede ser nulo!");

		if (nPuntoRecoleccion.getAltitud() == 0)
			throw new Exception("El Valor de la Altitud no puede ser nulo!");

		if (nPuntoRecoleccion.getLatitud() == 0)
			throw new Exception("El Valor de la Latitud no puede ser nulo!");

		if (bytesFotografia == null)
			throw new Exception("La información de la fotografía debe estár cargada!");

		EstrategiaLote et = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		if (et == null)
			throw new Exception("El objeto EstrategiaLote no existe!");

		if (seHanRegistradoTodosLosPuntos(lote, planMuestreo))
			throw new Exception("Ya se han registrado todos los puntos para este plan de muestreo" + "!");

		nPuntoRecoleccion.setEstrategiaLote(et);

		nPuntoRecoleccion.setPuntoid(generadorID.generarID());

		puntoRecoleccionDAO.persist(nPuntoRecoleccion);

		Fotografia fotografia = new Fotografia();

		fotografia.setFotoid(generadorID.generarID());
		fotografia.setFotografia(bytesFotografia);
		fotografia.setPuntorecoleccion(nPuntoRecoleccion);

		fotografiaDAO.persist(fotografia);

		return "El Punto ha sido registrado correctamente!";
	}

	/**
	 * Nombre darCanticadPuntosRecoleccion
	 * Descripcion Metodo que da la cantidad de puntos de recoleccion
	 * @param lote
	 * @param planMuestreo
	 * @return int
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public int darCanticadPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo) throws Exception {

		if (planMuestreo == null)
			throw new Exception("El Plan de Muestreo especificado es nulo!");

		if (lote == null)
			throw new Exception("El Lote especificado es nulo!");

		Estrategiamuestreo planBuscado = estrategiaMuestreoDAO.findById(planMuestreo.getPlanmid());

		if (planBuscado == null)
			throw new Exception("El Plan de Muestreo no está registrado!");

		Lote loteBuscado = loteDAO.findById(lote.getLoteid());

		if (loteBuscado == null)
			throw new Exception("El Lote no está registrado!");

		EstrategiaLote estrategiaLote = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		Long numeroPuntos = estrategiaLote.getNumeropuntos();

		return Integer.parseInt(numeroPuntos + "");
	}

	/**
	 * Nombre eliminarEstrategiaMuestreo
	 * Descripcion Metodo que elimina una estrategia de muestreo
	 * @param plan
	 * @return String
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String eliminarEstrategiaMuestreo(Estrategiamuestreo plan) throws Exception {

		if (plan == null)
			throw new Exception("El Plan de Muestreo es nulo!");

		Estrategiamuestreo buscado = estrategiaMuestreoDAOW.buscarEstrategiaMuestreo(plan.getTerreno(),
				plan.getCodigoest());

		if (buscado == null)
			throw new Exception("El Plan de Muestreo no existe!");

		List<Lote> lotesDelPlan = terrenoDAOW.darLotes(plan.getTerreno());

		for (Lote loteActual : lotesDelPlan) {

			estrategiaLoteDAOW.eliminarEstrategiaLote(plan, loteActual);

		}

		Diagnosticoagronomo diagnostico = diagnosticoAgronomoDAOW.darDiagnosticoDelPlan(plan);

		if (diagnostico != null)
			diagnosticoDAO.delete(diagnostico);

		informeDAO.delete(plan.getInformelaboratorio());

		estrategiaMuestreoDAO.delete(buscado);

		return "Plan de Muestreo Eliminado del Sistema!";
	}

	/**
	 * Nombre darPuntosDeRecoleccion
	 * Descripcion Metodo que da los puntos de recolección de un plan de muestreo y lote
	 * @param planMuestreo
	 * @param lote
	 * @return List<Puntorecoleccion>
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Puntorecoleccion> darPuntosDeRecoleccion(Estrategiamuestreo planMuestreo, Lote lote) throws Exception {

		List<Puntorecoleccion> puntos = new ArrayList<Puntorecoleccion>();

		EstrategiaLote planLote = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		Set<Puntorecoleccion> puntosProxy = planLote.getPuntorecoleccions();

		for (Puntorecoleccion puntoPxrActual : puntosProxy) {

			Puntorecoleccion puntoCompleto = puntoRecoleccionDAO.findById(puntoPxrActual.getPuntoid());

			puntos.add(puntoCompleto);
		}

		return puntos;
	}

	/**
	 * Nombre darPuntosDeRecoleccion
	 * Descripcion Metodo que da los puntos de recolección de un plan de muestreo y lote
	 * @param planMuestreo
	 * @param lote
	 * @return List<Puntorecoleccion>
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Informelaboratorio darInformeLaboratorio(Estrategiamuestreo plan) {

		Informelaboratorio informe = informeDAO.findById(plan.getInformelaboratorio().getInformeid());

		return informe;
	}

	/**
	 * Nombre darFotosPuntosRecoleccion
	 * Descripcion Metodo que da las fotos de puntos de recoleccion
	 * @return List<Fotografia>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Fotografia> darFotosPuntosRecoleccion() {
		return fotografiaDAOW.darTodasFotos();
	}

	/**
	 * Nombre darTodospuntosRecoleccion
	 * Descripcion Metodo que da los putos de recoleccion
	 * @return List<Puntorecoleccion>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Puntorecoleccion> darTodospuntosRecoleccion() {
		// TODO Auto-generated method stub
		return estrategiaMuestreoDAOW.darPuntosRecoleccion();
	}

	/**
	 * Nombre darFotografía
	 * Descripcion Metodo que da la fotos de un punto de recoleccion
	 * @return Fotografia
	 */
	@Override
	@Transactional(readOnly = true)
	public Fotografia darFotografia(BigDecimal id) {
		
		return fotografiaDAOW.darFotografia(id);
	}


	
	

}
