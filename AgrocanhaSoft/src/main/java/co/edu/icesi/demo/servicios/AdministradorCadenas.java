package co.edu.icesi.demo.servicios;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AdministradorCadenas implements IAdministradorCadenas {

	public static int LONGITUD_MINIMA_PASSWORD = 6;
	public static int LONGITUD_MAXIMA_PASSWORD = 10;

	@Override
	public boolean soloContieneLetrasYEspacios(String cadena) {

		boolean otroCaracterEncontrado = false;

		for (int i = 0; i < cadena.length() && !otroCaracterEncontrado; i++) {

			char carActual = cadena.charAt(i);

			if (!Character.isLetter(carActual) && carActual != ' ')
				otroCaracterEncontrado = true;

		}

		return !otroCaracterEncontrado;

	}

	@Override
	public boolean soloContieneNumeros(String cadena) {
		
		boolean otroCaracterEncontrado = false;

		for (int i = 0; i < cadena.length() && !otroCaracterEncontrado; i++) {

			char carActual = cadena.charAt(i);

			if (!Character.isDigit(carActual)) {
				otroCaracterEncontrado = true;
			}

		}

		return !otroCaracterEncontrado;

	}

	@Override
	public boolean longitudPasswordValida(String password) {

		if (password.length() >= LONGITUD_MINIMA_PASSWORD && password.length() <= LONGITUD_MAXIMA_PASSWORD)
			return true;

		else
			return false;

	}

	@Override
	public boolean tieneFormatoDecimal(String cadena) {

		String copia = "" + cadena;

		boolean tieneFormato=true;
		
		if(copia.charAt(0) =='-'){
			copia = copia.substring(1);
		}
		
		String[] componentes = copia.split(",");

		if (componentes.length > 2)
			tieneFormato=false;		
		
		for (int i = 0; i < componentes.length && tieneFormato; i++) {

			String subCadena = componentes[i];

			if(!soloContieneNumeros(subCadena))
				tieneFormato=false;
		}

		return tieneFormato;

	}

}
