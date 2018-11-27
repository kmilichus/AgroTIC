package co.edu.icesi.demo.logica;

import java.math.BigDecimal;

import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;

public interface IAdministracionLogic {
	
	public String registrarUsuario(Usuario nuevoUsuario) throws Exception;

	public String eliminarUsuario(String cedulaUsuario) throws Exception;
	
	public String cambiarNombreUsuario(String nuevoNombre, Usuario usuarioAModificar) throws Exception;
	
	public Usuario buscarUsuario (String cedula) throws Exception;

	public Rol buscarRol(BigDecimal idRol) throws Exception;
	
}
