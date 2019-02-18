package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JOptionPane;

public class CalculadoraRequerimientos {

	public static final String ARCHIVO_SINTOMAS = "./data/datos.dat";

	public static final String ARCHIVO_ENMIENDAS = "./data/datos2.dat";

	private LinkedList<AnalisisDeSuelo> listadoAnalisis;

	private LinkedList<Sintoma> listadoTodosLosSintomasFisicos;

	private LinkedList<Sintoma> listadoTodosLosSintomasQuimicos;

	private LinkedList<Enmienda> listadoEnmiendas;

	private LinkedList<Enmienda> listadoWarnings;

	private AnalisisDeSuelo actual;
	
	private boolean requiereSoluble;

	public CalculadoraRequerimientos() {


		listadoEnmiendas= new LinkedList<>();
		listadoTodosLosSintomasFisicos = new LinkedList<>();
		listadoTodosLosSintomasQuimicos = new LinkedList<>();
		listadoEnmiendas = new LinkedList<>();
		listadoWarnings = new LinkedList<>();

		inicializarPVI();


		listadoAnalisis = new LinkedList<>();

		//TODO temp 	
		actual =new AnalisisDeSuelo(7, 0.3, 32, 35, 33,0.5);
		requiereSoluble = false;
	}

	private void inicializarPVI() {

		//Cargando sintomas
		cargarSintomasFisicos(ARCHIVO_SINTOMAS);
		cargarSintomasQuimicos(ARCHIVO_SINTOMAS);


		cargarEnmiendas(ARCHIVO_ENMIENDAS);

	}

	public LinkedList<AnalisisDeSuelo> getListadoAnalisis() {
		return listadoAnalisis;
	}

	public void setListadoAnalisis(LinkedList<AnalisisDeSuelo> listadoAnalisis) {
		this.listadoAnalisis = listadoAnalisis;
	}

