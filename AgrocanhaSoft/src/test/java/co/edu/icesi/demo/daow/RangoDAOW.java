package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IMetricaDAO;
import co.edu.icesi.demo.modelo.Metrica;
import co.edu.icesi.demo.modelo.Rangometricas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RangoDAOW {

	@Autowired
	private IRangoMetricaDAOW rangoMetricaDAOW;
	
	@Autowired
	private IMetricaDAO metricaDAO;
	
	@Test
	@Transactional(readOnly = true)
	public void darRangoTest(){
		
		assertNotNull(rangoMetricaDAOW);
		assertNotNull(metricaDAO);
		
		BigDecimal idMetrica = new BigDecimal("2");
		String nombreEsperado = "MO Alto";
		
		Metrica metrica = metricaDAO.findById(idMetrica);
		
		double valor = 7.3;
		
		Rangometricas rangoMetrica =
				rangoMetricaDAOW.darClaMetrica(metrica, valor);
		
		//assertNull(rangoMetrica);
		
		assertEquals(nombreEsperado, rangoMetrica.getNombrerango());
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void darValoresExtremosTest(){
		
		assertNotNull(rangoMetricaDAOW);
		
		BigDecimal idMetrica = new BigDecimal("7");
		
		Metrica metrica = metricaDAO.findById(idMetrica);
		
		System.out.println(rangoMetricaDAOW.darValoresExtremos(metrica));
		
		
	}
		
}
