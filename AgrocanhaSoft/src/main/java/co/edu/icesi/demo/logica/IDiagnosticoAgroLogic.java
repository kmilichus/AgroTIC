package co.edu.icesi.demo.logica;

import co.edu.icesi.demo.modelo.Diagnosticoagronomo;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.modelo.Usuario;
import co.edu.icesi.demo.modelo.Zonaagroecologica;

public interface IDiagnosticoAgroLogic {

	public String registrarDiagnostico(Diagnosticoagronomo diagnosticoAgronomo)
			throws Exception;

	public Diagnosticoagronomo darDiagnosticoDeEstrategia(
			Terreno terreno, String codigoPlanMuestreo) throws Exception;

	public Zonaagroecologica darZonaDiagnostico(Diagnosticoagronomo diagnostico)
	throws Exception;
	
	public Usuario consultarAgronomo(Diagnosticoagronomo diagnostico);

}
