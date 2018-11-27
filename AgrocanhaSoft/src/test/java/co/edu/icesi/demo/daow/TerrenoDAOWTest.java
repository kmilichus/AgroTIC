package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITerrenoDAO;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TerrenoDAOWTest {
	
	@Autowired
	private ITerrenoDAOW terrenoDAOW;
	
	@Autowired
	private ITerrenoDAO terrenoDAO;
	
	@Test
	@Transactional(readOnly = true)
	public void darLotes() {
	
		assertNotNull(terrenoDAO);
		assertNotNull(terrenoDAOW);
		
		BigDecimal idTerreno = new BigDecimal(10);
		
		Terreno terreno = new Terreno();
		terreno.setTerrid(idTerreno);
		
		
		List<Lote> lotes= terrenoDAOW.darLotes(terreno);
		
		for (Lote lote : lotes) {
			System.out.println(lote.getLoteid());
		}
		
	}

	@Test
	@Transactional(readOnly = true)
	public void buscarTerreno() {
		
		assertNotNull(terrenoDAOW);
		
		String nombreTerreno = "AA";
		//long idEsperado = 4L;
		
		Terreno terreno = terrenoDAOW.buscarTerreno(nombreTerreno);
		
		assertNull(terreno);
		
		// long idReal = terreno.getTerrid();
		
		//assertEquals(idEsperado, idReal);
	}

	@Test
	@Transactional(readOnly = true)
	public void darEstrategiasMuestreo() {
		
		assertNotNull(terrenoDAO);
		assertNotNull(terrenoDAOW);

		BigDecimal idTerreno = new BigDecimal(4);
		
		Terreno terreno = terrenoDAO.findById(idTerreno);
		
		List<Estrategiamuestreo> planes = 
				terrenoDAOW.darEstrategiasMuestreo(terreno);
		
		for (Estrategiamuestreo estrategiamuestreo : planes) {
			System.out.println(estrategiamuestreo.getPlanmid());
		}
		
	}

}
