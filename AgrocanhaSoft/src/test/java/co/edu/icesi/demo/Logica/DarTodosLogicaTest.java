package co.edu.icesi.demo.Logica;

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

import co.edu.icesi.demo.logica.IAccesoLogic;
import co.edu.icesi.demo.logica.IAgroecologicaLogic;
import co.edu.icesi.demo.logica.ILaboratorioLogic;
import co.edu.icesi.demo.logica.ITerrenoLogic;
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
public class DarTodosLogicaTest {
	
	@Autowired
	private IAccesoLogic accesoLogica;
	
	@Autowired
	private IAgroecologicaLogic agroecologicaLogic;

	@Autowired
	private ITerrenoLogic terrenoLogic;

	@Autowired
	private ILaboratorioLogic labLogic;
	
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarElementos() {
		
		assertNotNull(labLogic);
		
		System.out.println("Elementos:");

		List<Elemento> elementos = labLogic.darTodosLosElementos();

		for (Elemento elementoActual : elementos) {
			System.out.println("id: " + elementoActual.getElemid());
			System.out.println("nombre: " + elementoActual.getNombreelem());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarFases() {
		
		assertNotNull(labLogic);
		
		System.out.println("Fases Utilizadas:");

		List<Faseaplicada> fases = labLogic.darTodasLasFases();

		for (Faseaplicada actual : fases) {
			System.out.println("id: " + actual.getFaseid());
			System.out.println("nombre: " + actual.getNombrefase());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarMetodologias() {
		
		assertNotNull(labLogic);
		
		System.out.println("Metodologías");

		List<Metodologia> metodologias = labLogic.darTodasLasMetodologias();

		for (Metodologia actual : metodologias) {
			System.out.println("id: " + actual.getMetoid());
			System.out.println("nombre: " + actual.getNombremet());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarMetricas() {
		
		assertNotNull(labLogic);
		
		System.out.println("Metricas:");

		List<Metrica> metricas = labLogic.darTodasLasMetricas();

		for (Metrica actual : metricas) {
			System.out.println("id: " + actual.getMetrid());
			System.out.println("nombre: " + actual.getNombremet());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarRoles() {
		
		assertNotNull(accesoLogica);
		
		System.out.println("Roles");

		List<Rol> roles = accesoLogica.darTodosLosRoles();

		for (Rol actual : roles) {
			System.out.println("id: " + actual.getRolid());
			System.out.println("nombre: " + actual.getNombrerol());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarTerrenos() {
		
		assertNotNull(terrenoLogic);
		
		System.out.println("Terrenos Registrados:");

		List<Terreno> terrenos = terrenoLogic.darTodosLosTerrenos();

		for (Terreno actual : terrenos) {
			System.out.println("id: " + actual.getTerrid());
			System.out.println("nombre: " + actual.getNombreterr());
		}
		
		System.out.println("");
	}

	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarTexturas() {
		
		assertNotNull(labLogic);
		
		System.out.println("Texturas:");

		List<Clasificaciontextura> texturas = labLogic.darTodosLosTiposTextura();

		for (Clasificaciontextura actual : texturas) {
			System.out.println("id: " + actual.getClatexid());
			System.out.println("nombre: " + actual.getNombrecla());
		}
		
		System.out.println("");
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testConsultarZonasAgroecologicas() {
		
		assertNotNull(agroecologicaLogic);
		
		System.out.println("Zonas Agroecologicas");

		List<Zonaagroecologica> zonasAgro;
		
		try {
			zonasAgro = agroecologicaLogic.darTodasZonasAgroecologicas();
			
			for (Zonaagroecologica actual : zonasAgro) {
				System.out.println("id: " + actual.getZonaagroid());
				System.out.println("nombre: " + actual.getCodigoznagro());
			}
			
			System.out.println("");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	


}
