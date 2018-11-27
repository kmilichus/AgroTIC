package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ICaracterizacionLoteDAO;
import co.edu.icesi.demo.dao.IElementoDAO;
import co.edu.icesi.demo.dao.IEstrategiaMuestreoDAO;
import co.edu.icesi.demo.dao.IFaseAplicadaDAO;
import co.edu.icesi.demo.dao.IInformeLaboratorioDAO;
import co.edu.icesi.demo.dao.ILoteDAO;
import co.edu.icesi.demo.dao.IMedicionElementoDAO;
import co.edu.icesi.demo.dao.IMedicionMetricaDAO;
import co.edu.icesi.demo.dao.IMetodologiaDAO;
import co.edu.icesi.demo.dao.IMetricaDAO;
import co.edu.icesi.demo.daow.IElementoDAOW;
import co.edu.icesi.demo.daow.IEstrategiaLoteDAOW;
import co.edu.icesi.demo.daow.IFaseDAOW;
import co.edu.icesi.demo.daow.IMedicionesDAOW;
import co.edu.icesi.demo.daow.IMetodologiaDAOW;
import co.edu.icesi.demo.daow.IMetricaDAOW;
import co.edu.icesi.demo.daow.IRangoElementoDAOW;
import co.edu.icesi.demo.daow.IRangoMetricaDAOW;
import co.edu.icesi.demo.daow.ITipoTexturaDAOW;
import co.edu.icesi.demo.mapeo.MapeoMedicionLAB;
import co.edu.icesi.demo.modelo.Caracterizacionlote;
import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.EstrategiaLote;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Faseaplicada;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Medicionelemento;
import co.edu.icesi.demo.modelo.Medicionmetricas;
import co.edu.icesi.demo.modelo.Metodologia;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rangoelementos;
import co.edu.icesi.demo.modelo.Rangometricas;
import co.edu.icesi.demo.servicios.IGeneradorID;

@Scope("singleton")
@Service("laboratorioLogic")
public class LaboratorioLogic implements ILaboratorioLogic {

	private final static int LONGITUD_MAXIMA_NOMBRE_HACIENDA = 25;
	private final static int LONGITUD_MAXIMA_NOMBRE_lAB = 20;
	private final static String NOMBRE_FASE_SOLUBLE = "Soluble";

	@Autowired
	private IElementoDAOW elementoDAOW;

	@Autowired
	private IFaseDAOW faseDAOW;

	@Autowired
	private IMetodologiaDAOW metodologiaDAOW;

	@Autowired
	private IMetricaDAOW metricaDAOW;

	@Autowired
	private ITipoTexturaDAOW texturaDAOW;

	@Autowired
	private IEstrategiaMuestreoDAO emDAO;

	@Autowired
	private ILoteDAO loteDAO;

	@Autowired
	private IRangoMetricaDAOW rangoMetricaDAOW;

	@Autowired
	private IMetricaDAO metricaDAO;

	@Autowired
	private IRangoElementoDAOW rangoElementoDAOW;

	@Autowired
	private IElementoDAO elementoDAO;

	@Autowired
	private IMetodologiaDAO metoDAO;

	@Autowired
	private IEstrategiaLoteDAOW estrategiaLoteDAOW;

	@Autowired
	private IGeneradorID generador;

	@Autowired
	private IMedicionElementoDAO meDAO;

	@Autowired
	private IMedicionMetricaDAO mmDAO;

	@Autowired
	private IInformeLaboratorioDAO informeDAO;

	@Autowired
	private IFaseAplicadaDAO faseDAO;

	@Autowired
	private ICaracterizacionLoteDAO caracterizacionLoteDAO;

	@Autowired
	private IMedicionesDAOW medicionesDAOW;

	/**
	 * Nombre registrarValorMetrica
	 * Descripcion Metodo que realiza toda la lógica para el registro una metrica al sistema
	 * @Param planMuestreo, lote, codigoMetrica, codigoMetodologia, magnitudValor
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#registrarValorMetrica(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote, java.math.BigDecimal, java.math.BigDecimal, double)
	 */
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarValorMetrica(Estrategiamuestreo planMuestreo, Lote lote, BigDecimal codigoMetrica,
			BigDecimal codigoMetodologia, double magnitudValor) throws Exception {

		String respuesta = "";

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo!");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe!");

		if (lote == null)
			throw new Exception("El lote es nulo!");

		Lote l = loteDAO.findById(lote.getLoteid());
		if (l == null)
			throw new Exception("El lote no existe en el sistema!");

