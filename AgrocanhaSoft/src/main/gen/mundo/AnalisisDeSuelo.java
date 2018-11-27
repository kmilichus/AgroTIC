package mundo;

import java.util.ArrayList;
import java.util.TreeSet;

public class AnalisisDeSuelo {
	
	private double ph;
	
	private double conductividadElectrica;
	
	private double arcilla;
	
	private double limo;
	
	private double arena;
	
	private double 	capacidadIntercambioCationico;
	
	private ExtractoSoluble faseSoluble;
	
	private ExtractoIntercambiable faseIntercambiable;
	
	private TreeSet<Sintoma> sintomasFisicos;
	
	private TreeSet<Sintoma> sintomasQuimicos;
	
	private TreeSet<Enmienda> enmiendas;

	public AnalisisDeSuelo(double ph, double conductividadElectrica, double arcilla, double limo, double arena, double CIC) {
		this.ph = ph;
		this.conductividadElectrica = conductividadElectrica;
		this.arcilla = arcilla;
		this.limo = limo;
		this.arena = arena;
		setCapacidadIntercambioCationico(CIC);
		
		sintomasFisicos = new TreeSet<>();
		sintomasQuimicos = new TreeSet<>();
		enmiendas = (new TreeSet<>());
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getConductividadElectrica() {
		return conductividadElectrica;
	}

	public void setConductividadElectrica(double conductividadElectrica) {
		this.conductividadElectrica = conductividadElectrica;
	}

	public double getArcilla() {
		return arcilla;
	}

	public void setArcilla(double arcilla) {
		this.arcilla = arcilla;
	}

	public double getLimo() {
		return limo;
	}

	public void setLimo(double limo) {
		this.limo = limo;
	}

	public double getArena() {
		return arena;
	}

	public void setArena(double arena) {
		this.arena = arena;
	}

	public ExtractoSoluble getFaseSoluble() {
		return faseSoluble;
	}

	public void setFaseSoluble(ExtractoSoluble faseSoluble) {
		this.faseSoluble = faseSoluble;
	}

	public ExtractoIntercambiable getFaseIntercambiable() {
		return faseIntercambiable;
	}

	public void setFaseIntercambiable(ExtractoIntercambiable faseIntercambiable) {
		this.faseIntercambiable = faseIntercambiable;
	}

	public TreeSet<Sintoma> getSintomasFisicos() {
		return sintomasFisicos;
	}

	public void setSintomasFisicos(TreeSet<Sintoma> sintomasFisicos) {
		this.sintomasFisicos = sintomasFisicos;
	}

	public TreeSet<Sintoma> getSintomasQuimicos() {
		return sintomasQuimicos;
	}

	public void setSintomasQuimicos(TreeSet<Sintoma> sintomasQuimicos) {
		this.sintomasQuimicos = sintomasQuimicos;
	}
	
	public boolean agregarSintomaFisico(Sintoma s){
		return sintomasFisicos.add(s);
	}
	
	public boolean agregarSintomaQuimico(Sintoma s){
		return sintomasQuimicos.add(s);
	}

	public TreeSet<Enmienda> getEnmiendas() {
		return enmiendas;
	}

	public void setEnmiendas(TreeSet<Enmienda> enmiendas) {
		this.enmiendas = enmiendas;
	}

	public boolean agregarEnmineda(Enmienda e){
		return enmiendas.add(e);
	}

	public double getCapacidadIntercambioCationico() {
		return capacidadIntercambioCationico;
	}

	public void setCapacidadIntercambioCationico(double capacidadIntercambioCationico) {
		this.capacidadIntercambioCationico = capacidadIntercambioCationico;
	}
	
	@Override
	public String toString() {
		return faseIntercambiable.toString();
	}


	
	
	
	
	
	
}
