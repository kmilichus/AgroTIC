package co.edu.icesi.demo.managedBeans;

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
import co.edu.icesi.demo.mapeo.MapeoMedicionLAB;
import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.modelo.Zonaagroecologica;

@ManagedBean
@ViewScoped
public class DiagnosticoMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;
	
	private String URLMenu; 

	private InputText txtAgronomo;
	
	private SelectOneMenu menuTerrenos;
	private List<SelectItem> itemsTerrenos;

	private SelectOneMenu menuEstrategiasMuestreo;
	private List<SelectItem> itemsEstrategiasMuestreo;

	private CommandButton btnConsultarDiagnostico;

	private InputTextarea txtObservaciones;

	private InputText txtFechaRegistro;

	private InputText txtZonaAgroecologica;

	private List<MapeoCaracteristica> caracteristicasZonaAgro;

	private SelectOneMenu menuLotes;
	private List<SelectItem> itemsLotes;

	private InputText txtCantidadPuntosRecoleccion;

	private List<MapeoMedicionLAB> medidasLaboratorio;

	private SelectOneMenu menuZonasAgro;
	private List<SelectItem> itemsZonasAgro;

	private CommandButton btnRegistrarDiagnostico;

	public void consultarDiagnosticoAgronomo() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1") ||
					menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno y un Plan de Muestreo!");

			
			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoPlanMuestreo = menuEstrategiasMuestreo.getValue().toString();

			Diagnosticoagronomo diagnostico = delegadoDeNegocio.darDiagnosticoDeEstrategia(terreno, codigoPlanMuestreo);

			txtObservaciones.setValue(diagnostico.getDescripciondiag());

			txtFechaRegistro.setValue(diagnostico.getFechacreaciondiag() + "");
			
			Zonaagroecologica zonaAgro = delegadoDeNegocio.darZonaDiagnostico(diagnostico);

			txtZonaAgroecologica.setValue(zonaAgro.getCodigoznagro());
			
			caracteristicasZonaAgro = delegadoDeNegocio.darCaracteristicasZonaAgroecologica(zonaAgro);

			txtAgronomo.setValue(delegadoDeNegocio.consultarAgronomo(diagnostico).getNombreusu());
			
			generadorDialogos.desplegarMensajeExito("Datos del"
					+ " Diagnóstico-Agronomo Cargados!");

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	
	public void registrarDiagnosticoAgronomo() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");
			
			if(menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Plan de Muestreo!");
	
			if(menuZonasAgro.getValue().toString().equals("-1"))	
				throw new Exception("Debe seleccionar una Zona Agroecológica!");
				
			
			if (txtObservaciones.getValue() == null || txtObservaciones.getValue().toString().trim().equals(""))
				throw new Exception("El Campo de Observaciones no puede estár vacío!");

			String descripcion = txtObservaciones.getValue().toString().trim();

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Estrategiamuestreo plan = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String codigoZona = menuZonasAgro.getValue().toString();

			Zonaagroecologica zonaAgro = delegadoDeNegocio.buscarZonaAgroecologica(codigoZona);

			Usuario usuario = delegadoDeNegocio.darUsuarioLogueado();

			Diagnosticoagronomo nDgAgronomo = new Diagnosticoagronomo();
			nDgAgronomo.setDescripciondiag(descripcion);
			nDgAgronomo.setEstrategiamuestreo(plan);
			nDgAgronomo.setUsuario(usuario);
			nDgAgronomo.setZonaagroecologica(zonaAgro);

			String mensaje = delegadoDeNegocio.registrarDiagnostico(nDgAgronomo);

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void cargarLotesDelTerreno() {

		try {

			if (!menuTerrenos.getValue().toString().equals("-1")) {

				String nombreTerreno = menuTerrenos.getValue().toString();

				List<Lote> lotes = delegadoDeNegocio.darLotes(nombreTerreno);

				itemsLotes = new ArrayList<SelectItem>();

				for (Lote loteActual : lotes) {

					SelectItem selectItem = new SelectItem(loteActual.getNombrelote(), loteActual.getNombrelote());

					itemsLotes.add(selectItem);
				}
			}
			else {
				itemsLotes = null;
			}

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void cargarEstrategiasDelTerreno() {
		
		try {

			if(!menuTerrenos.getValue().toString().equals("-1")){
				
			
			
			String nombreTerreno = menuTerrenos.getValue().toString();

			List<Estrategiamuestreo> planes = delegadoDeNegocio.darEstrategiasMuestro(nombreTerreno);

			itemsEstrategiasMuestreo = new ArrayList<SelectItem>();

			for (Estrategiamuestreo estActual : planes) {

				SelectItem selectItem = new SelectItem(estActual.getCodigoest(), estActual.getCodigoest());

				itemsEstrategiasMuestreo.add(selectItem);
			}

			}
			
			else {
				itemsEstrategiasMuestreo = null;
			}
			
		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError("Error: " + e.getMessage() + "!");

			e.printStackTrace();
		}
	}

	public void generarReporteLaboratorioDelLote() {

		
		try {

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar una Estrategia de Muestreo!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			else {
				String codigoPlanMuestreo = menuEstrategiasMuestreo.getValue().toString();
				String nombreTerreno = menuTerrenos.getValue().toString();
				String nombreLote = menuLotes.getValue().toString();

				Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);
				Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno,
						codigoPlanMuestreo);

				Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

				medidasLaboratorio = delegadoDeNegocio.darAnalisisLaboratorio(planMuestreo, lote);

				String numPuntos = delegadoDeNegocio.darCanticadPuntosRecoleccion(lote, planMuestreo) + "";

				txtCantidadPuntosRecoleccion.setValue(numPuntos);

				generadorDialogos.desplegarMensajeExito("Analisis del Lote Cargado!");
			}

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();

		}
		
	
	}

	public SelectOneMenu getMenuTerrenos() {
		return menuTerrenos;
	}

	public void setMenuTerrenos(SelectOneMenu menuTerrenos) {
		this.menuTerrenos = menuTerrenos;
	}

	public List<SelectItem> getItemsTerrenos() {

		if (itemsTerrenos == null) {

			List<Terreno> terrenos = delegadoDeNegocio.darTodosLosTerrenos();

			itemsTerrenos = new ArrayList<SelectItem>();

			for (Terreno terrActual : terrenos) {

				SelectItem selectItem = new SelectItem(terrActual.getNombreterr(), terrActual.getNombreterr());

				itemsTerrenos.add(selectItem);
			}
		}

		return itemsTerrenos;
	}

	public void setItemsTerrenos(List<SelectItem> itemsTerrenos) {
		this.itemsTerrenos = itemsTerrenos;
	}

	public SelectOneMenu getMenuEstrategiasMuestreo() {
		return menuEstrategiasMuestreo;
	}

	public void setMenuEstrategiasMuestreo(SelectOneMenu menuEstrategiasMuestreo) {
		this.menuEstrategiasMuestreo = menuEstrategiasMuestreo;
	}

	public List<SelectItem> getItemsEstrategiasMuestreo() {
		return itemsEstrategiasMuestreo;
	}

	public void setItemsEstrategiasMuestreo(List<SelectItem> itemsEstrategiasMuestreo) {
		this.itemsEstrategiasMuestreo = itemsEstrategiasMuestreo;
	}

	public CommandButton getBtnConsultarDiagnostico() {
		return btnConsultarDiagnostico;
	}

	public void setBtnConsultarDiagnostico(CommandButton btnConsultarDiagnostico) {
		this.btnConsultarDiagnostico = btnConsultarDiagnostico;
	}

	public InputTextarea getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(InputTextarea txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}

	public InputText getTxtFechaRegistro() {
		return txtFechaRegistro;
	}

	public void setTxtFechaRegistro(InputText txtFechaRegistro) {
		this.txtFechaRegistro = txtFechaRegistro;
	}

	public InputText getTxtZonaAgroecologica() {
		return txtZonaAgroecologica;
	}

	public void setTxtZonaAgroecologica(InputText txtZonaAgroecologica) {
		this.txtZonaAgroecologica = txtZonaAgroecologica;
	}

	public List<MapeoCaracteristica> getCaracteristicasZonaAgro() {
		return caracteristicasZonaAgro;
	}

	public void setCaracteristicasZonaAgro(List<MapeoCaracteristica> caracteristicasZonaAgro) {
		this.caracteristicasZonaAgro = caracteristicasZonaAgro;
	}

	public SelectOneMenu getMenuLotes() {
		return menuLotes;
	}

	public void setMenuLotes(SelectOneMenu menuLotes) {
		this.menuLotes = menuLotes;
	}

	public List<SelectItem> getItemsLotes() {
		return itemsLotes;
	}

	public void setItemsLotes(List<SelectItem> itemsLotes) {
		this.itemsLotes = itemsLotes;
	}

	public InputText getTxtCantidadPuntosRecoleccion() {
		return txtCantidadPuntosRecoleccion;
	}

	public void setTxtCantidadPuntosRecoleccion(InputText txtCantidadPuntosRecoleccion) {
		this.txtCantidadPuntosRecoleccion = txtCantidadPuntosRecoleccion;
	}

	public List<MapeoMedicionLAB> getMedidasLaboratorio() {
		return medidasLaboratorio;
	}

	public void setMedidasLaboratorio(List<MapeoMedicionLAB> medidasLaboratorio) {
		this.medidasLaboratorio = medidasLaboratorio;
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

				SelectItem selectItem = new SelectItem(zonaActual.getZonaagroid(), zonaActual.getCodigoznagro());

				itemsZonasAgro.add(selectItem);
			}
		}

		return itemsZonasAgro;
	}

	public void setItemsZonasAgro(List<SelectItem> itemsZonasAgro) {
		this.itemsZonasAgro = itemsZonasAgro;
	}

	public CommandButton getBtnRegistrarDiagnostico() {
		return btnRegistrarDiagnostico;
	}

	public void setBtnRegistrarDiagnostico(CommandButton btnRegistrarDiagnostico) {
		this.btnRegistrarDiagnostico = btnRegistrarDiagnostico;
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


	public InputText getTxtAgronomo() {
		return txtAgronomo;
	}


	public void setTxtAgronomo(InputText txtAgronomo) {
		this.txtAgronomo = txtAgronomo;
	}

}
