package co.edu.icesi.demo.managedBeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;
	
	private String URLMenu; 

	private InputText txtCedulaUsu;

	private InputText txtNombreUsu;

	private Password txtPasswordUsu;

	private Password txtNuevoPasswordUsu;

	private Password txtConfirmaPass;

	private SelectBooleanCheckbox boxModificarPassword;

	private CommandButton btnActualizarDatos, btnCargarDatosPersonales;
	private CommandButton btnRegistrarUsu;
	private CommandButton btnEliminarUsu;

	private SelectOneMenu menuRolesUsu;
	private List<SelectItem> itemRolesUsu;

	private final static String SUB_URL_REDIRECCION_PAGINAS = "?faces-redirect=true";
	
	public String iniciarSesion() {

		String URL = "";

		try {

			if(txtCedulaUsu.getValue().toString().trim().equals(""))
				throw new Exception("El Campo de la Cedula no puede ser nulo!");
				
			String cedulaUsuario = txtCedulaUsu.getValue().toString().trim();
			String password = txtPasswordUsu.getValue().toString().trim();

			boolean autenticado = delegadoDeNegocio.iniciarSesion(cedulaUsuario, password);

			if (autenticado) {
				URL = "";

				Usuario usuarioLogueado = delegadoDeNegocio.buscarUsuario(cedulaUsuario);
				Rol rolUsuario = delegadoDeNegocio.buscarRol(usuarioLogueado.getRol().getRolid());

				switch (rolUsuario.getNombrerol()) {
				case "Auxiliar Agronomia":
					URL = "/initialMenuAuxiliarAgronomia.xhtml" + SUB_URL_REDIRECCION_PAGINAS;
					break;

				case "Agronomo":
					URL="/initialMenuAgronomo.xhtml" + SUB_URL_REDIRECCION_PAGINAS;
					break;

				case "Auxiliar Laboratorio":
					URL="/initialMenuAuxiliarLaboratorio.xhtml" + SUB_URL_REDIRECCION_PAGINAS;
					break;

				case "Administrador Del Sistema":
					URL="/initialMenuAdministrador.xhtml" + SUB_URL_REDIRECCION_PAGINAS;
					break;

				default:
					throw new Exception ("El Usuario logueado no tiene un"
							+ " rol registrado en el sistema!");
				}

			}

			return URL;

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
			
			return"";
		}

	}

	public void registrarUsuario() {

		try {

			if (txtCedulaUsu.getValue() == null || txtCedulaUsu.getValue().toString().trim().equals(""))
				throw new Exception("La cedula no puede ser vacía!");

			if (txtNombreUsu.getValue() == null || txtNombreUsu.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre no puede ser vacío!");

			if (menuRolesUsu.getValue().toString().equals("-1"))
				throw new Exception("Debe Seleccionar un Rol!");

			if (txtPasswordUsu.getValue() == null || txtPasswordUsu.getValue().toString().trim().equals(""))
				throw new Exception("El Password no puede ser vacío!");

			String cedula = txtCedulaUsu.getValue().toString().trim();

			String nombre = txtNombreUsu.getValue().toString().trim();

			String passwordusu = txtPasswordUsu.getValue().toString();

			BigDecimal idRol = new BigDecimal(menuRolesUsu.getValue().toString());

			Rol rol = delegadoDeNegocio.buscarRol(idRol);

			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setCedulausu(cedula);
			nuevoUsuario.setNombreusu(nombre);
			nuevoUsuario.setPasswordusu(passwordusu);
			nuevoUsuario.setRol(rol);

			String msje = delegadoDeNegocio.registrarUsuario(nuevoUsuario);

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void eliminarUsuario() {

		try {

			if (txtCedulaUsu.getValue() == null || txtCedulaUsu.getValue().toString().trim().equals(""))
				throw new Exception("La cedula no puede ser vacía!");

			String cedulaUsuario = txtCedulaUsu.getValue().toString();

			String msje = delegadoDeNegocio.eliminarUsuario(cedulaUsuario);

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void actualizarDatosUsuario() {

		try {

			if (txtNombreUsu.getValue() == null || txtNombreUsu.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre no puede ser vacío!");

			String nombreNuevo = txtNombreUsu.getValue().toString().trim();

			String password = "", nPassword = "", confPassword = "";

			Usuario usuario = delegadoDeNegocio.darUsuarioLogueado();

			if (boxModificarPassword.getValue().toString().equalsIgnoreCase("true")) {

				if (txtPasswordUsu.getValue() == null || txtPasswordUsu.getValue().toString().trim().equals(""))
					throw new Exception("El Password no puede ser vacío!");

				if (txtNuevoPasswordUsu.getValue() == null
						|| txtNuevoPasswordUsu.getValue().toString().trim().equals(""))
					throw new Exception("El Nuevo Password no puede ser vacío!");

				if (txtConfirmaPass.getValue() == null || txtConfirmaPass.getValue().toString().trim().equals(""))
					throw new Exception("El Campo 'Confirmar Password' no puede ser vacío!");

				password = txtPasswordUsu.getValue().toString();

				nPassword = txtNuevoPasswordUsu.getValue().toString();

				confPassword = txtConfirmaPass.getValue().toString();

				if (!password.equals(usuario.getPasswordusu()))
					throw new Exception("La contraseña antigua no es correcta!");

				if (!confPassword.equals(nPassword))
					throw new Exception("Los campos de la nueva contraseña no coinciden!");

				delegadoDeNegocio.cambiarContraseña(nPassword, usuario);

			}

			delegadoDeNegocio.cambiarNombreUsuario(nombreNuevo, usuario);

			generadorDialogos.desplegarMensajeExito("Datos Actualizados correctamente!");

		} catch (Exception e) {
			
			generadorDialogos.desplegarMensajeError(e.getMessage());
			
			e.printStackTrace();
		}

	}

	public void cargarInfoUsuario() {

		try {

			Usuario usuario = delegadoDeNegocio.darUsuarioLogueado();
			
			String cedula = usuario.getCedulausu().trim();

			String nombre = usuario.getNombreusu().trim();

			txtCedulaUsu.setValue(cedula);
			txtNombreUsu.setValue(nombre);

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public InputText getTxtCedulaUsu() {
		return txtCedulaUsu;
	}

	public void setTxtCedulaUsu(InputText txtCedulaUsu) {
		this.txtCedulaUsu = txtCedulaUsu;
	}

	public InputText getTxtNombreUsu() {
		return txtNombreUsu;
	}

	public void setTxtNombreUsu(InputText txtNombreUsu) {
		this.txtNombreUsu = txtNombreUsu;
	}

	public Password getTxtPasswordUsu() {
		return txtPasswordUsu;
	}

	public void setTxtPasswordUsu(Password txtPasswordUsu) {
		this.txtPasswordUsu = txtPasswordUsu;
	}

	public Password getTxtNuevoPasswordUsu() {
		return txtNuevoPasswordUsu;
	}

	public void setTxtNuevoPasswordUsu(Password txtNuevoPasswordUsu) {
		this.txtNuevoPasswordUsu = txtNuevoPasswordUsu;
	}

	public Password getTxtConfirmaPass() {
		return txtConfirmaPass;
	}

	public void setTxtConfirmaPass(Password txtConfirmaPass) {
		this.txtConfirmaPass = txtConfirmaPass;
	}

	public SelectBooleanCheckbox getBoxModificarPassword() {
		return boxModificarPassword;
	}

	public void setBoxModificarPassword(SelectBooleanCheckbox boxModificarPassword) {
		this.boxModificarPassword = boxModificarPassword;
	}

	public CommandButton getBtnActualizarDatos() {
		return btnActualizarDatos;
	}

	public void setBtnActualizarDatos(CommandButton btnActualizarDatos) {
		this.btnActualizarDatos = btnActualizarDatos;
	}

	public CommandButton getBtnRegistrarUsu() {
		return btnRegistrarUsu;
	}

	public void setBtnRegistrarUsu(CommandButton btnRegistrarUsu) {
		this.btnRegistrarUsu = btnRegistrarUsu;
	}

	public CommandButton getBtnEliminarUsu() {
		return btnEliminarUsu;
	}

	public void setBtnEliminarUsu(CommandButton btnEliminarUsu) {
		this.btnEliminarUsu = btnEliminarUsu;
	}

	public SelectOneMenu getMenuRolesUsu() {
		return menuRolesUsu;
	}

	public void setMenuRolesUsu(SelectOneMenu menuRolesUsu) {
		this.menuRolesUsu = menuRolesUsu;
	}

	public List<SelectItem> getItemRolesUsu() {

		if (itemRolesUsu == null) {

			List<Rol> roles = delegadoDeNegocio.darTodosLosRoles();

			itemRolesUsu = new ArrayList<SelectItem>();

			for (Rol rolActual : roles) {

				SelectItem selectItem = new SelectItem(rolActual.getRolid(), rolActual.getNombrerol());

				itemRolesUsu.add(selectItem);
			}
		}

		return itemRolesUsu;
	}

	public void setItemRolesUsu(List<SelectItem> itemRolesUsu) {
		this.itemRolesUsu = itemRolesUsu;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public IPopUpMensjes getGeneradorDialogos() {
		return generadorDialogos;
	}

	public void setGeneradorDialogos(IPopUpMensjes generadorDialogos) {
		this.generadorDialogos = generadorDialogos;
	}

	public CommandButton getBtnCargarDatosPersonales() {
		return btnCargarDatosPersonales;
	}

	public void setBtnCargarDatosPersonales(CommandButton btnCargarDatosPersonales) {
		this.btnCargarDatosPersonales = btnCargarDatosPersonales;
	}
	
	public IRedireccionador getRedireccionadorURL() {
		return redireccionadorURL;
	}

	public void setRedireccionadorURL(IRedireccionador redireccionadorURL) {
		this.redireccionadorURL = redireccionadorURL;
	}
	
	public String getURLMenu() {
		
		URLMenu = redireccionadorURL.darMenuRol();
		return URLMenu;
	}

	public void setURLMenu(String uRLMenu) {
		URLMenu = uRLMenu;
	}

}
