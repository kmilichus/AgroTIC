package mundo;

public class ExtractoSoluble {

	private double calcio;
	
	private double Magnesio;
	
	private double potasio;
	
	private double sodio;
	
	private double RAS;
	
	

	public ExtractoSoluble(double calcio, double magnesio, double potasio, double sodio, double rAS) {
		super();
		this.calcio = calcio;
		Magnesio = magnesio;
		this.potasio = potasio;
		this.sodio = sodio;
		RAS = rAS;
	}

	public double getCalcio() {
		return calcio;
	}

	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}

	public double getMagnesio() {
		return Magnesio;
	}

	public void setMagnesio(double magnesio) {
		Magnesio = magnesio;
	}

	public double getPotasio() {
		return potasio;
	}

	public void setPotasio(double potasio) {
		this.potasio = potasio;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public double getRAS() {
		return RAS;
	}

	public void setRAS(double rAS) {
		RAS = rAS;
	}
	
	
	
	
}
