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
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.mapeo.MapeoDatosHumedad;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Nivelhumedad;
import co.edu.icesi.demo.modelo.Tipopermeabilidad;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;

@ManagedBean
@ViewScoped
public class ZonaGeoMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;

	private String URLMenu; 
	
	private CommandButton btnCargarDatosZonaAgro;

	private InputText txtLatitud;

	private InputText txtAltitud;

	private SelectOneMenu menuZonasAgro;
	private List<SelectItem> itemsZonasAgro;

	private CommandButton btnConsultarReporteCenicanha;

	private SelectOneMenu menuIngenios;
	private List<SelectItem> itemsIngenios;

	private SelectOneMenu menuFamiliasTexturales;
	private List<SelectItem> itemsFamiliasTexturales;

	private InputTextarea txtGruposTexturales;

	private List<MapeoCaracteristica> caracteristicasZonaAgro;

	private List<Zonavariedad> zonasVariedad;

	private InputText txtGrupoHomogeneoSuelo;

	private InputText txtGrupoHumedad;
	
	private List<MapeoDatosHumedad> datosHumedad;

	public void consultarDiagnosticoCenicana() {

		try {
			if (menuZonasAgro.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar una Zona Agroecológica!");

			String codigoZona = menuZonasAgro.getValue().toString();

			Zonaagroecologica zonaSeleccionada = delegadoDeNegocio.buscarZonaAgroecologica(codigoZona);

			List<Ingenio> ingenios = delegadoDeNegocio.darIngenios(zonaSeleccionada);

			List<Familiatextural> familiasTx = delegadoDeNegocio.darFamiliasTexturales(zonaSeleccionada);

			List<Grupotextural> gruposTx = delegadoDeNegocio.darGruposTexturales(zonaSeleccionada);

			zonasVariedad = delegadoDeNegocio.darZonasVariedad(zonaSeleccionada);

			caracteristicasZonaAgro = delegadoDeNegocio.darCaracteristicasZonaAgroecologica(zonaSeleccionada);

			Grupohomogeneosuelo grupoSuelo = delegadoDeNegocio.darGrupoHomogeneoSuelo(zonaSeleccionada);

			Grupohumedad grupoHumedad = delegadoDeNegocio.darGrupoHumedad(zonaSeleccionada);

			String numeroGrupoSuelo = grupoSuelo.getNumerogrupo() + "";

			String nombreGrupoHumedad = grupoHumedad.getNombregrhum();

			List<NivelhumTipopermeaGrupohum> listaHumedad = delegadoDeNegocio.darDatosHumedad(zonaSeleccionada);

			generarTablaDatosHumedad(listaHumedad);
			
			txtGrupoHomogeneoSuelo.setValue(numeroGrupoSuelo);
			txtGrupoHumedad.setValue(nombreGrupoHumedad);

			setearGruposTexturales(gruposTx);

			inicializarListaIngenios(ingenios);

			inicializarListaFamiliasTexturales(familiasTx);

			generadorDialogos.desplegarMensajeExito("Reporte de Cenicaña Importado!");

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}
	}

	private void generarTablaDatosHumedad(List<NivelhumTipopermeaGrupohum> listaHumedad) {
		
		datosHumedad = new ArrayList<MapeoDatosHumedad>();
		
		for (NivelhumTipopermeaGrupohum dato : listaHumedad) {
			
			BigDecimal idGrupoHumedad = dato.getGrupohumedad().getGrupohumid();
			BigDecimal idNivelHumedad = dato.getNivelhumedad().getNivelhumid();
			BigDecimal idTipoPermeabilidad = dato.getTipopermeabilidad().getTipoperid();
			
			Grupohumedad grupoHumedad = delegadoDeNegocio.darGrupoHumedadPorID(idGrupoHumedad);
			
			Nivelhumedad nivelHumedad = delegadoDeNegocio.darNivelHumedadPorID(idNivelHumedad);
			
			Tipopermeabilidad tipoPermeabilidad = delegadoDeNegocio.darTipoPermeabilidadPorID(idTipoPermeabilidad);
			
			MapeoDatosHumedad mapeo = new MapeoDatosHumedad();
			
			String nombreGH = grupoHumedad.getNombregrhum();
			
			String nombreNH = nivelHumedad.getNombrenvhum();
			
			String nombreTP = tipoPermeabilidad.getNombretipoper();
			
			mapeo.setNombreGrupoHumedad(nombreGH);
			mapeo.setNombreNivelHumedad(nombreNH);
			mapeo.setNombreTipoPermeabilidad(nombreTP);
			
			datosHumedad.add(mapeo);
		}
		
	}

	private void setearGruposTexturales(List<Grupotextural> gruposTx) {

		String cadena = " - ";
		for (Grupotextural grupotextural : gruposTx) {
			cadena = cadena + grupotextural.getNumerogrupotext() + " - ";
		}

		txtGruposTexturales.setValue(cadena);

	}

	private void inicializarListaFamiliasTexturales(List<Familiatextural> familiasTx) {

		itemsFamiliasTexturales = new ArrayList<SelectItem>();

		for (Familiatextural familiatextural : familiasTx) {

			itemsFamiliasTexturales.add(new SelectItem(familiatextural.getFamiliatexid(),
					familiatextural.getNombrefamtext() + " [" + familiatextural.getAbreviaturafamtext() + "]"));
		}

	}

	private void inicializarListaIngenios(List<Ingenio> ingenios) {
		
		itemsIngenios = new ArrayList<SelectItem>();

		for (Ingenio ingenio : ingenios) {
			itemsIngenios.add(new SelectItem(ingenio.getIngid(), ingenio.getNombreing()));
		}
		
			
	}

	public void darZonaAgroPorUbicacion() {

		generadorDialogos.desplegarMensajeExito("Datos de la Zona Agroecologica Cargados!");

		// TODO
	}

	public CommandButton getBtnCargarDatosZonaAgro() {
		return btnCargarDatosZonaAgro;
	}

	public void setBtnCargarDatosZonaAgro(CommandButton btnCargarDatosZonaAgro) {
		this.btnCargarDatosZonaAgro = btnCargarDatosZonaAgro;
	}

	public InputText getTxtLatitud() {
		return txtLatitud;
	}

	public void setTxtLatitud(InputText txtLatitud) {
		this.txtLatitud = txtLatitud;
	}

	public InputText getTxtAltitud() {
		return txtAltitud;
	}

	public void setTxtAltitud(InputText txtAltitud) {
		this.txtAltitud = txtAltitud;
	}

	public CommandButton getBtnConsultarReporteCenicanha() {
		return btnConsultarReporteCenicanha;
	}

	public void setBtnConsultarReporteCenicanha(CommandButton btnConsultarReporteCenicanha) {
		this.btnConsultarReporteCenicanha = btnConsultarReporteCenicanha;
	}

	public SelectOneMenu getMenuIngenios() {
		return menuIngenios;
	}

	public void setMenuIngenios(SelectOneMenu menuIngenios) {
		this.menuIngenios = menuIngenios;
	}

	public List<SelectItem> getItemsIngenios() {
		return itemsIngenios;
	}

	public void setItemsIngenios(List<SelectItem> itemsIngenios) {
		this.itemsIngenios = itemsIngenios;
	}

	public SelectOneMenu getMenuFamiliasTexturales() {
		return menuFamiliasTexturales;
	}

	public void setMenuFamiliasTexturales(SelectOneMenu menuFamiliasTexturales) {
		this.menuFamiliasTexturales = menuFamiliasTexturales;
	}

	public List<SelectItem> getItemsFamiliasTexturales() {
		return itemsFamiliasTexturales;
	}

	public void setItemsFamiliasTexturales(List<SelectItem> itemsFamiliasTexturales) {
		this.itemsFamiliasTexturales = itemsFamiliasTexturales;
	}

	public InputTextarea getTxtGruposTexturales() {
		return txtGruposTexturales;
	}

	public void setTxtGruposTexturales(InputTextarea txtGruposTexturales) {
		this.txtGruposTexturales = txtGruposTexturales;
	}

	public List<MapeoCaracteristica> getCaracteristicasZonaAgro() {
		return caracteristicasZonaAgro;
	}

	public void setCaracteristicasZonaAgro(List<MapeoCaracteristica> caracteristicasZonaAgro) {
		this.caracteristicasZonaAgro = caracteristicasZonaAgro;
	}

	public InputText getTxtGrupoHomogeneoSuelo() {
		return txtGrupoHomogeneoSuelo;
	}

	public void setTxtGrupoHomogeneoSuelo(InputText txtGrupoHomogeneoSuelo) {
		this.txtGrupoHomogeneoSuelo = txtGrupoHomogeneoSuelo;
	}

	public InputText getTxtGrupoHumedad() {
		return txtGrupoHumedad;
	}

	public void setTxtGrupoHumedad(InputText txtGrupoHumedad) {
		this.txtGrupoHumedad = txtGrupoHumedad;
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

	public SelectOneMenu getMenuZonasAgro() {
		return menuZonasAgro;
	}

	public void setMenuZonasAgro(SelectOneMenu menuZonasAgro) {
		this.menuZonasAgro = menuZonasAgro;
	}

	public List<SelectItem> getItemsZonasAgro() {

		if (itemsZonasAgro == null) {

			List<Zonaagroecologica> zonasAgro = delegadoDeNegocio.darTodasZonasAgroecologicas();

			itemsZonasAgro = new ArrayList<SelectItem>();

			for (Zonaagroecologica zonaActual : zonasAgro) {

				SelectItem selectItem = new SelectItem(zonaActual.getCodigoznagro(), zonaActual.getCodigoznagro());

				itemsZonasAgro.add(selectItem);
			}
		}

		return itemsZonasAgro;
	}

	public void setItemsZonasAgro(List<SelectItem> itemsZonasAgro) {
		this.itemsZonasAgro = itemsZonasAgro;
	}

	public List<Zonavariedad> getZonasVariedad() {
		return zonasVariedad;
	}

	public void setZonasVariedad(List<Zonavariedad> zonasVariedad) {
		this.zonasVariedad = zonasVariedad;
	}

	public List<MapeoDatosHumedad> getDatosHumedad() {
		return datosHumedad;
	}

	public void setDatosHumedad(List<MapeoDatosHumedad> datosHumedad) {
		this.datosHumedad = datosHumedad;
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
