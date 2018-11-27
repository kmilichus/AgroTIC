package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IDiagnosticoAgronomoDAO;
import co.edu.icesi.demo.dao.IEstrategiaMuestreoDAO;
import co.edu.icesi.demo.dao.ITerrenoDAO;
import co.edu.icesi.demo.dao.IUsuarioDAO;
import co.edu.icesi.demo.dao.IZonaAgroecologicaDAO;
import co.edu.icesi.demo.daow.IDiagnosticoAgronomoDAOW;
import co.edu.icesi.demo.daow.IEstrategiaMuestreoDAOW;
import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.servicios.IGeneradorID;

@Scope("singleton")
@Service("diagnosticoAgroLogic")
public class DiagnosticoAgroLogic implements IDiagnosticoAgroLogic {

	@Autowired
	private IDiagnosticoAgronomoDAO diagnosticoAgroDAO;

	@Autowired
	private IEstrategiaMuestreoDAOW estrategiaMuDAOW;

	@Autowired
	private IEstrategiaMuestreoDAO planDAO;

	@Autowired
	private ITerrenoDAO terrenoDAO;

	@Autowired
	private IDiagnosticoAgronomoDAOW diagnosticoAgroDAOW;

	@Autowired
	private IZonaAgroecologicaDAO zonaagroDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Autowired
	private IGeneradorID generadorID;

	private final static int LONGITUD_MAXIMA_DIAGNOSTICO = 100;
	
	/**
	 * Nombre registrarDiagnostico
	 * Descripcion Metodo que registra un diagnostico de un agronomo al sistema
	 * @param diagnosticoAgronomo
	 * @return String
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarDiagnostico(Diagnosticoagronomo diagnosticoAgronomo) throws Exception {

		String resultadoOperacion = "";

		if (diagnosticoAgronomo == null)
			throw new Exception("El diagnostico es nulo!");
		
		if (diagnosticoAgronomo.getZonaagroecologica() == null)
			throw new Exception("El zona agroecologica es nula!");

		Zonaagroecologica zonaagro = zonaagroDAO.findById(diagnosticoAgronomo.getZonaagroecologica().getZonaagroid());

		if (zonaagro == null)
			throw new Exception("La zona agroecologica no existe!");

		if (diagnosticoAgronomo.getDescripciondiag() == null
				|| diagnosticoAgronomo.getDescripciondiag().trim().equals(""))
			throw new Exception("La descripcion no puede ser vacia!");

		if (diagnosticoAgronomo.getUsuario() == null)
			throw new Exception("El diagnostico debe tener un usuario asignado!");
		
		if(diagnosticoAgronomo.getEstrategiamuestreo() == null)
			throw new Exception ("El diagnostico debe tener un plan de muestreo"
					+ " asociado!");
		
		int cantidadCaracteres = diagnosticoAgronomo.getDescripciondiag().length();
		
		if(cantidadCaracteres>LONGITUD_MAXIMA_DIAGNOSTICO)
			throw new Exception("Las observaciones escritas no pueden pasar de los"
					+ " 100 caracteres!" + " (Cantidad Actual: " + cantidadCaracteres + ")");
		
		BigDecimal idPlan = diagnosticoAgronomo.getEstrategiamuestreo().getPlanmid();

		Estrategiamuestreo planMuestreo = planDAO.findById(idPlan);

		String codigoPlan = planMuestreo.getCodigoest();

		Terreno terreno = terrenoDAO.findById(planMuestreo.getTerreno().getTerrid());

		Diagnosticoagronomo buscado = darDiagnosticoDeEstrategia(terreno, codigoPlan);

		if (buscado != null)
			throw new Exception("Ya se ha registrado un Diagnostico "
					+ "para la Estrategia seleccionada!");

		diagnosticoAgronomo.setFechacreaciondiag(new Date());

		diagnosticoAgronomo.setDiagnosticid(generadorID.generarID());

		diagnosticoAgroDAO.persist(diagnosticoAgronomo);

		resultadoOperacion = "El diagnostico ha sido registrado!";
		return resultadoOperacion;
	}

	/**
	 * Nombre darDiagnosticoDeEstrategia
	 * Descripcion Metodo que da  un diagnostico de una estrategia registrado en el sistema 
	 * @param terreno
	 * @param codigoPlanMuestreo
	 * @return Diagnosticoagronomo
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Diagnosticoagronomo darDiagnosticoDeEstrategia(Terreno terreno, String codigoPlanMuestreo) throws Exception {

		Diagnosticoagronomo diagnosticoEncontrado = null;

		if (codigoPlanMuestreo == null || codigoPlanMuestreo.trim().equals(" "))
			throw new Exception("El codigo del plan de muestreo es nulo!");

		Terreno t = terrenoDAO.findById(terreno.getTerrid());

		if (t == null)
			throw new Exception("El terreno no esta registrado!");

		Estrategiamuestreo em = estrategiaMuDAOW.buscarEstrategiaMuestreo(t, codigoPlanMuestreo);

		if (em == null)
			throw new Exception("La estrategia de muestreo no existe!");

		diagnosticoEncontrado = diagnosticoAgroDAOW.consultarDiagnosticoAgronomo(em.getCodigoest());

		return diagnosticoEncontrado;

	}

	/**
	 * Nombre darZonaDiagnostico
	 * Descripcion Metodo que da  la zona agroecologica asociada a un diagnostico
	 * @param diagnostico
	 * @return Zonaagroecologica
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Zonaagroecologica darZonaDiagnostico
	(Diagnosticoagronomo diagnostico) throws Exception{
		
		if(diagnostico== null)
			throw new Exception("El Diagnostico es nulo!");
		
		return diagnosticoAgroDAOW.consultarZonaDiagnostico(diagnostico);
		
	}
	
	/**
	 * Nombre consultarAgronomo
	 * Descripcion Metodo que consulta el agronomo de un diagnostico especifico
	 * @param diagnostico
	 * @return Usuario
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Usuario consultarAgronomo(Diagnosticoagronomo diagnostico) {
		
		return usuarioDAO.findById(diagnostico.getUsuario().getUsuid());
		
	}

}
