package co.edu.icesi.demo.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;

@ManagedBean
@ViewScoped
public class PuntosMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	@ManagedProperty(value = "#{popUpMensjes}")
	private IPopUpMensjes generadorDialogos;

	@ManagedProperty(value = "#{redireccionadorURL}")
	private IRedireccionador redireccionadorURL;

	private String URLMenu;

	private SelectOneMenu menuTerrenos;
	private List<SelectItem> itemsTerrenos;

	private SelectOneMenu menuLotes;
	private List<SelectItem> itemsLotes;

	private SelectOneMenu menuEstrategiasMuestreo;
	private List<SelectItem> itemsEstrategiasMuestreo;

	private InputText txtAltitud;

	private InputText txtLatitud;

	private UploadedFile fileCargado;

	public UploadedFile getFileCargado() {
		return fileCargado;
	}

	public void setFileCargado(UploadedFile fileCargado) {
		this.fileCargado = fileCargado;
	}

	private byte[] bytes;

	private FileUpload fileUpload;

	public void registrarPuntoRecoleccion() {

		try {

			if (menuTerrenos.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Terreno!");

			if (menuLotes.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Lote!");

			if (menuEstrategiasMuestreo.getValue().toString().equals("-1"))
				throw new Exception("Debe seleccionar un Plan de Muestreo!");

			if (txtAltitud.getValue().toString().trim().equals("")) {
				throw new Exception("El Campo 'Altitud' no puede estár vacío!");
			}

			if (txtLatitud.getValue().toString().trim().equals("")) {
				throw new Exception("El Campo 'Latitud' no puede estár vacío!");
			}

			if (fileCargado == null) {
				throw new Exception("Debe subir una foto, primero!");
			}
			
			if (bytes == null){
				throw new Exception("Debe subir una foto, primero!");
			}

			double altitud = Double.parseDouble(txtAltitud.getValue().toString().trim());
			double latitud = Double.parseDouble(txtLatitud.getValue().toString().trim());

			Puntorecoleccion nPuntoRecoleccion = new Puntorecoleccion();
			nPuntoRecoleccion.setAltitud(altitud);
			nPuntoRecoleccion.setLatitud(latitud);

			String nombreTerreno = menuTerrenos.getValue().toString();
			String codigoEstrategia = menuEstrategiasMuestreo.getValue().toString();

			Terreno terreno = delegadoDeNegocio.buscarTerreno(nombreTerreno);

			Estrategiamuestreo plan = delegadoDeNegocio.buscarEstrategiaMuestreo(terreno, codigoEstrategia);

			String nombreLote = menuLotes.getValue().toString();

			Lote lote = delegadoDeNegocio.buscarLote(terreno, nombreLote);
						
			String rta = delegadoDeNegocio.registrarPuntoRecoleccion(nPuntoRecoleccion, lote, plan
					, bytes);

			generadorDialogos.desplegarMensajeExito(rta);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			generadorDialogos
					.desplegarMensajeError("Los campos 'Latitud' y 'Longitud'" + " deben ser números decimales!");
		}

		catch (Exception e) {
			e.printStackTrace();
			generadorDialogos.desplegarMensajeError(e.getMessage());
		}

	}

	public void cargarFotografia(FileUploadEvent event) {

		try {

			if (event.getFile() == null) {
				throw new Exception("Debe seleccionar una fotografía");
			}

			fileCargado = event.getFile();
			bytes = fileCargado.getContents();

			String msg = "La imagen '" + fileCargado.getFileName() + "' fue subida correctamente.";

			generadorDialogos.desplegarMensajeExito(msg);

		} catch (Exception e) {
			e.printStackTrace();
			generadorDialogos.desplegarMensajeError(e.getMessage());
		}
	}

	public UploadedFile getFile() {
		return fileCargado;
	}

	public void setFile(UploadedFile file) {
		this.fileCargado = file;
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

	public InputText getTxtAltitud() {
		return txtAltitud;
	}

	public void setTxtAltitud(InputText txtAltitud) {
		this.txtAltitud = txtAltitud;
	}

	public InputText getTxtLatitud() {
		return txtLatitud;
	}

	public void setTxtLatitud(InputText txtLatitud) {
		this.txtLatitud = txtLatitud;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
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

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public void cargarEstrategiasDelTerreno() {

		try {

			if (!menuTerrenos.getValue().toString().equals("-1")) {

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
			}

		} catch (Exception e) {
			generadorDialogos.desplegarMensajeError(e.getMessage());

			e.printStackTrace();
		}

	}

}
