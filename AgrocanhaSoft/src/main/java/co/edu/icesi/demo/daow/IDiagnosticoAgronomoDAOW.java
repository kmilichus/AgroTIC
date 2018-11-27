package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Zonaagroecologica;

public interface IDiagnosticoAgronomoDAOW {

	public Diagnosticoagronomo consultarDiagnosticoAgronomo(String codigoEstrategiaMuestreo);

	public List<Diagnosticoagronomo> darTodosLosDiagnosticos();

	public Zonaagroecologica consultarZonaDiagnostico(Diagnosticoagronomo diagnostico);

	public Diagnosticoagronomo darDiagnosticoDelPlan(Estrategiamuestreo plan);

}
