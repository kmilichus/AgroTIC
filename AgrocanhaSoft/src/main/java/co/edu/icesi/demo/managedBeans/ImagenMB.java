package co.edu.icesi.demo.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.edu.icesi.demo.modelo.Fotografia;
import co.edu.icesi.demo.modelo.Puntorecoleccion;

@ManagedBean
@ApplicationScoped
public class ImagenMB {

	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private List<Fotografia> imagenes;
	private StreamedContent imagen;

	private SelectOneMenu menuPuntos;
	private List<SelectItem> itemMenuPuntos;

	public SelectOneMenu getMenuPuntos() {
		return menuPuntos;
	}

	public void setMenuPuntos(SelectOneMenu menuPuntos) {
		this.menuPuntos = menuPuntos;
	}

	public List<SelectItem> getItemMenuPuntos() {

		itemMenuPuntos = new ArrayList<SelectItem>();

		List<Puntorecoleccion> puntosR = delegadoDeNegocio.darTodospuntosRecoleccion();

		for (Puntorecoleccion puntoActual : puntosR) {

			SelectItem selectItem = new SelectItem(puntoActual.getPuntoid(), puntoActual.getPuntoid() + "");

			itemMenuPuntos.add(selectItem);

		}
		return itemMenuPuntos;
	}

	public void setItemMenuPuntos(List<SelectItem> itemMenuPuntos) {
		this.itemMenuPuntos = itemMenuPuntos;
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	public StreamedContent getImagen() {

		if (menuPuntos != null && menuPuntos.getValue() != null &&
				!menuPuntos.getValue().equals("-1")) {

			String idPunto = menuPuntos.getValue().toString();

			BigDecimal id = new BigDecimal(idPunto);
				
			Fotografia foto = delegadoDeNegocio.darFotografia(id);
			
			
			System.out.println("Foto Existe!");
			byte[] info = foto.getFotografia();
			
			InputStream input = new ByteArrayInputStream(info);
			
			imagen = new DefaultStreamedContent(input, "image/jpg");
		}

		else {
			
			System.out.println("Foto Defecto!");
			InputStream input = new ByteArrayInputStream(delegadoDeNegocio.darFotos().get(1).getFotografia());
			imagen = new DefaultStreamedContent(input, "image/jpg");
			
		}
		
		return imagen;
	}

	public List<Fotografia> getImagenes() {

		imagenes = delegadoDeNegocio.darFotos();

		return imagenes;
	}

	public void setImagenes(List<Fotografia> imagenes) {
		this.imagenes = imagenes;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

}
