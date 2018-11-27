package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.EstrategiaLote;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;

public interface IEstrategiaLoteDAOW {

	public EstrategiaLote darEstrategiaLote(Estrategiamuestreo plan, Lote lote);
	
	public int darTotalEstrategiasLotes();

	public List<Puntorecoleccion> darPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo);

	public void eliminarEstrategiaLote(Estrategiamuestreo plan, Lote loteActual);
	
}
