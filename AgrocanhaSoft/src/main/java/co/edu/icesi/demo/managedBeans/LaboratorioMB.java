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
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Faseaplicada;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Metodologia;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Terreno;

@ManagedBean
@ViewScoped
public class LaboratorioMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;

	private String URLMenu;

	private InputText txtNombreHacienda;

	private InputText txtNombreLaboratorio;

	private SelectOneMenu menuTerrenos;
	private List<SelectItem> itemsTerrenos;

	private SelectOneMenu menuEstrategiasMuestreo;
	private List<SelectItem> itemsEstrategiasMuestreo;

	private SelectOneMenu menuLotes;
	private List<SelectItem> itemsLotes;

	private SelectOneMenu menuMetricas;
	private List<SelectItem> itemsMetricas;

	private SelectOneMenu menuMetodologiasMetrica;
	private List<SelectItem> itemsMetodologiasMetrica;

	private InputText txtMagnitudMetrica;

	private CommandButton btnRegistrarDatoMetrica;

	private SelectOneMenu menuElementos;
	private List<SelectItem> itemsElementos;

	private SelectOneMenu menuMetodologiasElemento;
	private List<SelectItem> itemsMetodologiasElemento;

	private InputText txtPPM;

	private InputText txtPorcentajeSolubilidad;

	private CommandButton btnRegistrarDatoElemento;

	private SelectOneMenu menuFases;
	private List<SelectItem> itemsFases;

	private SelectOneMenu menuTiposTextura;
	private List<SelectItem> itemsTiposTextura;

	private CommandButton btnRegistrarDatosRestantes, btnRegistrarNombres;

	public void registrarNombres() {
		try {
			if (txtNombreHacienda.getValue() == null || txtNombreHacienda.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre de la Hacienda no puede ser vacío!");

			if (txtNombreLaboratorio.getValue() == null || txtNombreLaboratorio.getValue().toString().trim().equals(""))
				throw new Exception("El Nombre del Laboratorio no puede ser vacío!");

			if (menuTerrenos.getValue().toString().equals("-1")
					|| menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno y su respectiva Estrategia de Muestreo!");

			String nombreLAB = txtNombreLaboratorio.getValue().toString().trim();
			String nombreHacienda = txtNombreHacienda.getValue().toString().trim();

			String nombreTerreno = menuTerrenos.getValue().toString();
			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();
			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			delegadoDeNegocio.actualizarNombreLaboratorio(planMuestreo, nombreLAB);
			delegadoDeNegocio.actualizarNombreHacienda(planMuestreo, nombreHacienda);

			generadorDialogos.desplegarMensajeExito("Nombres del Laboratorio y Hacienda actualizados!");
		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cargarLotesDelTerreno() {

		try {

			if (!menuTerrenos.getValue().equals("-1")) {

				String nombreTerreno = menuTerrenos.getValue().toString();

				List<Lote> lotes = delegadoDeNegocio.darLotes(nombreTerreno);

				itemsLotes = new ArrayList<SelectItem>();

				for (Lote loteActual : lotes) {

					SelectItem selectItem = new SelectItem(loteActual.getNombrelote(), loteActual.getNombrelote());

					itemsLotes.add(selectItem);
				}
			}

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void cargarEstrategiasDelTerreno() {

		try {
			if (!menuTerrenos.getValue().equals("-1")) {
				String nombreTerreno = menuTerrenos.getValue().toString();

				List<Estrategiamuestreo> planes = delegadoDeNegocio.darEstrategiasMuestro(nombreTerreno);

				itemsEstrategiasMuestreo = new ArrayList<SelectItem>();

				for (Estrategiamuestreo estActual : planes) {

					SelectItem selectItem = new SelectItem(estActual.getCodigoest(), estActual.getCodigoest());

					itemsEstrategiasMuestreo.add(selectItem);
				}
			}

		} catch (Exception e) {

			generadorDialogos.desplegarMensajeError("Error: " + e.getMessage() + "!");

			e.printStackTrace();
		}

	}

	public void registrarMedicionMetrica() {

		try {

			if (menuMetodologiasMetrica.getValue().equals("-1") || menuMetricas.getValue().equals("-1"))
				throw new Exception("Debe seleccionar una Metrica y su respectiva Metodología primero!");

			if (txtMagnitudMetrica.getValue() == null || txtMagnitudMetrica.getValue().toString().trim().equals(""))
				throw new Exception("El Campo 'Magnitud/Medida' no puede estár vacío!");

			if (menuTerrenos.getValue().equals("-1") || menuEstrategiasMuestreo.getValue().equals("-1")
					|| menuLotes.getValue().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno y su respectivo Lote y Estrategia de Muestreo!");

			String valorMedida = txtMagnitudMetrica.getValue().toString().trim();

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			BigDecimal codigoMetrica = new BigDecimal(menuMetricas.getValue().toString());

			BigDecimal codigoMetodologia = new BigDecimal(menuMetodologiasMetrica.getValue().toString());

			double magnitudValor = Double.parseDouble(valorMedida);

			String mensaje = delegadoDeNegocio.registrarValorMetrica(planMuestreo, lote, codigoMetrica,
					codigoMetodologia, magnitudValor);

			generadorDialogos.desplegarMensajeExito(mensaje);

		} catch (NumberFormatException ex) {
			generadorDialogos.desplegarMensajeError("El Valor digitado en el campo 'Magnitud/Medida'" + " no es valdo");

			ex.printStackTrace();
		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void registrarMedicionElemento() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1")
					|| menuEstrategiasMuestreo.getValue().toString().equals("-1")
					|| menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno y su respectivo Lote y Estrategia de Muestreo!");

			if (menuMetodologiasElemento.getValue().toString().equals("-1")
					|| menuElementos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Elemento y su respectiva Metodología primero!");

			if (txtPPM.getValue() == null || txtPPM.getValue().toString().trim().equals(""))
				throw new Exception("El Campo 'Medida PPM' no puede estár vacío!");

			String nombreTerreno = menuTerrenos.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			BigDecimal codigoElemento = new BigDecimal(menuElementos.getValue().toString());

			BigDecimal codigoMetodologia = new BigDecimal(menuMetodologiasElemento.getValue().toString());

			double valorPPM = Double.parseDouble(txtPPM.getValue().toString().trim());

			boolean esSoluble = delegadoDeNegocio.usoFaseSoluble(planMuestreo, lote);

			double porcentajeSolubilidad;

			if (esSoluble)
				porcentajeSolubilidad = Double.parseDouble(txtPorcentajeSolubilidad.getValue().toString().trim());

			else
				porcentajeSolubilidad = 0.0;

			String msje = delegadoDeNegocio.registrarMedicionElemento(planMuestreo, lote, codigoElemento,
					codigoMetodologia, valorPPM, porcentajeSolubilidad);

			generadorDialogos.desplegarMensajeExito(msje);

		} catch (NumberFormatException e) {
			generadorDialogos.desplegarMensajeError(
					"El valor digitado en el campo" + " 'Valor PPM' o 'Porcentaje Solubilidad' no es valido! " + ""
							+ "(Nota: No incluir el Simbolo '%'!)");

			e.printStackTrace();
		}

		catch (Exception e) {

			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public void registrarClasificaciones() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1")
					|| menuEstrategiasMuestreo.getValue().toString().equals("-1")
					|| menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno y su respectivo Lote y Estrategia de Muestreo!");

			if (menuTiposTextura.getValue().toString().equals("-1"))
				throw new Exception("Seleccione un Tipo de Textura!");

			if (menuFases.getValue().toString().equals("-1"))
				throw new Exception("Seleccione la Fase Utilizada!");

			String nombreTerreno = menuTerrenos.getValue().toString();
			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();
			Estrategiamuestreo planMuestreo = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String nombreLote = menuLotes.getValue().toString();
			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);

			String tipoTextura = menuTiposTextura.getValue().toString();
			String nombreFaseAplicada = menuFases.getValue().toString();

			delegadoDeNegocio.actualizarClasificacionTextura(planMuestreo, lote, tipoTextura);
			delegadoDeNegocio.actualizarFaseAplicada(planMuestreo, lote, nombreFaseAplicada);

			generadorDialogos.desplegarMensajeExito("Tipo-Textura Y Fase Aplicada Actualizados!");

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

	public InputText getTxtNombreHacienda() {
		return txtNombreHacienda;
	}

	public void setTxtNombreHacienda(InputText txtNombreHacienda) {
		this.txtNombreHacienda = txtNombreHacienda;
	}

	public InputText getTxtNombreLaboratorio() {
		return txtNombreLaboratorio;
	}

	public void setTxtNombreLaboratorio(InputText txtNombreLaboratorio) {
		this.txtNombreLaboratorio = txtNombreLaboratorio;
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

	public SelectOneMenu getMenuMetricas() {
		return menuMetricas;
	}

	public void setMenuMetricas(SelectOneMenu menuMetricas) {
		this.menuMetricas = menuMetricas;
	}

	public List<SelectItem> getItemsMetricas() {

		if (itemsMetricas == null) {

			List<Metrica> metricas = delegadoDeNegocio.darTodasLasMetricas();

			itemsMetricas = new ArrayList<SelectItem>();

			for (Metrica metrActual : metricas) {

				SelectItem selectItem = new SelectItem(metrActual.getMetrid(), metrActual.getNombremet());

				itemsMetricas.add(selectItem);
			}
		}

		return itemsMetricas;
	}

	public void setItemsMetricas(List<SelectItem> itemsMetricas) {
		this.itemsMetricas = itemsMetricas;
	}

	public SelectOneMenu getMenuMetodologiasMetrica() {
		return menuMetodologiasMetrica;
	}

	public void setMenuMetodologiasMetrica(SelectOneMenu menuMetodologiasMetrica) {
		this.menuMetodologiasMetrica = menuMetodologiasMetrica;
	}

	public List<SelectItem> getItemsMetodologiasMetrica() {

		if (itemsMetodologiasMetrica == null) {

			List<Metodologia> metodologias = delegadoDeNegocio.darTodasLasMetodologias();

			itemsMetodologiasMetrica = new ArrayList<SelectItem>();

			for (Metodologia metActual : metodologias) {

				SelectItem selectItem = new SelectItem(metActual.getMetoid(), metActual.getNombremet());

				itemsMetodologiasMetrica.add(selectItem);
			}
		}

		return itemsMetodologiasMetrica;
	}

	public void setItemsMetodologiasMetrica(List<SelectItem> itemsMetodologiasMetrica) {
		this.itemsMetodologiasMetrica = itemsMetodologiasMetrica;
	}

	public InputText getTxtMagnitudMetrica() {
		return txtMagnitudMetrica;
	}

	public void setTxtMagnitudMetrica(InputText txtMagnitudMetrica) {
		this.txtMagnitudMetrica = txtMagnitudMetrica;
	}

	public CommandButton getBtnRegistrarDatoMetrica() {
		return btnRegistrarDatoMetrica;
	}

	public void setBtnRegistrarDatoMetrica(CommandButton btnRegistrarDatoMetrica) {
		this.btnRegistrarDatoMetrica = btnRegistrarDatoMetrica;
	}

	public SelectOneMenu getMenuElementos() {
		return menuElementos;
	}

	public void setMenuElementos(SelectOneMenu menuElementos) {
		this.menuElementos = menuElementos;
	}

	public List<SelectItem> getItemsElementos() {

		if (itemsElementos == null) {

			List<Elemento> elementos = delegadoDeNegocio.darTodosLosElementos();

			itemsElementos = new ArrayList<SelectItem>();

			for (Elemento eleActual : elementos) {

				SelectItem selectItem = new SelectItem(eleActual.getElemid(), eleActual.getNombreelem());

				itemsElementos.add(selectItem);
			}
		}

		return itemsElementos;
	}

	public void setItemsElementos(List<SelectItem> itemsElementos) {
		this.itemsElementos = itemsElementos;
	}

	public SelectOneMenu getMenuMetodologiasElemento() {
		return menuMetodologiasElemento;
	}

	public void setMenuMetodologiasElemento(SelectOneMenu menuMetodologiasElemento) {
		this.menuMetodologiasElemento = menuMetodologiasElemento;
	}

	public List<SelectItem> getItemsMetodologiasElemento() {

		if (itemsMetodologiasElemento == null) {

			List<Metodologia> metodologias = delegadoDeNegocio.darTodasLasMetodologias();

			itemsMetodologiasElemento = new ArrayList<SelectItem>();

			for (Metodologia metActual : metodologias) {

				SelectItem selectItem = new SelectItem(metActual.getMetoid(), metActual.getNombremet());

				itemsMetodologiasElemento.add(selectItem);
			}
		}

		return itemsMetodologiasElemento;
	}

	public void setItemsMetodologiasElemento(List<SelectItem> itemsMetodologiasElemento) {
		this.itemsMetodologiasElemento = itemsMetodologiasElemento;
	}

	public InputText getTxtPPM() {
		return txtPPM;
	}

	public void setTxtPPM(InputText txtPPM) {
		this.txtPPM = txtPPM;
	}

	public InputText getTxtPorcentajeSolubilidad() {
		return txtPorcentajeSolubilidad;
	}

	public void setTxtPorcentajeSolubilidad(InputText txtPorcentajeSolubilidad) {
		this.txtPorcentajeSolubilidad = txtPorcentajeSolubilidad;
	}

	public CommandButton getBtnRegistrarDatoElemento() {
		return btnRegistrarDatoElemento;
	}

	public void setBtnRegistrarDatoElemento(CommandButton btnRegistrarDatoElemento) {
		this.btnRegistrarDatoElemento = btnRegistrarDatoElemento;
	}

	public SelectOneMenu getMenuFases() {
		return menuFases;
	}

	public void setMenuFases(SelectOneMenu menuFases) {
		this.menuFases = menuFases;
	}

	public List<SelectItem> getItemsFases() {

		if (itemsFases == null) {

			List<Faseaplicada> fases = delegadoDeNegocio.darTodasLasFases();

			itemsFases = new ArrayList<SelectItem>();

			for (Faseaplicada faseActual : fases) {

				SelectItem selectItem = new SelectItem(faseActual.getNombrefase(), faseActual.getNombrefase());

				itemsFases.add(selectItem);
			}
		}

		return itemsFases;
	}

	public void setItemsFases(List<SelectItem> itemsFases) {
		this.itemsFases = itemsFases;
	}

	public SelectOneMenu getMenuTiposTextura() {
		return menuTiposTextura;
	}

	public void setMenuTiposTextura(SelectOneMenu menuTiposTextura) {
		this.menuTiposTextura = menuTiposTextura;
	}

	public List<SelectItem> getItemsTiposTextura() {

		if (itemsTiposTextura == null) {

			List<Clasificaciontextura> texturas = delegadoDeNegocio.darTodosLosTiposTextura();

			itemsTiposTextura = new ArrayList<SelectItem>();

			for (Clasificaciontextura texturaActual : texturas) {

				SelectItem selectItem = new SelectItem(texturaActual.getNombrecla(), texturaActual.getNombrecla());

				itemsTiposTextura.add(selectItem);
			}
		}

		return itemsTiposTextura;
	}

	public void setItemsTiposTextura(List<SelectItem> itemsTiposTextura) {
		this.itemsTiposTextura = itemsTiposTextura;
	}

	public CommandButton getBtnRegistrarDatosRestantes() {
		return btnRegistrarDatosRestantes;
	}

	public void setBtnRegistrarDatosRestantes(CommandButton btnRegistrarDatosRestantes) {
		this.btnRegistrarDatosRestantes = btnRegistrarDatosRestantes;
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

	public CommandButton getBtnRegistrarNombres() {
		return btnRegistrarNombres;
	}

	public void setBtnRegistrarNombres(CommandButton btnRegistrarNombres) {
		this.btnRegistrarNombres = btnRegistrarNombres;
	}

}
