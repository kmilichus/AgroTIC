package co.edu.icesi.demo.daow;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.demo.modelo.Fotografia;

public interface IFotografiaDAOW {
	
	public List<Fotografia> darTodasFotos();

	public Fotografia darFotografia(BigDecimal id);

}
