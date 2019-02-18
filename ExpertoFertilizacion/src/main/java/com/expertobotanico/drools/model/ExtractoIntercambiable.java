package mundo;

import java.text.DecimalFormat;

public class ExtractoIntercambiable {
	
	double[] rangoIdeal = {0.2,15,6,4,3.4,1,0.2, 0.4, 0.1,1.2,4,2.5};
	
	//Bases Intercambiables meq/100g
	
	private double potasio;
	
	private double 	magnesio;
	
	private double calcio;
	
	private double sodio;
	
	private double aluminio;
	

	//Menores mg/Kg
	
	private double boro;
	
	private double hierro;
	
	private double manganeso;
	
	private double cobre;
	
	private double zinc;

	public ExtractoIntercambiable(double aluminio, double potasio, double magnesio, double calcio, double sodio,
			double boro, double hierro, double manganeso, double cobre, double zinc) {
		super();
		this.aluminio = aluminio;
		this.potasio = potasio;
		this.magnesio = magnesio;
		this.calcio = calcio;
		this.sodio = sodio;
		this.boro = boro;
		this.hierro = hierro;
		this.manganeso = manganeso;
		this.cobre = cobre;
		this.zinc = zinc;
	}

	public double getAluminio() {
		return aluminio;
	}

	public void setAluminio(double aluminio) {
		this.aluminio = aluminio;
	}

	public double getPotasio() {
		return potasio;
	}

	public void setPotasio(double potasio) {
		this.potasio = potasio;
	}

	public double getMagnesio() {
		return magnesio;
	}

	public void setMagnesio(double magnesio) {
		this.magnesio = magnesio;
	}

	public double getCalcio() {
		return calcio;
	}

	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public double getBoro() {
		return boro;
	}

	public void setBoro(double boro) {
		this.boro = boro;
	}

	public double getHierro() {
		return hierro;
	}

	public void setHierro(double hierro) {
		this.hierro = hierro;
	}

	public double getManganeso() {
		return manganeso;
	}

	public void setManganeso(double manganeso) {
		this.manganeso = manganeso;
	}

	public double getCobre() {
		return cobre;
	}

	public void setCobre(double cobre) {
		this.cobre = cobre;
	}

	public double getZinc() {
		return zinc;
	}

	public void setZinc(double zinc) {
		this.zinc = zinc;
	}
	
	
	@Override
	public String toString() {
		

		double n = Math.abs((sodio+potasio)*0.75-rangoIdeal[10]);
		double k = Math.abs((potasio*sodio)/2-rangoIdeal[11]);
		double p = Math.abs(potasio-rangoIdeal[0]);
		double m = Math.abs(magnesio-rangoIdeal[1]);
		double c = Math.abs(calcio-rangoIdeal[2]);
		double s = Math.abs(sodio-rangoIdeal[3]);
		double al = Math.abs(aluminio-rangoIdeal[4]);
		double boro = Math.abs(this.boro-rangoIdeal[5]);
		double hierro = Math.abs(this.hierro-rangoIdeal[6]);
		double mn = Math.abs(magnesio-rangoIdeal[7]);
		double cu = Math.abs(cobre-rangoIdeal[8]);
		double z = Math.abs(zinc-rangoIdeal[9]);
		
		DecimalFormat df = new DecimalFormat("#.##");
	 
		return "Nitrogeno: "+df.format(n)+"Fosforo: "+df.format(p)+"Potasio: "+df.format(p)+"\n Magnesio: "+df.format(m)+"\n Calcio: "+df.format(c)+"\n Sodio: "+df.format(s)+"\n Aluminio: "+df.format(al)
				+"\n Boro: "+df.format(boro)+"\n Hierro: "+df.format(hierro)+"\n Manganeso: "+df.format(mn)+"\n Cobre: "+df.format(cu)+"\n Zinc: "+df.format(z) ;
	}
	
	
	

}
