package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ILoteDAO;
import co.edu.icesi.demo.dao.ITerrenoDAO;
import co.edu.icesi.demo.daow.ILoteDAOW;
import co.edu.icesi.demo.daow.ITerrenoDAOW;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.servicios.IGeneradorID;

@Scope("singleton")
@Service("terrenoLogic")
public class TerrenoLogic implements ITerrenoLogic {

	@Autowired
	private ITerrenoDAOW terrenoDAOW;

	@Autowired
	private ITerrenoDAO terrenoDAO;

	@Autowired
	private ILoteDAO loteDAO;

	@Autowired
	private ILoteDAOW loteDAOW;

	@Autowired
	private IGeneradorID generadorID;
	
	@Autowired
	private IEstrategiaLogic estrategiaLogic;
	
	private final static int LONGITUD_MAXIMA_NOMBRE_TERRENO= 25;
	private final static int LONGITUD_MAXIMA_NOMBRE_LOTE= 20;

	
	/**
	 * Nombre: buscarTerreno
	 * Descripcion: Entrega un terreno, consultado por su nombre
	 * @param - nombreTerreno : El nombre del terreno que se quiere consultar
	 * @return - Terreno : La instancia del terreno consultado
	 * **/
	
	
	@Override
	@Transactional(readOnly = true)
	public Terreno buscarTerreno(String nombreTerreno) throws Exception {

		if (nombreTerreno == null || nombreTerreno.trim().equals(""))
			throw new Exception("El nombre del terreno no puede ser nulo");

		return terrenoDAOW.buscarTerreno(nombreTerreno);

	}

	
	
	/**
	 * Nombre:  registrarTerreno
	 * Descripcion: Registra un nuevo Terreno al sistema
	 * @param - nuevoTerreno : El Terreno que se va a registrar al sistema
	 * @return - boolean : Mensaje con los resultados de la opeación de registro
	 * **/
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarTerreno(Terreno nuevoTerreno) throws Exception {

		String respuesta = "";
		if (nuevoTerreno == null)
			throw new Exception("El terreno es nulo");
		
		String nombreTerreno = nuevoTerreno.getNombreterr();
		
		Terreno buscado =terrenoDAOW.buscarTerreno(nombreTerreno);

		if(buscado !=null)
			throw new Exception("El Terreno ya existe!");
		
		if (nuevoTerreno.getNombreterr() == null || nuevoTerreno.getNombreterr().trim().equals(""))
			throw new Exception("El nombre del terreno es vacío");
		
		if (nuevoTerreno.getNombreterr().length()>
		LONGITUD_MAXIMA_NOMBRE_TERRENO)
		throw new Exception("El Nombre no puede tener "
				+ "más de 10 caracteres!");
	

		nuevoTerreno.setTerrid(generadorID.generarID());
		
		terrenoDAO.persist(nuevoTerreno);
		respuesta = "Se registro un nuevo terreno";
		return respuesta;

	}
	
	/**
	 * Nombre: eliminarTerreno
	 * Descripcion: Elimina un terreno del sistema
	 * @param - terrenoABorrar - Instancia del terreno que se quiere eliminar
	 * @return - String : Mensaje con el resultado de la operación
	 * **/

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String eliminarTerreno(Terreno terrenoABorrar) throws Exception {

		String respuesta = "";

		if (terrenoABorrar == null)
			throw new Exception("El terreno es null");

		List<Estrategiamuestreo> planes = 
				estrategiaLogic.darEstrategiasMuestro(terrenoABorrar.getNombreterr());
		
		for (Estrategiamuestreo estrategiamuestreo : planes) {	
			
			estrategiaLogic.eliminarEstrategiaMuestreo(estrategiamuestreo);
		}
		
		List<Lote> lotes = terrenoDAOW.darLotes(terrenoABorrar);
		
		for (Lote lote : lotes) {
			loteDAO.delete(lote);
		}
		
		Terreno terrenoProxy = terrenoDAO.findById(terrenoABorrar.getTerrid());
		
		terrenoDAO.delete(terrenoProxy);
		
		respuesta = "Se eliminó el terreno del sistema!";

		return respuesta;

	}

	/**
	 * Nombre: actualizarNombreTerreno
	 * Descripcion:  Reemplaza el nombre del terreno, por uno nuevo dado
	 * @param - terreno - Instancia del terreno, cuyo nombre se quiere actualizar
	 * @param - nuevoNombre - El nuevo nombre que el terreno va a tener
	 * @return - String : mensaje con el resultado de la operación
	 * **/
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarNombreTerreno(Terreno terreno, String nuevoNombre) throws Exception {

		String respuesta = "";

		if (terreno == null)
			throw new Exception("El terreno es nulo");

		if (nuevoNombre == null || nuevoNombre.trim().equals(""))
			throw new Exception("El nombre nuevo es vacio");
		
		if(nuevoNombre.length()>LONGITUD_MAXIMA_NOMBRE_TERRENO)
		throw new Exception("El Nuevo nombre no puede tener más de 10 caracteres!");
		

		terreno.setNombreterr(nuevoNombre);
		terrenoDAO.merge(terreno);
		respuesta = "Se actualizo el nombre del terreno";

		return respuesta;
	}

