package generador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;

import mundo.Sintoma;



public class formatter {
	
    public static final String ARCHIVO_INFO = "./data/datos.dat";
	
	private static LinkedList<Sintoma> sintomasFisicos;
	private static LinkedList<Sintoma> sintomasQuimicos;

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		out.write("Por favor ingresar el valor del ph:\n");
		out.flush();
		
		double ph = Double.parseDouble(in.readLine());
		
		
		out.write("Por favor ingresar el valor de la conductividad electrica:\n");
		out.flush();
				
		double ce = Double.parseDouble(in.readLine());
		
		out.write("Por favor el % de Arcilla del suelo: (en el formato ##.# - ej: 43.2)\n");
		out.flush();
		
		double arcilla = Double.parseDouble(in.readLine());
		
		out.write("Por favor el % de Arena del suelo: (en el formato ##.# - ej: 43.2)\n");
		out.flush();
		
		double arena = Double.parseDouble(in.readLine());

		
		out.write("Por favor el % de Limo del suelo: (en el formato ##.# - ej: 43.2)\n");
		out.flush();
		
		double limo = Double.parseDouble(in.readLine());

		out.write("Calculando ...\n");
		
		
		
		
		 loadData( ARCHIVO_INFO );
		
		
		 
		 testResults(ph, ce, arcilla, arena, limo);
		 
		 
		 
		
		
		in.close();
		out.close();

	}
	


	public static void loadData(String ruta){
		
		sintomasFisicos = new LinkedList<>();
		sintomasQuimicos = new LinkedList<>();

		
		cargarSintomasFisicos(ruta);
		cargarSintomasQuimicos(ruta);
		
		
	}
	
	
	private static void cargarSintomasFisicos( String archivo )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( archivo ) );
            Properties propiedades = new Properties( );
            propiedades.load( fis );

          
            int id;
            String descripcion;
            String dato = "total.diagnostico.fisico";
            String aux = propiedades.getProperty( dato );
            int cantidad = Integer.parseInt( aux );

            for( int cont = 1; cont <= cantidad; cont++ )
            {
                // Carga un sintoma
            	
                id = cont;

                dato = "diagnostico.fisico." + cont;
                descripcion = propiedades.getProperty( dato );

                
                // Sólo se carga si los datos son correctos
                if( descripcion != null){
                	
                	Sintoma actual = new Sintoma(id, descripcion);
                	sintomasFisicos.addLast(actual);
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
	
	private static void cargarSintomasQuimicos( String archivo )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( archivo ) );
            Properties propiedades = new Properties( );
            propiedades.load( fis );

          
            int id;
            String descripcion;
            String dato = "total.diagnostico.quimico";
            String aux = propiedades.getProperty( dato );
            int cantidad = Integer.parseInt( aux );

            for( int cont = 1; cont <= cantidad; cont++ )
            {
                // Carga un sintoma
            	
                id = cont;

                dato = "diagnostico.quimico." + cont;
                descripcion = propiedades.getProperty( dato );

                
                // Sólo se carga si los datos son correctos
                if( descripcion != null){
                	
                	Sintoma actual = new Sintoma(id, descripcion);
                	sintomasQuimicos.addLast(actual);
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
	
	
	private static void testResults(double ph, double ce, double arcilla, double arena, double limo) {

		System.out.println("El Análisis muestra los siguientes diagnosticos");
		
		
		Random ale = new Random();
		int pos = ale.nextInt(sintomasFisicos.size()-1);
		writeFisicos(1);
		writeFisicos(2);
		writeFisicos(pos);

		
		writeQuimicos(0);
		writeQuimicos(2);
		writeQuimicos(6);
		
	}



	private static void writeQuimicos(int i) {
		// TODO Auto-generated method stub
		System.out.println(sintomasQuimicos.get(i).getDescripcion());

	}



	private static void writeFisicos(int i) {
		
		System.out.println(sintomasFisicos.get(i).getDescripcion());
		
	}

}
