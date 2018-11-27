package mundo;

public class Enmienda implements Comparable {
	
	private String nombre;
	
	private String descripcion;
	
	
	public Enmienda(String nombre) {
		this.nombre = nombre;
	}

	public Enmienda(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Object o) {
		
		Enmienda e = (Enmienda) o;
		
		return e.getNombre().compareTo(nombre);
	}
	
	
	

}
