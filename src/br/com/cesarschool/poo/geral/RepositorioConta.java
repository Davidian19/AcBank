package br.com.cesarschool.poo.geral;

public class RepositorioConta{

	private static final int TAMANHO_MAX_CONTAS = 1000;
	private static RepositorioConta instancia = null;
	
	private Produto[] cadastroProduto = new Produto[TAMANHO_MAX_CONTAS];
	private int tamanhoAtual = 0;
	
	
	private RepositorioConta() {
		
	}
	
	public static RepositorioConta getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioConta();
		}
		return instancia; 
	}
	
	public boolean incluir(Produto produto) {
		if (buscarIndice(produto.getNumber()) != -1) {
			return false;
		} else if (tamanhoAtual == TAMANHO_MAX_CONTAS - 1) {
			return false;
		} else {
			for (int i = 0; i < cadastroProduto.length; i++) {
				if (cadastroProduto[i] == null) {
					cadastroProduto[i] = produto; 
					break;
				}
			}
			tamanhoAtual++; 
			return true; 
		}
	}
	public boolean alterar(Produto produto) {
		int indice = buscarIndice(produto.getNumber()); 
		if (indice == -1) {
			return false;
		} else {
			cadastroProduto[indice] = produto;
			return true; 
		}
	}
	
	public Produto buscar(long codigo) {
		int indice = buscarIndice(codigo);
		if (indice == -1) {
			return null;
		} else {
			return cadastroProduto[indice];
		}
	}
	
	public boolean excluir(long codigo) {
		int indice = buscarIndice(codigo);
		if (indice == -1) {
			return false;
		} else {
			cadastroProduto[indice] = null;
			tamanhoAtual--;
			return true;
		}		
	}
	
	private int buscarIndice(long codigo) {		
		for (int i = 0; i < cadastroProduto.length; i++) {
			Produto produto = cadastroProduto[i];
			if (produto != null && produto.getNumber() == codigo) {
				return i; 				
			}
		}
		return -1;
	}
}
