package br.com.cesarschool.poo.mediators;
import java.util.InputMismatchException;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.repositorios.CorrentistRepository;



public class CorrentistaMediators {
	
	private CorrentistRepository correntistRepository = CorrentistRepository.getInstancia();

	public boolean include(Correntista correntista) {
		return correntistRepository.incluir(correntista);
	}
	public boolean change(Correntista correntista) {
		return correntistRepository.change(correntista);
	}
	public Correntista searchCorrentistMediator(String cpf) {
		return correntistRepository.searchCorrentist(cpf);
	}
	public boolean delete(String cpf) {
		return correntistRepository.delete(cpf);
	}
	
	public Correntista[] consultarTodosOrdenadoPorNome() {
		Correntista[] todos = correntistRepository.buscarTodos();
		if (todos != null && todos.length > 0) {
			ordenarCorrentistaPorNome(todos);
		}
		return todos;
	}
	private void ordenarCorrentistaPorNome(Correntista[] correntista) {
		Correntista ax = null;
		for (int i = 0; i < correntista.length; i++) {
			for (int k = i; k < correntista.length; k++) {
				if (correntista[i].getCorrentistName().compareTo(correntista[k].getCorrentistName()) > 0) {
					ax = correntista[i];
					correntista[i] = correntista[k];
					correntista[k] = ax;
				}
			} 
		}
	}
	public static Correntista searchCorrentist(String cpf) {
		return null;
	}
	public boolean validarCpf(String CPF) {
		if (CPF.equals("00000000000") ||
	            CPF.equals("11111111111") ||
	            CPF.equals("22222222222") || CPF.equals("33333333333") ||
	            CPF.equals("44444444444") || CPF.equals("55555555555") ||
	            CPF.equals("66666666666") || CPF.equals("77777777777") ||
	            CPF.equals("88888888888") || CPF.equals("99999999999") ||
	            (CPF.length() != 11))
	            return(false);

	        char dig10, dig11;
	        int sm, i, r, num, peso;

	        
	        try {
	        
	            sm = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {
	       
		            num = (int)(CPF.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char)(r + 48);

	        
	            sm = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
		            num = (int)(CPF.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                 dig11 = '0';
	            else dig11 = (char)(r + 48);

	 
	            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	                 return(true);
	            else return(false);
	                } catch (InputMismatchException erro) {
	                return(false);
	            }
	}
}