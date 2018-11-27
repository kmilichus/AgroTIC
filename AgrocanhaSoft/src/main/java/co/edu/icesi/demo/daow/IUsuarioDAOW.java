package co.edu.icesi.demo.daow;

import co.edu.icesi.demo.modelo.Usuario;

public interface IUsuarioDAOW{
	
	public Usuario buscarUsuarioPorCedula(String cedula);
}
