package br.com.cesarschool.poo.repositorios;
import br.com.cesarschool.poo.entidades.Correntista;

/**
 * @author An�nimo
 *
 * Implementa��o prim�ria para efeito did�tico.
 * Ser� melhorada!!!
 */
public class CorrentistRepository {

	private static final int TAMANHO_MAX_CORRENTISTA = 1000;
	private static CorrentistRepository instancia; 
	
	private Correntista[] correntistregistration = new Correntista[TAMANHO_MAX_CORRENTISTA];
	private int tamanhoAtual = 0;
	
	public CorrentistRepository() {
		
	}
	
	public static CorrentistRepository getInstancia() {
		if (instancia == null) {
			instancia = new CorrentistRepository();
		}
		return instancia; 
	}
	
	public boolean incluir(Correntista correntista) {
		if (buscarIndice(correntista.getCpf()) != -1) {
			return false;
		} else if (tamanhoAtual == TAMANHO_MAX_CORRENTISTA - 1) {
			return false;
		} else {
			for (int i = 0; i < correntistregistration.length; i++) {
				if (correntistregistration[i] == null) {
					correntistregistration[i] = correntista; 
					break;
				}
			}
			tamanhoAtual++; 
			return true; 
		}
	}
	public boolean change(Correntista correntista) {
		int indice = buscarIndice(correntista.getCpf()); 
		if (indice == -1) {
			return false;
		} else {
			correntistregistration[indice] = correntista;
			return true; 
		}
	}
	
	public Correntista searchCorrentist(String cpf) {
		int indice = buscarIndice(cpf);
		if (indice == -1) {
			return null;
		} else {
			return correntistregistration[indice];
		}
	}
	
	public boolean delete(String cpf) {
		int indice = buscarIndice(cpf);
		if (indice == -1) {
			return false;
		} else {
			correntistregistration[indice] = null;
			tamanhoAtual--;
			return true;
		}		
	}
	
	public Correntista[] buscarTodos() {
		Correntista[] resultado = new Correntista[tamanhoAtual];
		int indice = 0;
		for (Correntista Correntista : correntistregistration) {
			if (Correntista != null) {
				resultado[indice++] = Correntista; 
			}
		}
		return resultado;
	}
	
	private int buscarIndice(String cpf) {		
		for (int i = 0; i < correntistregistration.length; i++) {
			Correntista Correntista = correntistregistration[i];
			if (Correntista != null && Correntista.getCpf().equals(cpf)) {
				return i; 				
			}
		}
		return -1;
	}		
}
