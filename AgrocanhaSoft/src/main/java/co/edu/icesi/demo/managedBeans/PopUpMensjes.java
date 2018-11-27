package co.edu.icesi.demo.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("popUpMensjes")
public class PopUpMensjes implements IPopUpMensjes{

	@Override
	public void desplegarMensajeError(String mensaje) {

		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
		
	}

	@Override
	public void desplegarMensajeExito(String mensaje) {
		
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
		
	}

}
