package co.edu.icesi.demo.servicios;


public interface IAdministradorCadenas {
	
	public boolean soloContieneLetrasYEspacios(String cadena);
	
	public boolean soloContieneNumeros(String cadena);

	public boolean longitudPasswordValida(String password);
	
	public boolean tieneFormatoDecimal (String cadena);

}
