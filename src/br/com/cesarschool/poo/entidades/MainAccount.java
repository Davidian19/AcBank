package br.com.cesarschool.poo.entidades;
import br.com.cesarschool.poo.repositorios.RepositoryAccount;
import br.com.cesarschool.poo.entidades.Account;
import br.com.cesarschool.poo.mediators.MediatorsAcount;
import br.com.cesarschool.poo.telas.InterfaceAccount;
import java.util.InputMismatchException;

public class MainAccount {
	
	public static void main(String[] args) {
		InterfaceAccount tela = new InterfaceAccount();
		tela.executaTela();
	}
}

