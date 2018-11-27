package co.edu.icesi.demo.daow;

import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rangometricas;

public interface IRangoMetricaDAOW {

	public Rangometricas darClaMetrica(Metrica m, double valor );

	public String darValoresExtremos(Metrica metrica);
	
	
}
