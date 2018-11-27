package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;

public interface IAccesoLogic {
	
	public boolean iniciarSesion(String cedulaUsuario, String password) throws Exception;

	public String cambiarContrasenha(String nuevoPassword, Usuario usuario) throws Exception;
	
	public List<Rol> darTodosLosRoles();

	public Usuario darUsuarioLogueado();
	
}
