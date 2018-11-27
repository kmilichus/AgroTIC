package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

public interface ITerrenoLogic {
	
	public Terreno buscarTerreno (String nombreTerreno) throws Exception;

	public String registrarTerreno (Terreno nuevoTerreno) throws Exception;
	
	public String eliminarTerreno (Terreno terrenoABorrar) throws Exception;
	
	public String actualizarNombreTerreno(Terreno terreno, String nuevoNombre)
			throws Exception;
	
	public String registrarLote(Lote nuevoLote) throws Exception;

	public Lote buscarLote(Terreno terreno, String nombreLote) throws Exception;

	public String actualizarNombreLote(Lote lote, String nuevoNombre)
			throws Exception;

	public List<Lote> darLotes(String nombreTerreno) throws Exception;
	
	public List<Terreno> darTodosLosTerrenos();

	public String eliminarLote(Lote lote) throws Exception;;

	public void actualizarDescripcionLote(Lote lote, String nuevaDescripcion)
			 throws Exception;
	

}
