package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITerrenoDAO;
import co.edu.icesi.demo.modelo.Estrategiamuestreo;
import co.edu.icesi.demo.modelo.Terreno;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class EstrategiaMuestreoDAOWTest {

	@Autowired
	private IEstrategiaMuestreoDAOW planDAOW;
	
	@Autowired
	private ITerrenoDAO terrenoDAO;
	
	@Test
	@Transactional(readOnly = true)
	public void buscarEstrategiaMuestreo() {

		BigDecimal idTerreno = new BigDecimal(1);
		String codigoPlan = "sfasfas";
		//long idEsperado = 3L;
		
		Terreno terreno = terrenoDAO.findById(idTerreno);

		Estrategiamuestreo plan = planDAOW.buscarEstrategiaMuestreo(terreno, codigoPlan);
		
		assertNull(plan);
		
		/**long idReal = plan.getPlanmid();
	
		assertEquals(idEsperado, idReal);**/
		
	}
		

}
