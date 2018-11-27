package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IUsuarioDAO;
import co.edu.icesi.demo.daow.IRolDAOW;
import co.edu.icesi.demo.daow.IUsuarioDAOW;
import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.servicios.IAdministradorCadenas;
import co.edu.icesi.demo.util.FacesUtils;

@Scope("singleton")
@Service("accesoLogica")
public class AccesoLogic implements IAccesoLogic {

	@Autowired
	private IRolDAOW rolDAOW;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private IUsuarioDAOW usuarioDAOW;
	
	@Autowired
	private IAdministradorCadenas revisorCadenas;
	
	private final static String CLAVE_UTIL = "AgroUsuario";
	
	/**
	 * Nombre: Iniciar Sesión
	 * Descripcion: Loguea a un usuario, por medio de unas credenciales, para permitirle el
	 * 				uso de los servicios del sistema
	 * @param - cedulaUsuario : La cedula del usuario, quién quiere iniciar sesión en el
	 * 							sistema
	 * @param - password : La contraseña de la cuenta del usuario, quién
	 * 					   quiere iniciar sesión en el sistema
	 * @return - boolean : Indica si las credenciales ingresadas fueron validas y así se le
	 * 					   permite al usuario ingresar al sistema
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public boolean iniciarSesion(String cedulaUsuario, String password) throws Exception{

		boolean fueAutenticado = false;

		if (cedulaUsuario == null || cedulaUsuario.trim().equalsIgnoreCase("")){
			throw new Exception("La cedula está vacía!");
		}
		
		if (password == null || password.trim().equalsIgnoreCase("")){
			throw new Exception("La contraseña está vacía!");
		}
		
		Usuario usuario = usuarioDAOW.buscarUsuarioPorCedula(cedulaUsuario);
		
		if (usuario == null){
			throw new Exception("No Existe Un Usuario con la Cedula Digitada");
		}
		
		if(!usuario.getPasswordusu().equals(password)){
			throw new Exception("Error: Contraseña Incorrecta!");
		}
		
		FacesUtils.putinSession(CLAVE_UTIL, usuario);
		
		fueAutenticado = true;
		
		return fueAutenticado;
	}
	
	/**
	 * Nombre: CambiarContrasenha
	 * Descripcion: Permite a un usuario, modificar la contraseña de su cuenta
	 * @param - nuevoPassword : La contraseña nueva de la cuenta del usuario
	 * @param - usuario : El usuario, a quién se le hará un cambio de contraseña
	 * @return - String : Un mensaje con el resultado del proceso 0de cambio de contraseña
	 * **/

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String cambiarContrasenha(String nuevoPassword, Usuario usuario) throws Exception{

		String resultadoOperacion = "";

		if (usuario==null){
			throw new Exception("El Usuario es nulo!");
		}
		
		if (nuevoPassword == null || nuevoPassword.trim().equalsIgnoreCase("")){
			throw new Exception("La nueva contraseña está vacía!");
		}
		
		if (!revisorCadenas.longitudPasswordValida(nuevoPassword))
			throw new Exception("La contraseña debe tener entre 6 y 10 caracteres!");
		
		Usuario usuarioBuscado = usuarioDAOW.buscarUsuarioPorCedula(usuario.getCedulausu());

		if (usuarioBuscado == null)
			throw new Exception("El Usuario no está registrado!");
	
		
		usuario.setPasswordusu(nuevoPassword);
		
		usuarioDAO.merge(usuario);
		
		resultadoOperacion = "Contraseña modificada correctamente!";
		
		return resultadoOperacion;
	}

	/**
	 * Nombre: darTodosLosRoles
	 * Descripcion: Entrega una lista con todos los roles (tipo de usuarios)
	 * 				que hay validados dentro del
	 * 				sistema
	 * @return - List<Rol> - La lista de los roles que hay disponibles en el sistema,
	 * 						 para iniciar sesión
	 * **/
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> darTodosLosRoles() {
		
		return rolDAOW.darTodosLosRoles();
	}

	/**
	 * Nombre: darUsuarioLogueado
	 * Descripcion: Entrega el usuario que está logueado en el sistema
	 * @return - Usuario - El usuario que ha iniciado sesión en el sistema
	 * **/
	
	@Override
	public Usuario darUsuarioLogueado() {
		return (Usuario) FacesUtils.getfromSession(CLAVE_UTIL);
	}

}
