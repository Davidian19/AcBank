package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.mediators.CorrentistaMediators;

public class TesteCorrentistas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Correntista[] correntistas = {
				new Correntista("111", "Zorobabel"), 
				new Correntista("112", "Abilio"),
				new Correntista("113", "Claudio"),
				new Correntista("114", "Bruno"),
				new Correntista("115", "Marcelo"),
				new Correntista("116", "Dandara"),
				new Correntista("117", "Carlos"),
				new Correntista("118", "Amarildo")
				};
		CorrentistaMediators cm = new CorrentistaMediators();
		for (Correntista Correntista : correntistas) {
			cm.include(Correntista);
		}
		Correntista[] ord = cm.consultarTodosOrdenadoPorNome();
		for (Correntista Correntista : ord) {
			System.out.println(Correntista.getCorrentistName());
		}
	}

}