		EstrategiaLote el = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);
		if (el == null)
			throw new Exception("La estrategia para el lote dado no existe!");

		if (codigoMetodologia == null)
			throw new Exception("El codigo de la metodologia es nulo!");

		Metodologia mt = metoDAO.findById(codigoMetodologia);
		if (mt == null)
			throw new Exception("La metodologia no existe!");

		if (codigoMetrica == null)
			throw new Exception("El codigo de la metrica es nulo!");

		Metrica metrica = metricaDAO.findById(codigoMetrica);
		if (metrica == null)
			throw new Exception("La metrica no existe en el sistema!");

		Caracterizacionlote cl = caracterizacionLoteDAO.findById(el.getCaracterizacionlote().getCarid());

		if (cl.getFaseaplicada() == null)
			throw new Exception("Debe ingresar la Fase Aplicada primero!");

		Faseaplicada fase = faseDAO.findById(cl.getFaseaplicada().getFaseid());

		String nombreTex = fase.getNombrefase();

		Rangometricas rm = rangoMetricaDAOW.darClaMetrica(metrica, magnitudValor);

		if (rm == null) {
			String alertaFueraRango = rangoMetricaDAOW.darValoresExtremos(metrica);

			throw new Exception("El valor para " + metrica.getNombremet() + " no se encuentra en el rango "
					+ alertaFueraRango);
		}

		if (metrica.getNombremet().equalsIgnoreCase("PH")) {
			if ((magnitudValor < 7.2 && nombreTex.equalsIgnoreCase("Soluble"))
					|| (magnitudValor >= 7.2 && cl.getFaseaplicada().equals("Intercambiable")))
				throw new Exception("Si el valor del PH es menor a 7.2, la fase deberia ser"
						+ " intercambiable!");

			Set<Medicionmetricas> lista = cl.getMedicionmetricases();
			for (Medicionmetricas i : lista) {
				Rangometricas prueba = i.getRangometricas();
				String nombre = prueba.getMetrica().getNombremet();
				if (nombre.equals("Conductividad Electrica")) {
					if (prueba.getNombrerango().equals("CE Bajo") && rm.getNombrerango().equals("Acido"))
						throw new Exception("No deberia registrarse esta medicion de PH porque no concuerda con el "
								+ "valor de la Conductividad Electrica!");
				}
			}
		}
		if (metrica.getNombremet().equalsIgnoreCase("Conductividad Electrica")) {

			Set<Medicionmetricas> lista = cl.getMedicionmetricases();
			for (Medicionmetricas i : lista) {
				Rangometricas prueba = i.getRangometricas();
				String nombre = prueba.getMetrica().getNombremet();
				if (nombre.equals("PH")) {
					if (prueba.getNombrerango().equals("Acido") && rm.getNombrerango().equals("CE Bajo"))
						throw new Exception("No deberia registrarse esta medicion de CE porque no concuerda con el PH");
				}
			}
		}

		Medicionmetricas medicionBuscada = medicionesDAOW.darMedidaMetrica(planMuestreo, lote, metrica);

		if (medicionBuscada != null) {

			medicionBuscada.setMagnitud(magnitudValor);
			medicionBuscada.setMetodologia(mt);
			mmDAO.merge(medicionBuscada);

			respuesta = "La medida de la metrica ha sido actualizada correctamente!";

		} else {
			BigDecimal idNew2 = generador.generarID();
			Medicionmetricas mm = new Medicionmetricas(idNew2, rm, magnitudValor);

			mm.setMetodologia(mt);
			mm.setCaracterizacionlote(cl);
			mmDAO.persist(mm);
			respuesta = "Se registro la " + "medicion de la metrica correctamente!";

		}

		return respuesta;

	}

	/**
	 * Nombre darTodosLosElementos
	 * Descripcion Metodo que da todos los elementos registrados en el sistema
	 * @Return List<Elemento>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darTodosLosElementos()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Elemento> darTodosLosElementos() {

		return elementoDAOW.darTodosLosElementos();
	}
	
	/**
	 * Nombre darTodasLasFases
	 * Descripcion Metodo que retorna todas las fases registradas en el sistema
	 * @Return List<Faseaplicada>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darTodasLasFases()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Faseaplicada> darTodasLasFases() {

		return faseDAOW.darTodasLasFases();
	}

	/**
	 * Nombre darTodasLasMetodologias
	 * Descripcion Metodo que retorna todas las metodologias del sistema
	 * @Return List<Metodologia>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darTodasLasMetodologias()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Metodologia> darTodasLasMetodologias() {

		return metodologiaDAOW.darTodasLasMetodologias();
	}

	/**
	 * Nombre darTodasLasMetricas
	 * Descripcion Metodo que da todas las metricas registradas en el sistema
	 * @Return List<Metrica>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darTodasLasMetricas()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Metrica> darTodasLasMetricas() {

		return metricaDAOW.darTodasLasMetricas();
	}

	/**
	 * Nombre darTodosLosTiposTextura
	 * Descripcion Metodo que da todas los tipos de textura en el sistema
	 * @Return List<Clasificaciontextura>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darTodasLasMetricas()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Clasificaciontextura> darTodosLosTiposTextura() {

		return texturaDAOW.darTodosLosTiposTextura();
	}

	/**
	 * Nombre registrarMedicionElemento
	 * Descripcion Metodo que realiza toda la lógica para el registro una medicion de un elemento al sistema
	 * @Param planMuestreo, lote, codigoElemento, codigoMetodologia, valorPPM porcentajeSolubilidad
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#registrarMedicionElemento(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote, java.math.BigDecimal, java.math.BigDecimal, double, double)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarMedicionElemento(Estrategiamuestreo planMuestreo, Lote lote, BigDecimal codigoElemento,
			BigDecimal codigoMetodologia, double valorPPM, double porcentajeSolubilidad) throws Exception {

		String respuesta = "";

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo!");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe!");

		if (lote == null)
			throw new Exception("El lote es nulo!");

		Lote l = loteDAO.findById(lote.getLoteid());
		if (l == null)
			throw new Exception("El lote no existe!");

		if (codigoElemento == null)
			throw new Exception("El codigo del elemento es nulo!");

		Elemento elemento = elementoDAO.findById(codigoElemento);
		if (elemento == null)
			throw new Exception("El elemento no existe!");

		if (codigoMetodologia == null)
			throw new Exception("El codigo de la metodologia es nulo!");

		Metodologia mt = metoDAO.findById(codigoMetodologia);
		if (mt == null)
			throw new Exception("La metodologia no existe!");

		EstrategiaLote el = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);
		if (el == null)
			throw new Exception("El Objeto estrategiaLote, para el lote, dado no existe!");

		Caracterizacionlote cl = el.getCaracterizacionlote();

		Rangoelementos re = rangoElementoDAOW.darClaElemento(elemento, valorPPM);

		if (re == null) {
			String alertaFueraRango = rangoElementoDAOW.darValoresExtremosPPM(elemento);

			throw new Exception("El PPM para " + "" + elemento.getNombreelem() + " no se encuentra" + " en el rango "
					+ alertaFueraRango);
		}

		if (cl.getFaseaplicada() == null)
			throw new Exception("Debe registrar la fase Aplicada, primero!");

		Medicionelemento medida = medicionesDAOW.darMedidaElementos(planMuestreo, lote, elemento);

		Medicionelemento me = new Medicionelemento();
		me.setMetodologia(mt);
		me.setCaracterizacionlote(cl);
		me.setRangoelementos(re);
		me.setPpm(valorPPM);

		if (cl.getFaseaplicada().getNombrefase().equals(NOMBRE_FASE_SOLUBLE)) {

			if (porcentajeSolubilidad > 1 || porcentajeSolubilidad < 0)
				throw new Exception("El % porcentaje de solubilidad " + "no puede estar por fuera del Rango [0,1]");

			me.setPorcentajesolubilidad(porcentajeSolubilidad);
		}

		if (medida != null) {

			medida.setMetodologia(mt);
			medida.setRangoelementos(re);
			medida.setPpm(valorPPM);
			medida.setPorcentajesolubilidad(porcentajeSolubilidad);

			meDAO.merge(medida);
			respuesta = "Se actualizo el valor del Elemento!";
		} else {
			BigDecimal idNew2 = generador.generarID();
			me.setMeleid(idNew2);

			meDAO.persist(me);
			respuesta = "Se registro la medicion del elemento correctamente!";
		}

		return respuesta;
	}

	
	/**
	 * Nombre actualizarClasificacionTextura
	 * Descripcion Metodo que realiza toda la lógica para la actualizacion de la clasificacion de textura
	 * @Param planMuestreo, lote, tipoTextura
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#actualizarClasificacionTextura(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarClasificacionTextura(Estrategiamuestreo planMuestreo, Lote lote, String tipoTextura)
			throws Exception {
		String respuesta = "";

		if (tipoTextura == null || tipoTextura.equals(" "))
			throw new Exception("La clasificacion textural es nula");

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe");

		if (lote == null)
			throw new Exception("El lote es nulo");

		Lote lot = loteDAO.findById(lote.getLoteid());
		if (lot == null)
			throw new Exception("El lote no existe en el sistema!");

		EstrategiaLote eslote = estrategiaLoteDAOW.darEstrategiaLote(em, lot);
		if (eslote == null)
			throw new Exception("La estrategia para ese lote no existe");

		Caracterizacionlote car = eslote.getCaracterizacionlote();
		Clasificaciontextura tipoTx = faseDAOW.darTipoTextura(tipoTextura);

		car.setClasificaciontextura(tipoTx);

		caracterizacionLoteDAO.merge(car);

		return respuesta;
	}

	/**
	 * Nombre actualizarFaseAplicada
	 * Descripcion Metodo que realiza toda la lógica para la actualizacion del nombre de la Fase Aplicada
	 * @Param planMuestreo, lote, nombreFaseAplicada
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#actualizarFaseAplicada(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarFaseAplicada(Estrategiamuestreo planMuestreo, Lote lote, String nombreFaseAplicada)
			throws Exception {
		String respuesta = "";

		if (nombreFaseAplicada == null || nombreFaseAplicada.trim().equals(""))
			throw new Exception("La fase aplicada es nula");

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe");

		if (lote == null)
			throw new Exception("El lote es nulo");

		Lote lot = loteDAO.findById(lote.getLoteid());
		if (lot == null)
			throw new Exception("El lote no existe");

		EstrategiaLote eslote = estrategiaLoteDAOW.darEstrategiaLote(em, lot);
		if (eslote == null)
			throw new Exception("La estrategia para ese lote no existe");

		Caracterizacionlote car = eslote.getCaracterizacionlote();
		Faseaplicada faseAplicada = faseDAOW.darFase(nombreFaseAplicada);

		car.setFaseaplicada(faseAplicada);

		caracterizacionLoteDAO.merge(car);

		respuesta = "Se actualizo la fase aplicada!";

		return respuesta;
	}

	/**
	 * Nombre darAnalisisLaboratorio
	 * Descripcion Metodo que realiza la logica para la obtencion de la informacion del analisis de un laboratorio 
	 * @Param planMuestreo, lote
	 * @Return List<MapeoMedicionLAB>
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#darAnalisisLaboratorio(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MapeoMedicionLAB> darAnalisisLaboratorio(Estrategiamuestreo planMuestreo, Lote lote) throws Exception {

		List<MapeoMedicionLAB> mapeos = new LinkedList<MapeoMedicionLAB>();

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe");

		if (lote == null)
			throw new Exception("El lote es nulo");

		Lote lot = loteDAO.findById(lote.getLoteid());
		if (lot == null)
			throw new Exception("El lote no existe");

		EstrategiaLote eslote = estrategiaLoteDAOW.darEstrategiaLote(em, lot);

		Caracterizacionlote cl = eslote.getCaracterizacionlote();

		Faseaplicada faseP = cl.getFaseaplicada();
		Clasificaciontextura clasiT = cl.getClasificaciontextura();
		Set<Medicionelemento> medicionesElementos = cl.getMedicionelementos();
		Set<Medicionmetricas> medicionesMetrica = cl.getMedicionmetricases();

		if (faseP != null) {
			MapeoMedicionLAB mapeoMedic = new MapeoMedicionLAB("Fase Aplicada", faseP.getNombrefase(), " - - -");
			mapeos.add(mapeoMedic);
		}
		if (clasiT != null) {
			MapeoMedicionLAB mapeoMedic = new MapeoMedicionLAB("Tipo Textura", clasiT.getNombrecla(), "- - - ");
			mapeos.add(mapeoMedic);
		}

		for (Medicionmetricas medicionMetricaActual : medicionesMetrica) {

			Metodologia metodologia = medicionMetricaActual.getMetodologia();
			Rangometricas rango = medicionMetricaActual.getRangometricas();

			Metrica metrica = rango.getMetrica();

			MapeoMedicionLAB mapeoMagnitud = new MapeoMedicionLAB("Medida " + metrica.getNombremet(),
					medicionMetricaActual.getMagnitud() + "", metodologia.getNombremet());

			MapeoMedicionLAB mapeoRango = new MapeoMedicionLAB("Clasificacion" + metrica.getNombremet(),
					rango.getNombrerango(), "- - -");

			mapeos.add(mapeoMagnitud);
			mapeos.add(mapeoRango);

		}

		for (Medicionelemento medicionEleActual : medicionesElementos) {

			Metodologia metodologia = medicionEleActual.getMetodologia();
			Rangoelementos rango = medicionEleActual.getRangoelementos();
			Elemento elemento = rango.getElemento();
			
			MapeoMedicionLAB mapeoPPM = new MapeoMedicionLAB("PPM " + elemento.getNombreelem(),
					medicionEleActual.getPpm() + "", metodologia.getNombremet());
			
			double porcentaje = medicionEleActual.getPorcentajesolubilidad()*100;
			
			MapeoMedicionLAB mapeoSolubilidad = new MapeoMedicionLAB(
					"%Solubilidad " + elemento.getNombreelem(),
					porcentaje +"%",
					medicionEleActual.getMetodologia().getNombremet());
			
			MapeoMedicionLAB mapeoRango = new MapeoMedicionLAB(
					"Concentracion PPM" + elemento.getNombreelem(), rango.getNombrerango(),
					"- - -");
			
			mapeos.add(mapeoPPM);
			mapeos.add(mapeoSolubilidad);
			mapeos.add(mapeoRango);

		}

		return mapeos;

	}

	/**
	 * Nombre actualizarNombreLaboratorio
	 * Descripcion Metodo que actualiza el nombre de un laboratorio
	 * @Param planMuestreo, nombreLAB
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#actualizarNombreLaboratorio(co.edu.icesi.demo.modelo.Estrategiamuestreo, java.lang.String)
	 */
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarNombreLaboratorio(Estrategiamuestreo planMuestreo, String nombreLAB) throws Exception {
		String respuesta = "";

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo");

		if (nombreLAB == null || nombreLAB.equals(" "))
			throw new Exception("El nombre de la hacienda nuevo es nulo");

		if (nombreLAB.length() > LONGITUD_MAXIMA_NOMBRE_lAB)
			throw new Exception("El Nombre no puede tener " + "mas de 20 caracteres!");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe");

		if (em.getInformelaboratorio() == null)
			throw new Exception("La estrategia no tiene un informe laboratorio");

		em.getInformelaboratorio().setNombrelaboratorio(nombreLAB);
		informeDAO.merge(em.getInformelaboratorio());
		respuesta = "Se actualizo el nombre de la hacienda";

		return respuesta;
	}

	/**
	 * Nombre actualizarNombreHacienda
	 * Descripcion Metodo que actualiza el nombre de una hacienda
	 * @Param planMuestreo, nombreHacienda
	 * @Return String respuesta
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#actualizarNombreHacienda(co.edu.icesi.demo.modelo.Estrategiamuestreo, java.lang.String)
	 */
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarNombreHacienda(Estrategiamuestreo planMuestreo, String nombreHacienda) throws Exception {

		String respuesta = "";

		if (planMuestreo == null)
			throw new Exception("El plan de muestreo es nulo");

		if (nombreHacienda == null || nombreHacienda.equals(" "))
			throw new Exception("El nombre de la hacienda nuevo es nulo");

		if (nombreHacienda.length() > LONGITUD_MAXIMA_NOMBRE_HACIENDA)
			throw new Exception("El Nombre no puede tener " + "mas de 25 caracteres!");

		Estrategiamuestreo em = emDAO.findById(planMuestreo.getPlanmid());
		if (em == null)
			throw new Exception("El plan de muestreo no existe");

		if (em.getInformelaboratorio() == null)
			throw new Exception("La estrategia no tiene un informe laboratorio");

		em.getInformelaboratorio().setNombrehacienda(nombreHacienda);
		informeDAO.merge(em.getInformelaboratorio());
		respuesta = "Se actualizo el nombre de la hacienda";

		return respuesta;
	}

	/*
	 * Nombre usoFaseSoluble
	 * Descripcion Metodo que verifica si se usa fase soluble
	 * @Param planMuestreo, lote
	 * @Return boolean 
	 * @see co.edu.icesi.demo.logica.ILaboratorioLogic#usoFaseSoluble(co.edu.icesi.demo.modelo.Estrategiamuestreo, co.edu.icesi.demo.modelo.Lote)
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean usoFaseSoluble(Estrategiamuestreo planMuestreo, Lote lote) {

		EstrategiaLote eL = estrategiaLoteDAOW.darEstrategiaLote(planMuestreo, lote);

		Caracterizacionlote cL = caracterizacionLoteDAO.findById(eL.getCaracterizacionlote().getCarid());

		Faseaplicada fase = faseDAO.findById(cL.getFaseaplicada().getFaseid());

		if (fase != null && fase.getNombrefase().equalsIgnoreCase(NOMBRE_FASE_SOLUBLE))
			return true;

		else
			return false;

	}

}
