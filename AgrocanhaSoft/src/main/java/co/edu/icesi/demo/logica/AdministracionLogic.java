package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IDiagnosticoAgronomoDAO;
import co.edu.icesi.demo.dao.IRolDAO;
import co.edu.icesi.demo.dao.IUsuarioDAO;
import co.edu.icesi.demo.daow.IUsuarioDAOW;
import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.servicios.IAdministradorCadenas;
import co.edu.icesi.demo.servicios.IGeneradorID;

@Scope("singleton")
@Service("administracionLogic")
public class AdministracionLogic implements IAdministracionLogic {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Autowired
	private IUsuarioDAOW usuarioDAOW;

	@Autowired
	private IRolDAO rolDAO;

	@Autowired
	private IAdministradorCadenas revisorCadenas;
	
	@Autowired
	private IGeneradorID generadorID;
	
	@Autowired
	private IDiagnosticoAgronomoDAO diagnosticoDAO;

	@Autowired
	private IAccesoLogic accesoLogic;
	
	private final static int LONGITUD_MAXIMA_NOMBRE_USUARIO = 15;
	private final static BigDecimal ID_USUARIO_ELIMINADO= new BigDecimal(2);
	
	private final static String CEDULA_USUARIO_ELIMINADO = "00000000";
	
	/**
	 * Nombre: registrarUsuario
	 * Descripcion: Registrar un usuario en el sistema, validando primero que se
	 * 				cumpla la integridad de los datos
	 * @param - nuevoUsuario : El nuevo usuario que se va a registrar en la base de datos
	 * @return - String : Un mensaje con el resultado final de la operación de registro
	 * **/
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String registrarUsuario(Usuario nuevoUsuario) throws Exception {

		String resultadoOperacion = "";

		if (nuevoUsuario == null)
			throw new Exception("El Usuario es nulo!");

		if (nuevoUsuario.getCedulausu() == null || nuevoUsuario.getCedulausu().trim().equals(""))
			throw new Exception("La Cedula del usuario no puede ser vacía!");

		if (!revisorCadenas.soloContieneNumeros(nuevoUsuario.getCedulausu()))
			throw new Exception("La Cedula solo puede contener números!");

		Usuario usuarioBuscado = usuarioDAOW.buscarUsuarioPorCedula(nuevoUsuario.getCedulausu());

		if (usuarioBuscado != null)
			throw new Exception("El Usuario ya ha sido registrado anteriormente!");

		if (nuevoUsuario.getNombreusu() == null || nuevoUsuario.getNombreusu().trim().equals(""))
			throw new Exception("El Nombre del Usuario no puede ser vacío!");
		
		
		if(nuevoUsuario.getNombreusu().length()>LONGITUD_MAXIMA_NOMBRE_USUARIO)
			throw new Exception("El Nombre del Usuario no puede tener más de 15 carac"
					+ "teres!");

		
		if (!revisorCadenas.soloContieneLetrasYEspacios(nuevoUsuario.getNombreusu()))
			throw new Exception("El Nombre solo puede contener letras y espacios!");

		if (nuevoUsuario.getPasswordusu() == null || nuevoUsuario.getPasswordusu().trim().equals(""))
			throw new Exception("El Password del Usuario no puede ser vacío!");

		if (!revisorCadenas.longitudPasswordValida(nuevoUsuario.getPasswordusu()))
			throw new Exception("La constraseña debe tener entre 6 y 10 caracteres!");

		nuevoUsuario.setUsuid(generadorID.generarID());

		if (nuevoUsuario.getRol() == null)
			throw new Exception("El Rol del usuario no puede ser nulo!");

		Rol rol = rolDAO.findById(nuevoUsuario.getRol().getRolid());

		if (rol == null)
			throw new Exception("El Rol determinado para el Usuario es invalido!");

		usuarioDAO.persist(nuevoUsuario);

		resultadoOperacion = "Usuario Registrado Exitosamente!";

		return resultadoOperacion;
	}

