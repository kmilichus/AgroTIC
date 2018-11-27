package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertEquals;
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

import co.edu.icesi.demo.dao.IEstrategiaMuestreoDAO;
import co.edu.icesi.demo.dao.ILoteDAO;
import co.edu.icesi.demo.modelo.EstrategiaLote;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Puntorecoleccion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class EstrategiaLoteDAOWTest {

	@Autowired
	private IEstrategiaLoteDAOW estrategiaLoteDAOW;
	
	@Autowired
	private IEstrategiaMuestreoDAO planDAO;
	
	@Autowired
	private ILoteDAO loteDAO;

	@Test
	@Transactional(readOnly = true)
	public void aDarEstrategiaLoteTest() {
		
		BigDecimal idPlan = new BigDecimal(6); //  1   + 2   + 3 + 4    + 5,6
		BigDecimal idLote = new BigDecimal(7); // 1,2 + 3,6 + 7 + 8,10 + 11,13
		//long idEsperado = 1L;
		
		assertNotNull(estrategiaLoteDAOW);
		assertNotNull(planDAO);
		assertNotNull(loteDAO);
		
		Estrategiamuestreo plan = planDAO.findById(idPlan);
		Lote lote = loteDAO.findById(idLote);
		
		EstrategiaLote resultado = estrategiaLoteDAOW.darEstrategiaLote(plan, lote);
				
		
		assertNull(resultado);
		
		/**long idReal = resultado.getEstrategialoteid();
				
		assertEquals(idEsperado, idReal);**/
		
	}

	@Test
	@Transactional(readOnly = true)
	public void bDarTotalEstrategiasLotesTest() {
		
		int resultadoEsperado = 0;
		
		assertNotNull(estrategiaLoteDAOW);
		
		int resultadoReal = estrategiaLoteDAOW.darTotalEstrategiasLotes();
		
		assertEquals(resultadoEsperado, resultadoReal);
	}

	@Test
	@Transactional(readOnly = true)
	public void cDarPuntosRecoleccionTest() {

		BigDecimal idPlan = new BigDecimal(2);
		BigDecimal idLote = new BigDecimal(6);
		
		assertNotNull(estrategiaLoteDAOW);
		assertNotNull(planDAO);
		assertNotNull(loteDAO);
		
		Estrategiamuestreo plan = planDAO.findById(idPlan);
		Lote lote = new Lote();
		
		lote.setLoteid(idLote);
		
		List<Puntorecoleccion> puntos = estrategiaLoteDAOW.darPuntosRecoleccion(lote, plan);
		
		String real = "";
		
		for (Puntorecoleccion puntorecoleccion : puntos) {
			real = real + puntorecoleccion.getPuntoid() + "-";
		}
		
		System.out.println("Puntos: " + real);
		
	}
	

}
