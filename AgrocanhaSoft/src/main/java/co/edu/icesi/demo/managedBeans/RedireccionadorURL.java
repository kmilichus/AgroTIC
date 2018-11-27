package co.edu.icesi.demo.managedBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.icesi.demo.logica.IAccesoLogic;
import co.edu.icesi.demo.logica.IAdministracionLogic;
import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;

@Service("redireccionadorURL")
@Scope("singleton")
public class RedireccionadorURL implements IRedireccionador {

	@Autowired
	private IAccesoLogic accesoLogic;

	@Autowired
	private IAdministracionLogic administracionLogic;

	private final static String SUB_URL_REDIRECCION_PAGINAS = "/WEB-INF/plantilla/";

	@Override
	public String darMenuRol() {

		try {

			String URL = "";

			Usuario usuarioLogueado = accesoLogic.darUsuarioLogueado();

			if (usuarioLogueado != null) {

				Rol rolUsuario = administracionLogic.buscarRol(usuarioLogueado.getRol().getRolid());

				switch (rolUsuario.getNombrerol()) {
				case "Auxiliar Agronomia":
					URL = SUB_URL_REDIRECCION_PAGINAS + "templateAuxiliarAgronomo.xhtml";
					break;

				case "Agronomo":
					URL = SUB_URL_REDIRECCION_PAGINAS + "templateAgronomo.xhtml";
					break;

				case "Administrador Del Sistema":
					URL = SUB_URL_REDIRECCION_PAGINAS + "/templateAdministrador.xhtml";
					break;

				}

				

			}else {
				URL = SUB_URL_REDIRECCION_PAGINAS + "/templateNulo.xhtml";
			}
			
			return URL;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
