package co.edu.icesi.demo.daow;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.modelo.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UsuarioDAOWTest {
	
	@Autowired
	private IUsuarioDAOW usuarioDAOW;
	
	@Test
	@Transactional(readOnly = true)
	public void buscarUsuarioPorCedula() {
		
		String cedula = "1144191672";
		//long idEsperado = 1L;
		
		Usuario usuario = usuarioDAOW.buscarUsuarioPorCedula(cedula);
		
		assertNotNull(usuario);
		
		/**long idReal = usuario.getUsuid();
		
		assertEquals(idEsperado, idReal);**/

	}

}
