package br.com.cesarschool.poo.geral;
/**
 mudei algumas variáveis, mas é preciso rever a logica
 */
public class RepositorioConta{

	private static final int TAMANHO_MAX_CONTAS = 1000;
	private static RepositorioConta instancia = null;
	
	private Conta[] cadastroConta = new Conta[TAMANHO_MAX_CONTAS];
	private int tamanhoAtual = 0;
	
	
	private RepositorioConta() {
		
	}
	
	public static RepositorioConta getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioConta();
		}
		return instancia; 
	}
	
	public boolean incluir(Conta Conta) {
		if (buscarIndice(Conta.getNumber()) != -1) {
			return false;
		} else if (tamanhoAtual == TAMANHO_MAX_CONTAS - 1) {
			return false;
		} else {
			for (int i = 0; i < cadastroConta.length; i++) {
				if (cadastroConta[i] == null) {
					cadastroConta[i] = Conta; 
					break;
				}
			}
			tamanhoAtual++; 
			return true; 
		}
	}
	public boolean alterar(Conta Conta) {
		int indice = buscarIndice(Conta.getNumber()); 
		if (indice == -1) {
			return false;
		} else {
			cadastroConta[indice] = Conta;
			return true; 
		}
	}
	
	public Conta buscar(long number) {
		int indice = buscarIndice(number);
		if (indice == -1) {
			return null;
		} else {
			return cadastroConta[indice];
		}
	}
	
	public boolean excluir(long number) {
		int indice = buscarIndice(number);
		if (indice == -1) {
			return false;
		} else {
			cadastroConta[indice] = null;
			tamanhoAtual--;
			return true;
		}		
	}
	
	private int buscarIndice(long number) {		
		for (int i = 0; i < cadastroConta.length; i++) {
			Conta Conta = cadastroConta[i];
			if (Conta != null && Conta.getNumber() == number) {
				return i; 				
			}
		}
		return -1;
	}
}
