package co.edu.icesi.demo.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.demo.mapeo.MapeoMedicionLAB;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;

@ManagedBean
@ViewScoped
public class SuelosMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;

	private String URLMenu;

	private SelectOneMenu menuTerrenos, menuTerrenosParaLote;
	private List<SelectItem> itemsTerrenos, itemsTerrenosParaLote;

	private SelectOneMenu menuLotes;
	private List<SelectItem> itemsLotes;

	private SelectOneMenu menuEstrategiasMuestreo;
	private List<SelectItem> itemsEstrategiasMuestreo;


	private InputText txtNombreLote, txtNombreTerreno, txtCodigoPlanMuestreo;

	private InputText txtDescripcionLote, txtCantidadMuestrasRecolectadas;

	private CommandButton btnRegistrarLote, btnRegistrarTerreno, btnRegistrarEstrategia;

	private CommandButton btnModificarLote, btnModificarTerreno, btnAsignarPuntosALote;

	private CommandButton btnEliminarLote, btnEliminarTerreno, btnEliminarEstrategia;

	private CommandButton btnConsultarPlanMuestreo, btnConsultarReporteLaboratorio;

	private List<MapeoMedicionLAB> medidasLaboratorio;

	private List<Puntorecoleccion> datosMuestreo;

	public void registrarTerreno() {

		try {

			if (txtNombreTerreno.getValue() == null || txtNombreTerreno.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre del Terreno no puede est�r vac�o!");

			String nombreTerreno = txtNombreTerreno.getValue().toString().trim();

			Terreno nuevoTerreno = new Terreno();
			nuevoTerreno.setNombreterr(nombreTerreno);

			String mensaje = delegadoDeNegocio.registrarTerreno(nuevoTerreno);

			itemsTerrenos = null;

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}
	}

	public void eliminarTerreno() {

		try {
			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terrenoABorrar = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String mensaje = delegadoDeNegocio.eliminarTerreno(terrenoABorrar);

			itemsTerrenos = null;

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actualizarDatosTerreno() {

		try {

			if (txtNombreTerreno.getValue() == null || txtNombreTerreno.getValue().toString().trim().equals(""))
				throw new Exception("El nuevo nombre no puede est�r vac�o!");

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			String nombreTerreno = menuTerrenos.getValue().toString();
			String nuevoNombre = txtNombreTerreno.getValue().toString().trim();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String msje = delegadoDeNegocio.actualizarNombreTerreno(terreno, nuevoNombre);

			itemsTerrenos = null;

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void registrarLote() {

		try {
			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (txtNombreLote.getValue() == null || txtNombreLote.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre del Lote no puede est�r vac�o!");

			if (txtDescripcionLote.getValue() == null || txtDescripcionLote.getValue().toString().trim().equals(""))
				throw new Exception("La descripci�n del Lote no puede est�r vac�a!");

			String descripcionlote = txtDescripcionLote.getValue().toString();

			String nombreLote = txtNombreLote.getValue().toString().trim();

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			Lote nuevoLote = new Lote();
			nuevoLote.setDescripcionlote(descripcionlote);
			nuevoLote.setNombrelote(nombreLote);
			nuevoLote.setTerreno(terreno);

			String mensaje = delegadoDeNegocio.registrarLote(nuevoLote);

			cargarLotesDelTerreno();

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cargarLotesDelTerreno2() {

		try {

			if (!menuTerrenosParaLote.getValue().toString().equals("-1")) {

				String nombreTerreno = menuTerrenosParaLote.getValue().toString();

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

	public void cargarLotesDelTerreno() {

		try {

			if (!menuTerrenos.getValue().toString().equals("-1")) {

				String nombreTerreno = menuTerrenos.getValue().toString();

				List<Lote> lotes = delegadoDeNegocio.darLotes(nombreTerreno);

				itemsLotes = new ArrayList<SelectItem>();

				for (Lote loteActual : lotes) {

					SelectItem selectItem = new SelectItem(loteActual.getNombrelote(),
							loteActual.getNombrelote().trim());

					itemsLotes.add(selectItem);
				}
			}

			else {
				itemsLotes = null;

				if (txtNombreLote != null)
					txtNombreLote.resetValue();

				if (txtDescripcionLote != null)
					txtDescripcionLote.resetValue();
			}

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void eliminarLote() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			String mensaje = delegadoDeNegocio.eliminarLote(lote);

			cargarLotesDelTerreno();

			txtDescripcionLote.resetValue();
			txtNombreLote.resetValue();

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void actualizarDatosLote() {

		try {
			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			if (txtNombreLote.getValue() == null || txtNombreLote.getValue().toString().trim().equals(""))
				throw new Exception("El nuevo Nombre del Lote no puede est�r vac�o!");

			if (txtDescripcionLote.getValue() == null || txtDescripcionLote.getValue().toString().trim().equals(""))
				throw new Exception("La nueva Descripci�n del Lote no puede est�r vac�a!");

			String nombreTerreno = menuTerrenos.getValue().toString().trim();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			String nuevoNombre = txtNombreLote.getValue().toString().trim();
			String nuevaDescripcion = txtDescripcionLote.getValue().toString().trim();

			delegadoDeNegocio.actualizarNombreLote(lote, nuevoNombre);
			delegadoDeNegocio.actualizarDescripcionLote(lote, nuevaDescripcion);

			cargarLotesDelTerreno();

			generadorDialogos.desplegarMensajeExito("Datos del Lote Actualizados!");

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}
	}

	public void registrarPlanMuestreo() {

		try {

			if (txtCodigoPlanMuestreo.getValue() == null
					|| txtCodigoPlanMuestreo.getValue().toString().trim().equals(""))
				throw new Exception("El Codigo de la Estrategia no puede ser vac�o!");

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			Estrategiamuestreo nuevoPlan = new Estrategiamuestreo();

			String codigoEstrategia = txtCodigoPlanMuestreo.getValue().toString().trim();

			String nombreTerreno = menuTerrenos.getValue().toString().trim();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			nuevoPlan.setCodigoest(codigoEstrategia);
			nuevoPlan.setTerreno(terreno);

			String msje = delegadoDeNegocio.registrarEstrategiaMuestreo(nuevoPlan);

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void asignarCantidadPuntosALote() {

		try {
			if (menuTerrenosParaLote.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Plan de Muestreo!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			if (txtCantidadMuestrasRecolectadas.getValue() == null
					|| txtCantidadMuestrasRecolectadas.getValue().toString().trim().equals(""))
				throw new Exception("El N�mero de Puntos de Recolecci�n no puede ser vac�o!");

			String nombreTerreno = menuTerrenosParaLote.getValue().toString().trim();

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			String nombreLote = menuLotes.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			int cantidadPuntosRecoleccion = Integer
					.parseInt(txtCantidadMuestrasRecolectadas.getValue().toString().trim());

			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String msje = delegadoDeNegocio.actualizarCantidadPuntosRecoleccion(planMuestreo, lote,
					cantidadPuntosRecoleccion);

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (NumberFormatException e) {
			generadorDialogos.desplegarMensajeError(
					"El valor digitado en el campo " + "'N�mero Puntos Recolecci�n' no es valido!");
		}

		catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void eliminarPlanMuestreo() {

		try {

			if (menuTerrenosParaLote.getValue().toString().equals("-1"))

				throw new Exception("Debe seleccionar un Terreno!");

			if (menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Plan de Muestreo!");

			String nombreTerreno = menuTerrenosParaLote.getValue().toString().trim();

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String mensaje = delegadoDeNegocio.eliminarEstrategiaMuestreo(planMuestreo);

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void cargarEstrategiasDelTerreno2() {

		try {

			if (!menuTerrenosParaLote.getValue().toString().equals("-1")) {

				String nombreTerreno = menuTerrenosParaLote.getValue().toString();

				List<Estrategiamuestreo> planes = delegadoDeNegocio.darEstrategiasMuestro(nombreTerreno);

				itemsEstrategiasMuestreo = new ArrayList<SelectItem>();

				for (Estrategiamuestreo estActual : planes) {

					SelectItem selectItem = new SelectItem(estActual.getCodigoest(), estActual.getCodigoest());

					itemsEstrategiasMuestreo.add(selectItem);
				}

			} else {
				itemsEstrategiasMuestreo = null;
			}

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError("Error: " + e.getMessage() + "!");

			e.printStackTrace();
		}

	}

	public void cargarEstrategiasDelTerreno() {

		try {

			String nombreTerreno = menuTerrenos.getValue().toString();

			List<Estrategiamuestreo> planes = delegadoDeNegocio.darEstrategiasMuestro(nombreTerreno);

			itemsEstrategiasMuestreo = new ArrayList<SelectItem>();

			for (Estrategiamuestreo estActual : planes) {

				SelectItem selectItem = new SelectItem(estActual.getCodigoest(), estActual.getCodigoest());

				itemsEstrategiasMuestreo.add(selectItem);
			}

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError("Error: " + e.getMessage() + "!");

			e.printStackTrace();
		}

	}

	public void cargarInfoLote() {

		if (!menuLotes.getValue().toString().equals("-1")) {

			try {

				String nombreTerreno = menuTerrenos.getValue().toString();

				Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

				String nombreLote = menuLotes.getValue().toString();

				Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

				txtNombreLote.setValue(lote.getNombrelote());
				txtDescripcionLote.setValue(lote.getDescripcionlote());

			} catch (Exception e) {

				generadorDialogos.desplegarMensajeError(e.getMessage());
				e.printStackTrace();
			}

		} else {
			txtNombreLote.resetValue();
			txtDescripcionLote.resetValue();
		}

	}

	public void consultarPlanMuestreoLote() {
		try {
			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Plan de Muestreo!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			String nombreTerreno = menuTerrenos.getValue().toString().trim();

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			datosMuestreo = delegadoDeNegocio.darPuntosDeRecoleccion(planMuestreo, lote);

			// TODO Para las fotos!

			int cantidadPuntos = delegadoDeNegocio.darCanticadPuntosRecoleccion(lote, planMuestreo);

			txtCantidadMuestrasRecolectadas.setValue(cantidadPuntos + " submuestras!");

			generadorDialogos.desplegarMensajeExito("Plan de Muestreo Importado!");

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
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

				txtCantidadMuestrasRecolectadas.setValue(numPuntos);

				generadorDialogos.desplegarMensajeExito("Analisis de Suelos Cargado!");
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

	public InputText getTxtNombreLote() {
		return txtNombreLote;
	}

	public void setTxtNombreLote(InputText txtNombreLote) {
		this.txtNombreLote = txtNombreLote;
	}

	public InputText getTxtDescripcionLote() {
		return txtDescripcionLote;
	}

	public void setTxtDescripcionLote(InputText txtDescripcionLote) {
		this.txtDescripcionLote = txtDescripcionLote;
	}

	public CommandButton getBtnRegistrarLote() {
		return btnRegistrarLote;
	}

	public void setBtnRegistrarLote(CommandButton btnRegistrarLote) {
		this.btnRegistrarLote = btnRegistrarLote;
	}

	public CommandButton getBtnModificarLote() {
		return btnModificarLote;
	}

	public void setBtnModificarLote(CommandButton btnModificarLote) {
		this.btnModificarLote = btnModificarLote;
	}

	public CommandButton getBtnEliminarLote() {
		return btnEliminarLote;
	}

	public void setBtnEliminarLote(CommandButton btnEliminarLote) {
		this.btnEliminarLote = btnEliminarLote;
	}

	public InputText getTxtNombreTerreno() {
		return txtNombreTerreno;
	}

	public void setTxtNombreTerreno(InputText txtNombreTerreno) {
		this.txtNombreTerreno = txtNombreTerreno;
	}

	public CommandButton getBtnRegistrarTerreno() {
		return btnRegistrarTerreno;
	}

	public void setBtnRegistrarTerreno(CommandButton btnRegistrarTerreno) {
		this.btnRegistrarTerreno = btnRegistrarTerreno;
	}

	public CommandButton getBtnModificarTerreno() {
		return btnModificarTerreno;
	}

	public void setBtnModificarTerreno(CommandButton btnModificarTerreno) {
		this.btnModificarTerreno = btnModificarTerreno;
	}

	public CommandButton getBtnEliminarTerreno() {
		return btnEliminarTerreno;
	}

	public void setBtnEliminarTerreno(CommandButton btnEliminarTerreno) {
		this.btnEliminarTerreno = btnEliminarTerreno;
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

	public CommandButton getBtnConsultarPlanMuestreo() {
		return btnConsultarPlanMuestreo;
	}

	public void setBtnConsultarPlanMuestreo(CommandButton btnConsultarPlanMuestreo) {
		this.btnConsultarPlanMuestreo = btnConsultarPlanMuestreo;
	}

	public InputText getTxtCantidadMuestrasRecolectadas() {
		return txtCantidadMuestrasRecolectadas;
	}

	public void setTxtCantidadMuestrasRecolectadas(InputText txtCantidadMuestrasRecolectadas) {
		this.txtCantidadMuestrasRecolectadas = txtCantidadMuestrasRecolectadas;
	}

	public CommandButton getBtnConsultarReporteLaboratorio() {
		return btnConsultarReporteLaboratorio;
	}

	public void setBtnConsultarReporteLaboratorio(CommandButton btnConsultarReporteLaboratorio) {
		this.btnConsultarReporteLaboratorio = btnConsultarReporteLaboratorio;
	}

	public List<MapeoMedicionLAB> getMedidasLaboratorio() {
		return medidasLaboratorio;
	}

	public void setMedidasLaboratorio(List<MapeoMedicionLAB> medidasLaboratorio) {
		this.medidasLaboratorio = medidasLaboratorio;
	}

	public SelectOneMenu getMenuTerrenosParaLote() {
		return menuTerrenosParaLote;
	}

	public void setMenuTerrenosParaLote(SelectOneMenu menuTerrenosParaLote) {
		this.menuTerrenosParaLote = menuTerrenosParaLote;
	}

	public List<SelectItem> getItemsTerrenosParaLote() {

		if (itemsTerrenosParaLote == null) {

			List<Terreno> terrenos = delegadoDeNegocio.darTodosLosTerrenos();

			itemsTerrenosParaLote = new ArrayList<SelectItem>();

			for (Terreno terrActual : terrenos) {

				SelectItem selectItem = new SelectItem(terrActual.getNombreterr(), terrActual.getNombreterr());

				itemsTerrenosParaLote.add(selectItem);
			}
		}

		return itemsTerrenosParaLote;
	}

	public void setItemsTerrenosParaLote(List<SelectItem> itemsTerrenosParaLote) {
		this.itemsTerrenosParaLote = itemsTerrenosParaLote;
	}

	public InputText getTxtCodigoPlanMuestreo() {
		return txtCodigoPlanMuestreo;
	}

	public void setTxtCodigoPlanMuestreo(InputText txtCodigoPlanMuestreo) {
		this.txtCodigoPlanMuestreo = txtCodigoPlanMuestreo;
	}

	public CommandButton getBtnRegistrarEstrategia() {
		return btnRegistrarEstrategia;
	}

	public void setBtnRegistrarEstrategia(CommandButton btnRegistrarEstrategia) {
		this.btnRegistrarEstrategia = btnRegistrarEstrategia;
	}

	public CommandButton getBtnAsignarPuntosALote() {
		return btnAsignarPuntosALote;
	}

	public void setBtnAsignarPuntosALote(CommandButton btnAsignarPuntosALote) {
		this.btnAsignarPuntosALote = btnAsignarPuntosALote;
	}

	public CommandButton getBtnEliminarEstrategia() {
		return btnEliminarEstrategia;
	}

	public void setBtnEliminarEstrategia(CommandButton btnEliminarEstrategia) {
		this.btnEliminarEstrategia = btnEliminarEstrategia;
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

	public List<Puntorecoleccion> getDatosMuestreo() {
		return datosMuestreo;
	}

	public void setDatosMuestreo(List<Puntorecoleccion> datosMuestreo) {
		this.datosMuestreo = datosMuestreo;
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
