package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Faseaplicada;

public interface IFaseDAOW {
	
	public List<Faseaplicada> darTodasLasFases();

	public Faseaplicada darFase(String nombreFaseAplicada);

	public Clasificaciontextura darTipoTextura(String tipoTextura);
	

}
