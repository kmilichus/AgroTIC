package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

public interface ITerrenoDAOW {

	public List<Terreno> darTodosLosTerrenos();
	
	public List<Lote> darLotes(Terreno terreno);

	public Terreno buscarTerreno(String nombreTerreno);

	public List<Estrategiamuestreo> darEstrategiasMuestreo(Terreno terreno);
		
}
