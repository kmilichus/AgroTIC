package co.edu.icesi.demo.daow;

import java.util.List;

import co.edu.icesi.demo.modelo.Lote;
import co.edu.icesi.demo.modelo.Terreno;

public interface ILoteDAOW {

   public Lote buscarLote (Terreno t , String nombre);	
   
   public List<Lote> darTodosLosLotes();
}
