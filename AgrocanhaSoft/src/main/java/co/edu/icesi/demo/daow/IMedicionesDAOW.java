package co.edu.icesi.demo.daow;

import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Medicionelemento;
import co.edu.icesi.demo.modelo.Medicionmetricas;
import co.edu.icesi.demo.modelo.Metrica;

public interface IMedicionesDAOW {

	public Medicionmetricas
	darMedidaMetrica(Estrategiamuestreo planMuestreo, Lote lote, Metrica metrica);

	
	public Medicionelemento
	darMedidaElementos(Estrategiamuestreo planMuestreo,
			Lote lote, Elemento elemento);

}
