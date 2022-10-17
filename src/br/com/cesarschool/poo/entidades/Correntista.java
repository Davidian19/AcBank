package br.com.cesarschool.poo.entidades;

public class Correntista {
	private String nome;
    private String cpf;

	public Correntista(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCorrentistName() {
		return nome;
	}
	public void setCorrentistName(String nome) {
		this.nome = nome;
	} 
	
}
