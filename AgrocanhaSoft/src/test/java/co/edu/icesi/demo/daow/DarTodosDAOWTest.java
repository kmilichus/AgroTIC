package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.modelo.Clasificaciontextura;
import co.edu.icesi.demo.modelo.Elemento;
import co.edu.icesi.demo.modelo.Faseaplicada;
import co.edu.icesi.demo.modelo.Metodologia;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rol;
import co.edu.icesi.demo.modelo.Terreno;
import co.edu.icesi.demo.modelo.Zonaagroecologica;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DarTodosDAOWTest {

	@Autowired
	private IElementoDAOW elementoDAOW;
	
	@Autowired
	private IFaseDAOW faseDAOW;

	@Autowired
	private IMetodologiaDAOW metodologiaDAOW;

	@Autowired
	private IMetricaDAOW metricaDAOW;

	@Autowired
	private IRolDAOW rolDAOW;

	@Autowired
	private ITerrenoDAOW terrenoDAOW;

	@Autowired
	private ITipoTexturaDAOW texturaDAOW;

	@Autowired
	private IZonaAgroecologicaDAOW zonaAgroDAOW;
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarElementos() {
		
		assertNotNull(elementoDAOW);
		
		System.out.println("Elementos:");

		List<Elemento> elementos = elementoDAOW.darTodosLosElementos();

		for (Elemento elementoActual : elementos) {
			System.out.println("id: " + elementoActual.getElemid());
			System.out.println("nombre: " + elementoActual.getNombreelem());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarFases() {
		
		assertNotNull(elementoDAOW);
		
		System.out.println("Fases Utilizadas:");

		List<Faseaplicada> fases = faseDAOW.darTodasLasFases();

		for (Faseaplicada actual : fases) {
			System.out.println("id: " + actual.getFaseid());
			System.out.println("nombre: " + actual.getNombrefase());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarMetodologias() {
		
		assertNotNull(metodologiaDAOW);
		
		System.out.println("Metodologías");

		List<Metodologia> metodologias = metodologiaDAOW.darTodasLasMetodologias();

		for (Metodologia actual : metodologias) {
			System.out.println("id: " + actual.getMetoid());
			System.out.println("nombre: " + actual.getNombremet());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarMetricas() {
		
		assertNotNull(metricaDAOW);
		
		System.out.println("Metricas:");

		List<Metrica> metricas = metricaDAOW.darTodasLasMetricas();

		for (Metrica actual : metricas) {
			System.out.println("id: " + actual.getMetrid());
			System.out.println("nombre: " + actual.getNombremet());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarRoles() {
		
		assertNotNull(rolDAOW);
		
		System.out.println("Roles");

		List<Rol> roles = rolDAOW.darTodosLosRoles();

		for (Rol actual : roles) {
			System.out.println("id: " + actual.getRolid());
			System.out.println("nombre: " + actual.getNombrerol());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarTerrenos() {
		
		assertNotNull(terrenoDAOW);
		
		System.out.println("Terrenos Registrados:");

		List<Terreno> terrenos = terrenoDAOW.darTodosLosTerrenos();

		for (Terreno actual : terrenos) {
			System.out.println("id: " + actual.getTerrid());
			System.out.println("nombre: " + actual.getNombreterr());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarTexturas() {
		
		assertNotNull(texturaDAOW);
		
		System.out.println("Texturas:");

		List<Clasificaciontextura> texturas = texturaDAOW.darTodosLosTiposTextura();

		for (Clasificaciontextura actual : texturas) {
			System.out.println("id: " + actual.getClatexid());
			System.out.println("nombre: " + actual.getNombrecla());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarZonasAgroecologicas() {
		
		assertNotNull(zonaAgroDAOW);
		
		System.out.println("Zonas Agroecologicas");

		List<Zonaagroecologica> zonasAgro = zonaAgroDAOW.darTodasLasZonasAgroecologicas();

		for (Zonaagroecologica actual : zonasAgro) {
			System.out.println("id: " + actual.getZonaagroid());
			System.out.println("nombre: " + actual.getCodigoznagro());
		}
		
		System.out.println("");
	}
	

}
