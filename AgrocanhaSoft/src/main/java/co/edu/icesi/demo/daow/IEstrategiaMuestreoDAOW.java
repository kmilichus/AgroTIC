package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;

public interface IEstrategiaMuestreoDAOW {

	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia);

	public List<Puntorecoleccion> darPuntosRecoleccion();

}
