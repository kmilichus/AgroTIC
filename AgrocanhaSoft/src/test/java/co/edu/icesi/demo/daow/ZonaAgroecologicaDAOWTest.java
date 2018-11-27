package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.mapeo.MapeoCaracteristica;
import co.edu.icesi.demo.modelo.Familiatextural;
import co.edu.icesi.demo.modelo.Grupohomogeneosuelo;
import co.edu.icesi.demo.modelo.Grupohumedad;
import co.edu.icesi.demo.modelo.Grupotextural;
import co.edu.icesi.demo.modelo.Ingenio;
import co.edu.icesi.demo.modelo.NivelhumTipopermeaGrupohum;
import co.edu.icesi.demo.modelo.Zonaagroecologica;
import co.edu.icesi.demo.modelo.Zonavariedad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ZonaAgroecologicaDAOWTest {

	@Autowired
	private IZonaAgroecologicaDAOW zonaAgroDAOW;

	@Test
	@Transactional(readOnly = true)
	public void darZonaAgroecologica() {

		assertNotNull(zonaAgroDAOW);

		int grupoSuelo = 22;
		String grupoHumedad = "H2";
		String idEsperado = "22H2";

		Zonaagroecologica resultado = zonaAgroDAOW.darZonaAgroecologica(grupoSuelo, grupoHumedad);

		//assertNull(resultado);
		
		String idReal = resultado.getZonaagroid();

		System.out.println(idReal);
		
		assertEquals(idEsperado, idReal);

	}

	@Test
	@Transactional(readOnly = true)
	public void consultarIngenios() {

		assertNotNull(zonaAgroDAOW);

		String codigoznagro = "10H3";

		Zonaagroecologica nZonaAgroecologica = new Zonaagroecologica();
		nZonaAgroecologica.setZonaagroid(codigoznagro);

		List<Ingenio> ingenios = zonaAgroDAOW.consultarIngenios(nZonaAgroecologica);

		for (Ingenio ingenio : ingenios) {
			System.out.println("Ingenio: " + ingenio.getIngid());
		}

	}

	@Test
	@Transactional(readOnly = true)
	public void consultarZonasVariedad() {

		assertNotNull(zonaAgroDAOW);

		String codigoznagro = "10H3";

		Zonaagroecologica nZonaAgroecologica = new Zonaagroecologica();
		nZonaAgroecologica.setZonaagroid(codigoznagro);
		
		List<Zonavariedad> variedades = zonaAgroDAOW.consultarZonasVariedad(nZonaAgroecologica);
		
		for (Zonavariedad variedad : variedades) {
			System.out.println("Zona Variedad: " + variedad.getZonavarid());
		}
		
	}

	@Test
	@Transactional(readOnly = true)
	public void consultarFamiliasTexturales() {

		assertNotNull(zonaAgroDAOW);
		
		String codigoznagro = "33H4";

		Zonaagroecologica nZonaAgroecologica
		= zonaAgroDAOW.consultarZonaPorCodigo(codigoznagro);
		
		List<Familiatextural> familias = zonaAgroDAOW.consultarFamiliasTexturales(nZonaAgroecologica);
		
		for (Familiatextural fam : familias) {
			System.out.println("Familia: " + fam.getFamiliatexid());
		}
		
	}

	@Test
	@Transactional(readOnly = true)
	public void consultarZonaPorCodigo() {

		String codigoZona = "10H3";
		String idEsperado = "10H3";

		Zonaagroecologica resultado = zonaAgroDAOW.consultarZonaPorCodigo(codigoZona);

		//assertNull(resultado);
		
		String idReal = resultado.getZonaagroid();

		assertEquals(idEsperado, idReal);

	}

	@Test
	@Transactional(readOnly = true)
	public void consultarCaracteristicasZona() {

		assertNotNull(zonaAgroDAOW);
		
		String codigoznagro = "11H0";

		Zonaagroecologica zonaAgroecologica
		= zonaAgroDAOW.consultarZonaPorCodigo(codigoznagro);
		
		List<MapeoCaracteristica> cars= 
		zonaAgroDAOW.consultarCaracteristicasZona(zonaAgroecologica);
		
		for (MapeoCaracteristica mapeoCaracteristica : cars) {
			
			System.out.println("Tipo:" + mapeoCaracteristica.getNombreCaract()
			+" - " + mapeoCaracteristica.getDescripcionCaract());
			System.out.println("");
			
		}
	}

	@Test
	@Transactional(readOnly = true)
	public void consultarGruposTexturales() {

		assertNotNull(zonaAgroDAOW);
		
		String codigoznagro = "33H4";

		Zonaagroecologica nZonaAgroecologica
		= zonaAgroDAOW.consultarZonaPorCodigo(codigoznagro);
		
		List<Grupotextural> grupos = zonaAgroDAOW.
				consultarGruposTexturales(nZonaAgroecologica);
		
		for (Grupotextural g : grupos) {
			System.out.println("Familia: " + g.getGrupotextid());
		}
		
		
	}

	@Test
	@Transactional(readOnly = true)
	public void darGrupoHomogeneoSuelo() {
		
		String codigoznagro = "10H3";
		BigDecimal idEsperado = new BigDecimal(10);
		
		Zonaagroecologica zonaAgroecologica = new Zonaagroecologica();
		zonaAgroecologica.setZonaagroid(codigoznagro);

		assertNotNull(zonaAgroDAOW);
		
		Grupohomogeneosuelo resultado =
			zonaAgroDAOW.darGrupoHomogeneoSuelo(zonaAgroecologica);

		//assertNull(resultado);
		
		BigDecimal idReal = resultado.getGrhomosueloid();
		
		assertEquals(idEsperado, idReal);
		
		
	}

	@Test
	@Transactional(readOnly = true)
	public void darGrupoHumedad() {

		assertNotNull(zonaAgroDAOW);
		
		String codigoznagro = "10H3";
		BigDecimal idEsperado = new BigDecimal(4);
	
		Zonaagroecologica zonaAgroecologica = new Zonaagroecologica();
		zonaAgroecologica.setZonaagroid(codigoznagro);

		assertNotNull(zonaAgroDAOW);
		
		Grupohumedad resultado =
				zonaAgroDAOW.darGrupoHumedad(zonaAgroecologica);

		//assertNull(resultado);
		
		BigDecimal idReal = resultado.getGrupohumid();
		
		assertEquals(idEsperado, idReal);

	}

	@Test
	@Transactional(readOnly = true)
	public void darDatosHumedad() {

		assertNotNull(zonaAgroDAOW);
		
		String codigoznagro = "10H2";
		
		Zonaagroecologica nZonaAgroecologica
		= zonaAgroDAOW.consultarZonaPorCodigo(codigoznagro);
		
		List<NivelhumTipopermeaGrupohum> 
		resultado = zonaAgroDAOW.darDatosHumedad(nZonaAgroecologica);

		for (NivelhumTipopermeaGrupohum n : resultado) {
			System.out.println("Grupo Humedad: " + n.getGrupohumedad().getGrupohumid());
			System.out.println("Nivel Humedad: " + n.getNivelhumedad().getNivelhumid());
			System.out.println("Tipo Permeabilidad: " + n.getTipopermeabilidad().getTipoperid());
			System.out.println("");
		}
				
	}

}
