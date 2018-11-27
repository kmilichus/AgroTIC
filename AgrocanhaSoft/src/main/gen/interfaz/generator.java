package interfaz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class generator {
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		System.out.println("total.enmiendas.qumica = 9");
		String linea = in.readLine();
		int i = 1;
		
		while (linea!=null) {
			
			System.out.println("warning."+i+"="+linea);
			
			linea= in.readLine();
			++i;
		}
		
		out.close();
		in.close();
		
	}

}
