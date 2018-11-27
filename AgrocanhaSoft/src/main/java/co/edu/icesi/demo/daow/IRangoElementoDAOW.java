package co.edu.icesi.demo.daow;

import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Rangoelementos;


public interface IRangoElementoDAOW {

	public Rangoelementos darClaElemento(Elemento e, double valor );

	public String darValoresExtremosPPM(Elemento elemento);

}
