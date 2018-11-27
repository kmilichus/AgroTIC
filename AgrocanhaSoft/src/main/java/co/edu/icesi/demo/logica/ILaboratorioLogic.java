package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.mapeo.MapeoMedicionLAB;
import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Faseaplicada;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Metodologia;
import co.edu.icesi.demo.modelo.Metrica;

public interface ILaboratorioLogic {

	public String registrarValorMetrica(Estrategiamuestreo planMuestreo, Lote lote,
			BigDecimal codigoMetrica,
			BigDecimal codigoMetodologia, double magnitudValor) throws Exception;

	public String registrarMedicionElemento(Estrategiamuestreo planMuestreo,
			Lote lote, BigDecimal codigoElemento,
			BigDecimal codigoMetodologia, double valorPPM, 
			double porcentajeSolubilidad) throws Exception;

	public String actualizarClasificacionTextura(Estrategiamuestreo planMuestreo, Lote lote, String tipoTextura)
			throws Exception;

	public String actualizarNombreLaboratorio(Estrategiamuestreo planMuestreo, String nombreLAB)
			throws Exception;

	public String actualizarNombreHacienda(Estrategiamuestreo planMuestreo, String nombreHacienda)
			throws Exception;

	public String actualizarFaseAplicada(Estrategiamuestreo planMuestreo, Lote lote, String nombreFaseAplicada)
			throws Exception;

	public List<MapeoMedicionLAB> darAnalisisLaboratorio
	(Estrategiamuestreo planMuestreo, Lote lote) throws Exception;

	public List<Elemento> darTodosLosElementos();

	public List<Faseaplicada> darTodasLasFases();

	public List<Metodologia> darTodasLasMetodologias();

	public List<Metrica> darTodasLasMetricas();

	public List<Clasificaciontextura> darTodosLosTiposTextura();

	public boolean usoFaseSoluble(Estrategiamuestreo planMuestreo, Lote lote);

}
