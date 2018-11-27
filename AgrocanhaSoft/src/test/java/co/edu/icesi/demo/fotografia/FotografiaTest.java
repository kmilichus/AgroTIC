package co.edu.icesi.demo.fotografia;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.daow.IFotografiaDAOW;
import co.edu.icesi.demo.modelo.Fotografia;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class FotografiaTest {

	@Autowired
	private IFotografiaDAOW fotografiaDAOW;

	@Test
	@Transactional(readOnly = true)
	public void imprimirFotos() {

		try {
			
			assertNotNull(fotografiaDAOW);

			List<Fotografia> fotos = fotografiaDAOW.darTodasFotos();
			
			for (Fotografia fotografiaActual : fotos) {

				File newFile = new File("D:\\" + System.currentTimeMillis() + ".jpg");

				byte[] info = fotografiaActual.getFotografia();

				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(info));
				ImageIO.write(imag, "jpg", newFile);

				System.out.println("Imagen Exportada!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
