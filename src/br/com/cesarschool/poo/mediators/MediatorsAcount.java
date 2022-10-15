package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;
import br.com.cesarschool.poo.entidades.Account;


public class MediatorsAcount{

	
	public static final int SUCESSO = 0;
	public static final int NUMERO_INVALIDO = 1; 
	public static final int STATUS_INVALIDO = 2; 
	public static final int DATA_ABERTURA_INVALIDA = 3;
	
	public static MediatorsAcount INSTANCY;
	
	public static MediatorsAcount getInstancia() {
		if(INSTANCY == null) {
			INSTANCY = new MediatorsAcount();
		}
		return INSTANCY;
	}
	
	public boolean isBalance(Account conta) {
		if(conta.accountBalance > 0) {
			return true;
		} else {
			return false;
		}
	}
	public int isAccount(Account conta) {
		LocalDate agora = LocalDate.now();
		LocalDate umMesMenos = agora.minusMonths(1);
		if(conta.getNumber() < 0) {
			return NUMERO_INVALIDO;
		} else if (conta.getStatusId() > 3 && conta.getStatusId() < 1) {
			return STATUS_INVALIDO;
		} else if (!(conta.openingDate.isAfter(umMesMenos)) || (!(conta.openingDate.isBefore(agora) || conta.openingDate.equals(agora)))) {
			return DATA_ABERTURA_INVALIDA;
		}
		
		return SUCESSO;
	}
	public void creditar(double valorCredito, Account conta) {
		if(conta.getStatusDescription() != "ENCERRADA") {
			if(valorCredito >= 0) {
				double atualBalance = conta.getBalance();
				conta.setBalance(atualBalance + valorCredito);
			}
		}
	}
	public void debitar(double valorDebito, Account conta) {
		if(conta.getStatusDescription() != "BLOQUEADA") {
			if(valorDebito > 0 && conta.getBalance() > valorDebito) {
				conta.accountBalance -= valorDebito;
			}
		}
	}
}