	/**
	 * Nombre: registrarLote
	 * Descripcion: Registra un nuevo lote en el sistema
	 * @param - nuevoLote - Instancia del lote que se va a registrar
	 * @return - String - Mensaje con el resultado de la operación
	 * **/
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarLote(Lote nuevoLote) throws Exception {

		String respuesta = "";
		if (nuevoLote == null)
			throw new Exception("El terreno es nulo");

		if (nuevoLote.getNombrelote() == null || nuevoLote.getNombrelote().trim().equals(""))
			throw new Exception("El nombre del lote es vacío");

		if (nuevoLote.getDescripcionlote() == null || nuevoLote.getDescripcionlote().trim().equals(""))
			throw new Exception("La descripción del lote es vacío");

		if (nuevoLote.getTerreno() == null)
			throw new Exception("El terreno al que corresponde el lote es nulo");

		Terreno t = terrenoDAO.findById(nuevoLote.getTerreno().getTerrid());

		if (t == null)
			throw new Exception("El terreno no esta registrado");


		if(buscarLote(t, nuevoLote.getNombrelote())!=null)
			throw new Exception("El terreno ya tiene un Lote con el mismo nombre!");
		
		if(nuevoLote.getNombrelote().length()> LONGITUD_MAXIMA_NOMBRE_LOTE)
			throw new Exception("El nombre del Lote no puede contener más de 25 "
					+ "caracteres!");

			
		nuevoLote.setLoteid(generadorID.generarID());

		loteDAO.persist(nuevoLote);
		respuesta = "Se registro un nuevo lote";
		return respuesta;
	}

	/**
	 * Nombre: buscarLote
	 * Descripcion: Dado un Terreno, se busca un lote de dicho terreno con un nombre dado
	 * @param - terreno : Terreno al que el lote pertenece
	 * @param - nombreLote : Nombre del Lote que se quiere buscar
	 * @return - Lote : Intancia del lote encontrado
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Lote buscarLote(Terreno terreno, String nombreLote) throws Exception {

		if (terreno == null)
			throw new Exception("El Terreno no puede ser nulo!");

		Terreno t = terrenoDAO.findById(terreno.getTerrid());

		if (t == null)
			throw new Exception("El Terreno especificado no está registrado!");

		if (nombreLote == null || nombreLote.trim().equals(""))
			throw new Exception("El nombre del lote no puede ser vacío");

		return loteDAOW.buscarLote(t, nombreLote);
	}
	
	/**
	 * Nombre: actualizarNombreLote
	 * Descripcion: Reemplaza el nombre de un lote, por un nombre nuevo dado
	 * @param - lote : Instancia del lote, cuyo nombre se quiere actualizar
	 * @param - nuevoNombre: El nuevo nombre que va a tener el lote
	 * @return - String : Mensaje con el resultado de la operación de actualización
	 * **/

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String actualizarNombreLote(Lote lote, String nuevoNombre) throws Exception {

		String respuesta = "";

		if (lote == null)
			throw new Exception("El lote es nulo");

		if (nuevoNombre == null || nuevoNombre.trim().equals(""))
			throw new Exception("El nombre nuevo es vacio");

		lote.setNombrelote(nuevoNombre);
		loteDAO.merge(lote);
		respuesta = "Se actualizo el nombre del lote";

		return respuesta;
	}
	
	/**
	 * Nombre: darLotes
	 * Descripcion: Entrega los lotes que conforman un Terreno dado
	 * @param - nombreTerreno - Nombre del Terreno, cuyos lotes se quieren consultar
	 * @return - List<Lote> - Lista de los lotes encontrados
	 * **/

	@Override
	@Transactional(readOnly = true)
	public List<Lote> darLotes(String nombreTerreno) throws Exception {
		
		if (nombreTerreno == null || nombreTerreno.trim().equals(""))
			throw new Exception("El Nombre del Terreno no puede estár vacío!");
		
		Terreno terreno = terrenoDAOW.buscarTerreno(nombreTerreno);
		
		return terrenoDAOW.darLotes(terreno);
		
	}
	
	/**
	 * Nombre: darTodosTerrenos
	 * Descripcion: Entrega todos los terrenos registrados en el sistema
	 * @return - List<Terreno> : Lista de todos los Terrenos registrados en el sistema
	 * **/


	@Override
	@Transactional(readOnly = true)
	public List<Terreno> darTodosLosTerrenos() {

		return terrenoDAOW.darTodosLosTerrenos();
	}

	/**
	 * Nombre: eliminarLote
	 * Descripcion: Elimina un lote del sistema
	 * @param - lote - Instancia del lote que se va a eliminar
	 * @return - String : Mensaje con los resultados de la operación de eliminado
	 * **/

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String eliminarLote(Lote lote) throws Exception {
		String respuesta = "";

		if (lote == null)
			throw new Exception("El Lote es nulo!");

		loteDAO.delete(lote);
		respuesta = "Se borro el Lote del Sistema!";

		return respuesta;
	}
	
	/**
	 * Nombre: actualizarDescripcionLote
	 * Descripcion: Actualiza la descripción de un Lote dado
	 * @param - lote : Lote, cuya descripción se quiere cambiar
	 * @param - nuevaDescripcion : La nueva descripción que va a tener el Lote
	 * **/


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void actualizarDescripcionLote(Lote lote, String nuevaDescripcion)
			throws Exception {

		if (lote == null)
			throw new Exception("El lote es nulo!");

		if (nuevaDescripcion == null || nuevaDescripcion.trim().equals(""))
			throw new Exception("La Descripción nueva es vacía!");

		lote.setDescripcionlote(nuevaDescripcion);
		loteDAO.merge(lote);

	}

}
