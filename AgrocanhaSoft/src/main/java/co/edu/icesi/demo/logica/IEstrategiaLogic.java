package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Fotografia;
import co.edu.icesi.demo.modelo.Informelaboratorio;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;
import co.edu.icesi.demo.modelo.Terreno;

public interface IEstrategiaLogic {
	
	public String registrarEstrategiaMuestreo(Estrategiamuestreo nEstrategiaMuestreo)
			throws Exception;

	public Estrategiamuestreo buscarEstrategiaMuestreo(Terreno terreno, String codigoEstrategia)
			throws Exception;

	public String actualizarCantidadPuntosRecoleccion(
			Estrategiamuestreo planMuestreo, Lote lote, int cantidadPuntosRecoleccion)
					throws Exception;

	public List<Estrategiamuestreo> darEstrategiasMuestro(String nombreTerreno)
			throws Exception;

	public String registrarPuntoRecoleccion(Puntorecoleccion nPuntoRecoleccion,
			Lote lote, Estrategiamuestreo planMuestreo, byte[] bytesFotografia)
			throws Exception;

	public int darCanticadPuntosRecoleccion(Lote lote, Estrategiamuestreo planMuestreo)
			throws Exception;
	
	public String eliminarEstrategiaMuestreo(Estrategiamuestreo plan) throws Exception;

	public List<Puntorecoleccion> darPuntosDeRecoleccion(Estrategiamuestreo planMuestreo, Lote lote)
	throws Exception;

	public Informelaboratorio darInformeLaboratorio(Estrategiamuestreo plan);

	public List<Fotografia> darFotosPuntosRecoleccion();
	
	public List<Puntorecoleccion> darTodospuntosRecoleccion();
	
	public Fotografia darFotografia(BigDecimal id);


}
