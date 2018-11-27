package interfaz;

import mundo.AnalisisDeSuelo;
import mundo.CalculadoraRequerimientos;
import mundo.ExtractoIntercambiable;

public class Main {
	
	
	private CalculadoraRequerimientos calculadora;
	
	
	public Main(){
		
		calculadora = new CalculadoraRequerimientos();
	
		escenario1();
//		escenario2();
//		escenario3();
//		escenario4();
//		escenario5();

	}
	
	
	

	
	
	private void escenario3() {
		System.out.println("Escenario Prueba 3");
		AnalisisDeSuelo analisis = new AnalisisDeSuelo(7.97, 0.65, 8, 36, 56,0.7);
		ExtractoIntercambiable ei = new ExtractoIntercambiable(0, 0.23, 4.85, 13.8, 0.8, 0.16, 3.21, 13.1, 5.63, 1.19);
		analisis.setFaseIntercambiable(ei);
		calculadora.setActual(analisis);
		calculadora.diagnostico();
		
	}
	
	private void escenario2() {
		System.out.println("Escenario Prueba 2");

		AnalisisDeSuelo analisis = new AnalisisDeSuelo(5.8, 0.11, 36.8, 24.0, 39.2,0.3);
		ExtractoIntercambiable ei = new ExtractoIntercambiable(0, 1.22, 30.12, 65.85, 2.8, 0.08, 46.78, 33.75, 5.98, 1.33);
		analisis.setFaseIntercambiable(ei);
		calculadora.setActual(analisis);
		calculadora.diagnostico();
	}

	private void escenario1() {
		System.out.println("Escenario Prueba 1");

		AnalisisDeSuelo analisis = new AnalisisDeSuelo(6.97, 0.65, 30, 29, 40,0.2);		
		ExtractoIntercambiable ei = new ExtractoIntercambiable(0, 1.22, 30.12, 65.85, 2.8, 0.08, 46.78, 33.75, 5.98, 1.33);
		analisis.setFaseIntercambiable(ei);
		calculadora.setActual(analisis);
		calculadora.diagnostico();
		
		
	}
	
	private void escenario4() {
		System.out.println("Escenario Prueba 4");
//		new AnalisisDeSuelo(ph, conductividadElectrica, arcilla, limo, arena)
		AnalisisDeSuelo analisis = new AnalisisDeSuelo(6.7, 0.3, 41.0, 27.6, 31.4,1);
		ExtractoIntercambiable ei = new ExtractoIntercambiable(0.5, 1.06, 12.04, 24.8, 0.29, 0.21, 0.191, 102.7, 0.58, 3.6);
		analisis.setFaseIntercambiable(ei);
		calculadora.setActual(analisis);
		calculadora.diagnostico();
		
	}

	private void escenario5() {
		System.out.println("Escenario Prueba 5");
		AnalisisDeSuelo analisis = new AnalisisDeSuelo(7.3, 0.2, 31.0, 37.6, 31.4,0.65);
		ExtractoIntercambiable ei = new ExtractoIntercambiable(0.2, 1.65, 6.37, 18.44, 0.26, 0.25, 0.191, 100.1, 0.24, 3);
		analisis.setFaseIntercambiable(ei);
		calculadora.setActual(analisis);
		calculadora.diagnostico();
		
	}

	public static void main(String[] args) {		
		Main m = new Main();
	}

}