	/**
	 * Nombre: eliminarUsuario
	 * Descripcion: Elimina los usuarios que se hayan registrado anteriormente en el sistema.
	 * 				El usuario con cedula "00000000" no puede ser eliminado
	 * @param - cedulaUsuario : La cedula del usuaro que se va a eliminar del sistema
	 * @return - String : Mensaje con el resultado de la operación de eliminación
	 * **/
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String eliminarUsuario(String cedulaUsuario) throws Exception {

		String resultadoOperacion = "";

		if (cedulaUsuario == null || cedulaUsuario.trim().equals(""))
			throw new Exception("La Cedula del usuario no puede ser vacía!");

		if (!revisorCadenas.soloContieneNumeros(cedulaUsuario))
			throw new Exception("La Cedula solo puede contener números!");

		Usuario usuarioBuscado = usuarioDAOW.buscarUsuarioPorCedula(cedulaUsuario);

		if (usuarioBuscado == null)
			throw new Exception("El Usuario No existe en el Sistema!");

		if(cedulaUsuario.equals(CEDULA_USUARIO_ELIMINADO))
			throw new Exception("Está prohibido eliminar a este usuario!");
		
		if(accesoLogic.darUsuarioLogueado().getCedulausu().equals(cedulaUsuario))
			throw new Exception("No se puede eliminar a usted mismo!");
		
		Set<Diagnosticoagronomo> diagnosticos = usuarioBuscado.getDiagnosticoagronomos();
		Usuario entidadEliminada = usuarioDAO.findById(ID_USUARIO_ELIMINADO);
		
		for (Diagnosticoagronomo dgActual : diagnosticos) {
			dgActual.setUsuario(entidadEliminada);
			diagnosticoDAO.merge(dgActual);
		}
			
		usuarioDAO.delete(usuarioBuscado);
		
		resultadoOperacion = "El Usuario Ha Sido Eliminado Del Sistema!";

		return resultadoOperacion;
	}

	/**
	 * Nombre: cambiarNombreUsuario
	 * Descripcion: Modificar el username de algún usuario registrado.
	 * @param - nuevoNombre: El nuevo username del usuario que se va a modificar
	 * @param - usuarioAModificar : El usuario, cuyo username se va a modificar
	 * @return - String : Un mensaje con el resultado de la operación del update
	 * **/
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String cambiarNombreUsuario(String nuevoNombre, Usuario usuarioAModificar) throws Exception {

		String resultadoOperacion = "";

		if (nuevoNombre == null || nuevoNombre.trim().equals(""))
			throw new Exception("El Nombre del Usuario no puede ser vacío!");

		if (!revisorCadenas.soloContieneLetrasYEspacios(nuevoNombre))
			throw new Exception("El Nombre solo puede contener letras y espacios!");

		if (usuarioAModificar == null)
			throw new Exception("El Usuario es nulo!");

		BigDecimal cero = new BigDecimal(0);
		
		if (usuarioAModificar.getUsuid().compareTo(cero) == 0)
			throw new Exception("El Id del Usuario no puede ser nulo!");

		Usuario usuarioBuscado = usuarioDAOW.buscarUsuarioPorCedula(usuarioAModificar.getCedulausu());

		if (usuarioBuscado == null)
			throw new Exception("El Usuario no está registrado!");
		
		if(nuevoNombre.length()>LONGITUD_MAXIMA_NOMBRE_USUARIO)
			throw new Exception("El nuevo nombre no puede tener más de 15 caracteres!");
			
		usuarioAModificar.setNombreusu(nuevoNombre);
		
		usuarioDAO.merge(usuarioAModificar);
		
		resultadoOperacion = "El Nombre Del Usuario Ha Sido Actualizado!";
		
		return resultadoOperacion;
	}

	
	/**
	 * Nombre: buscarUsuario 
	 * Descripcion: Entrega un usuario, consultado por su cedula
 	 * @param - cedula : Cedula del usuario, con ella se busca el registro del mismo
	 * @return - Usuario : El usuario que se haya encontrado (null si no existe un usuario
	 * 			 con la cedula descrita anteriormente)
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(String cedula) throws Exception {
		
		Usuario usuEncontrado = null;

		if (cedula == null || cedula.trim().equals(""))
			throw new Exception("La Cedula del usuario no puede ser vacía!");

		if (!revisorCadenas.soloContieneNumeros(cedula))
			throw new Exception("La Cedula solo puede contener números!");

		usuEncontrado = usuarioDAOW.buscarUsuarioPorCedula(cedula);
		
		return usuEncontrado;
	}
	
	/**
	 * Nombre: buscarRol
	 * Descripcion: Entrega la instancia de un Rol, buscado por medio de su identificador
	 * @param - idRol : El identificador del rol que se quiere buscar
	 * @return - Rol : La instancia del Rol que se haya encontrado
	 * **/

	@Override
	@Transactional(readOnly = true)
	public Rol buscarRol(BigDecimal idRol) throws Exception {
		
		if(idRol.compareTo(new BigDecimal("0")) == 0)
			throw new Exception("El id del Rol no puede ser nulo!");
		
		if((idRol +"").equals(""))
			throw new Exception("El id del Rol no puede ser vacío!");
		
		return rolDAO.findById(idRol);
	}


	
}
