package mundo;

public class Sintoma implements Comparable{
	
	
	public final static int BAJA=10;
	
	public final static int MEDIA=20;
	
	public final static int ALTA=30;
	
	public final static String FISICO_Y_BIOLOGICO= "Diagnostico Fisico y biologico";
	
	public final static String  QUIMICO ="Diagnostico Quimico";
	
	
	private int id;

	private String descripcion;
	
	private int intensidad;
	
	private String tipo;
	
	

	public Sintoma(int id, String descripcion, int inte) {

		this.id = id;
		this.descripcion = descripcion;
		intensidad = inte;
	}
	
	public Sintoma(int id, String descripcion) {

		this.id = id;
		this.descripcion = descripcion;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(int intensidad) {
		this.intensidad = intensidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public int compareTo(Object arg0) {
		
		Sintoma s = (Sintoma) arg0;
		
		int resta = Math.abs(s.getId()-id);

		return resta;
	}
	
	@Override
	public boolean equals(Object obj) {
		Sintoma s = (Sintoma) obj;
		return s.getId()==id;
	}

	

	

}