	private void cargarSintomasFisicos( String archivo ){

		try{
			FileInputStream fis = new FileInputStream( new File( archivo ) );
			Properties propiedades = new Properties( );
			propiedades.load( fis );


			int id;
			String descripcion;
			String dato = "total.diagnostico.fisico";
			String aux = propiedades.getProperty( dato );
			int cantidad = Integer.parseInt( aux );

			for( int cont = 1; cont <= cantidad; cont++ ){
				// Carga un sintoma

				id = cont;

				dato = "diagnostico.fisico." + cont;
				descripcion = propiedades.getProperty( dato );


				// Sólo se carga si los datos son correctos
				if( descripcion != null){

					Sintoma actual = new Sintoma(id, descripcion);
					listadoTodosLosSintomasFisicos.addLast(actual);
				}
				fis.close( );
			}
		}
		catch( FileNotFoundException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
		catch( IOException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	private  void cargarSintomasQuimicos( String archivo ){

		try{
			FileInputStream fis = new FileInputStream( new File( archivo ) );
			Properties propiedades = new Properties( );
			propiedades.load( fis );


			int id;
			String descripcion;
			String dato = "total.diagnostico.quimico";
			String aux = propiedades.getProperty( dato );
			int cantidad = Integer.parseInt( aux );

			for( int cont = 1; cont <= cantidad; cont++ ){
				// Carga un sintoma

				id = cont;

				dato = "diagnostico.quimico." + cont;
				descripcion = propiedades.getProperty( dato );


				// Sólo se carga si los datos son correctos
				if( descripcion != null){

					Sintoma actual = new Sintoma(id, descripcion);
					listadoTodosLosSintomasQuimicos.addLast(actual);
				}
				fis.close( );
			}
		}
		catch( FileNotFoundException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
		catch( IOException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}


	private  void cargarEnmiendas( String archivo ){

		try{
			FileInputStream fis = new FileInputStream( new File( archivo ) );
			Properties propiedades = new Properties( );
			propiedades.load( fis );


			int id;
			String nombre;
			String dato = "total.enmiendas.fisica";
			String aux = propiedades.getProperty( dato );
			int cantidad = Integer.parseInt( aux );

			for( int cont = 1; cont <= cantidad; cont++ ){
				// Carga una enmienda fisica

				id = cont;

				dato = "enmienda.fisica." + cont;
				nombre = propiedades.getProperty( dato );


				// Sólo se carga si los datos son correctos
				if( nombre != null){

					Enmienda actual = new Enmienda(nombre);
					listadoEnmiendas.addLast(actual);
				}

			}


			dato = "total.enmiendas.qumica";
			aux = propiedades.getProperty( dato );
			cantidad = Integer.parseInt( aux );

			for( int cont = 1; cont <= cantidad; cont++ ){
				// Carga una enmienda quimica

				id = cont;

				dato = "enmienda.quimica." + cont;
				nombre = propiedades.getProperty( dato );


				// Sólo se carga si los datos son correctos
				if( nombre != null){

					Enmienda actual = new Enmienda(nombre);
					listadoEnmiendas.addLast(actual);
				}
				fis.close( );
			}


			dato = "total.warnings";
			aux = propiedades.getProperty( dato );
			cantidad = Integer.parseInt( aux );

			for( int cont = 1; cont <= cantidad; cont++ ){
				// Carga una enmienda quimica

				id = cont;

				dato = "warning." + cont;
				nombre = propiedades.getProperty( dato );


				// Sólo se carga si los datos son correctos
				if( nombre != null){

					Enmienda actual = new Enmienda(nombre);
					listadoWarnings.addLast(actual);
				}
				fis.close( );
			}





		}
		catch( FileNotFoundException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
		catch( IOException e )
		{
			JOptionPane.showMessageDialog( null, "Problemas al cargar la información", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}



	public void clasificarAnalisis(){

		//REGLAS

		//RULE 1
		if (actual.getPh()>6.8 && actual.getArcilla()>40) {

			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(1));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(3));

		}

		//RULE 2
		if (actual.getPh()>6.8 && actual.getLimo()>45) {

			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(1));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(4));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(5));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(8));
		}

		//RULE 3
		if (actual.getPh()>6.8 && actual.getLimo()<40 && actual.getArcilla()<40 && actual.getArena()<40) {

			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(1));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(4));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(5));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(8));
		}


		//RULE 4
		if (actual.getPh()<6.8 && actual.getPh()>6.2 && actual.getLimo()<40 && actual.getArcilla()<40 && actual.getArena()<40) {

			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));

		}



	}

	public LinkedList<Sintoma> getListadoTodosLosSintomasFisicos() {
		return listadoTodosLosSintomasFisicos;
	}

	public void setListadoTodosLosSintomasFisicos(LinkedList<Sintoma> listadoTodosLosSintomasFisicos) {
		this.listadoTodosLosSintomasFisicos = listadoTodosLosSintomasFisicos;
	}

	public LinkedList<Sintoma> getListadoTodosLosSintomasQuimicos() {
		return listadoTodosLosSintomasQuimicos;
	}

	public void setListadoTodosLosSintomasQuimicos(LinkedList<Sintoma> listadoTodosLosSintomasQuimicos) {
		this.listadoTodosLosSintomasQuimicos = listadoTodosLosSintomasQuimicos;
	}

	public LinkedList<Enmienda> getListadoEnmiendas() {
		return listadoEnmiendas;
	}

	public void setListadoEnmiendas(LinkedList<Enmienda> listadoEnmiendas) {
		this.listadoEnmiendas = listadoEnmiendas;
	}

	public LinkedList<Enmienda> getListadoWarnings() {
		return listadoWarnings;
	}

	public void setListadoWarnings(LinkedList<Enmienda> listadoWarnings) {
		this.listadoWarnings = listadoWarnings;
	}

	public AnalisisDeSuelo getActual() {
		return actual;
	}

	public void setActual(AnalisisDeSuelo actual) {
		this.actual = actual;
	}




	public boolean revisionWarnings(){

		boolean problem = false;

		if(actual.getArena()>50){
			System.err.println(listadoWarnings.get(0));
			problem = true;
		}
		return problem;
	}





	public void diagnostico() {



		if (actual!=null && !revisionWarnings()) {

			System.out.println("*****************CALCULANDO DIAGNOSTICO *********************");

			ruleRunner();

			mostrarDiagnosticos();

			if(actual.getSintomasQuimicos().size()!=0 || actual.getSintomasFisicos().size()!=0){
				calcularEnmiendas();
			}
			
			calcularCationesIntercambiables();
			if (requiereSoluble) {				
				calcularSolubles();
			}else {
				System.out.println("\n************** No requiere Fase Soluble *********************");
			}
			
			
			vectorRequerimientos();
			

		}

	}


	private void vectorRequerimientos() {
	
		System.out.println("\n************** Vector Requerimientos meq/100 *****************");
		System.out.println(actual);
		System.out.println("Los calculos estan dados en meq/100g");
	}

	private void calcularSolubles() {
		
//		System.out.println("\n***************** Calculo de Fase Soluble *********************");
		
		ExtractoSoluble faseSoluble = new ExtractoSoluble(1.16, 1.95, 0.6, 2.39, 1);
		actual.setFaseSoluble(faseSoluble);
	}

	private void calcularCationesIntercambiables() {
		
//		System.out.println("\n***************** Calculo de Fase Intercambiable *********************");	
	
		
	}

	private void ruleRunner() {
	
		Thread t = new Thread(new ThreadDemo());
		
		t.run();

		if(actual.getPh()>6.8 && actual.getPh()<7.2){
			
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(1));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(3));

			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('A'-'A'));
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('C'-'A'));
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('H'-'A'));
//			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('I'-'A'));
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('J'-'A'));

			requiereSoluble = true;
			
		} else if(actual.getPh() >=5.6 && actual.getPh()<6.2){	
			//ligramente acido
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(1));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));

			
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('M'-'A'));
			
		} else if(actual.getPh()>7.2){	
			requiereSoluble=true;
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('D'-'A'));
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('E'-'A'));
		}else if(actual.getPh() >=6.2 && actual.getPh()<6.8){	
			// neutro
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(0));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(2));

			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('M'-'A'));
		}else if(actual.getPh() >=5 && actual.getPh()<5.6){

			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(6));
			actual.agregarSintomaFisico(listadoTodosLosSintomasFisicos.get(8));
			
			actual.agregarSintomaQuimico(listadoTodosLosSintomasQuimicos.get('L'-'A'));
			
		}
	}

	public void mostrarDiagnosticos(){

		System.out.println("Diagnostico Fisico: \n");
		if(actual.getSintomasFisicos().size()!=0){
			for (Sintoma s : actual.getSintomasFisicos()) {
				System.out.println("-> "+s.getDescripcion());
			}
		}else {
			System.out.println("Este suelo no presenta un diagnostico fisico visible\n");
		}

		System.out.println("\nDiagnostico Quimico: \n");
		if(actual.getSintomasQuimicos().size()!=0){
			for (Sintoma s : actual.getSintomasQuimicos()) {
				System.out.println("-> "+s.getDescripcion());
			}
		}else {
			System.out.println("Este suelo no presenta un diagnostico quimico visible\n");
		}

	}


	private void calcularEnmiendas() {		
		System.out.println("\n*****************CALCULANDO ENMIENDAS *********************\n");		

		//		System.out.println(listadoEnmiendas);

		actual.agregarEnmineda(listadoEnmiendas.get(0));
		actual.agregarEnmineda(listadoEnmiendas.get(1));
		actual.agregarEnmineda(listadoEnmiendas.get(2));
		actual.agregarEnmineda(listadoEnmiendas.get(4));
		actual.agregarEnmineda(listadoEnmiendas.get(12));
		actual.agregarEnmineda(listadoEnmiendas.get(17));

		mostrarEnmiendas();

	}

	private void mostrarEnmiendas() {
		//--> Evitar el desequilibrio entre la relación calcio magnesio

		for (Enmienda e : actual.getEnmiendas()) {
			System.out.println("+ "+e.toString());
		}

		System.out.println("\nNotas: \n");
		System.out.println("* Evitar el desequilibrio entre la relación calcio magnesio");
		System.out.println("* El encalado consiste en incorporar al suelo calcio y magnesio para neutralizar la acidez del mismo, es decir para que el pH alcance un nivel ideal para el desarrollo normal de los cultivos y al mismo tiempo reduzca el contenido del aluminio y manganeso tóxico.");
	}
	
	


	public class ThreadDemo implements Runnable {

		Thread t;

		public void run() {

				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					System.out.println(e);
				}
			
		}


	}
}
