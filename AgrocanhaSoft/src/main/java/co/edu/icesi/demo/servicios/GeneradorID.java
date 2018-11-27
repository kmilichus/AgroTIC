package co.edu.icesi.demo.servicios;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class GeneradorID implements IGeneradorID{
	
	public BigDecimal generarID(){
		
		Calendar calendario = Calendar.getInstance();

		String año = calendario.get(Calendar.YEAR) + "";
		año = año.substring(2, 4);

		String mes = (calendario.get(Calendar.MONTH) + 1) + "";
		String dia = calendario.get(Calendar.DATE) + "";

		String hora = calendario.get(Calendar.HOUR_OF_DAY) + "";
		String minuto = calendario.get(Calendar.MINUTE) + "";
		String segundos = calendario.get(Calendar.SECOND) + "";

		String tiempoEje = System.currentTimeMillis() + "";
		tiempoEje = tiempoEje.substring(tiempoEje.length() - 4, tiempoEje.length());

		String idUsuario = año + mes + dia + hora + minuto + segundos + tiempoEje;

		return new BigDecimal(idUsuario);
		
}

}
