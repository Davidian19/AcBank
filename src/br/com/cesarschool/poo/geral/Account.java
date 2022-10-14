package br.com.cesarschool.poo.geral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Account{
	private long number;
    private int statusAccountId;
    private String statusAccountDescription;
    private double accountBalance;
    private LocalDate openingDate;

    //constructor
    public Account(long number, int statusAccountId, String openingDate) {
		this.setNumber(number);
		this.setStatus(statusAccountId);
        this.setBalance(accountBalance);
		this.setOpeningDate(openingDate);
	}

    //Number
    public void setNumber(long number) {
		this.number = number;
	}
    public long getNumber() {
		return number;
	}

    //status
    public void setStatus(int statusAccountId) {
		this.statusAccountId = statusAccountId;
	}
	public int getStatusId() {
		return statusAccountId;
	}
	public int obterPorCodigo(int codigo) {
		return getStatusId();
	}
	public String getStatusDescription() {
		if(this.getStatusId() == 1){
            return "CONTA ATIVA";
        } else if (this.getStatusId() == 2){
            return "CONTA ENCERRADA";
        } else if (this.getStatusId() == 3){
            return "CONTA BLOQUEADA";
        } else {
            return "INSIRA UMA FUNÇÃO VÁLIDA";
        }
    }

    //data
    public void setOpeningDate(String openingDate) {
		LocalDate dateAux = LocalDate.parse(openingDate);
		this.openingDate = dateAux;
	}

	public LocalDate getDataAbertura() {
		return openingDate;
	}

    //saldo
	public double getBalance() {
		return accountBalance;
	}
	public void setBalance(double accountBalance) {
		this.accountBalance = accountBalance;

	}
	public boolean isAccountBalance() {
		if(this.accountBalance > 0) {
			return true;
		} else {
			return false;
		}
	}

    //validar conta
    public boolean isNumber(){
        if(this.number < 0){
            return false;
        }else if(this.number < 0){
            return false;
        }else{
            return true;
        }
    }
    public boolean isDate(LocalDate now, LocalDate monthVerification){
        if(this.openingDate.isAfter(monthVerification) == false){
            return false;
        }else if(this.openingDate.isAfter(monthVerification) == false){
            return false;
        }else if((this.openingDate.isBefore(now) == false || this.openingDate.equals(now) == false)){
            return false;
        }else{
            return true;
        }
        
    }
    public boolean isStatusId(){
        if(this.getStatusId() > 3 && this.getStatusId() < 1){
            return false;
        }else{
            return true;
        }
        
    }

	public short calcularEscore() {
		LocalDate dataAgora = LocalDate.now();
		short score = -2;
		long f;
		if(this.getStatusId() == 3) {
			score = -1;
			return score;
		} else if(this.getStatusId() == 2) {
			score = 0;
			return score;
			
		} else if(this.getStatusId() == 1) {
			long tempoDeVida = openingDate.until(dataAgora,ChronoUnit.DAYS);
			f = (long) (this.getBalance() * 3.0 + tempoDeVida * 2L);
			if(f < 5800) {
				score = 1;
			} else if (f > 5800 && f < 13000) {
				score = 2;
			} else if(f > 13000 && f < 39000) {
				score = 3;
			} else {
				score = 4;
			}
		}
		return score;
	}
    
    public int isAccount() {
		LocalDate now = LocalDate.now();
		LocalDate monthVerification = now.minusMonths(1);
		if(this.getNumber() < 0) {
			return 1;
		} else if (this.getStatusId() > 3 && this.getStatusId() < 1) {
			return 2;
		} else if (!(this.openingDate.isAfter(monthVerification)) ||
			(!(this.openingDate.isBefore(now) || this.openingDate.equals(now)))) {
			return 3;
		}else{
			return 0;
		}
	}

    public void creditar(double valorCredito) {
		if(!(this.getStatusId() == 3 && this.getStatusId() == 2)){
			if(valorCredito >= 0) {
				this.setBalance(valorCredito + this.getBalance());
			}
		}
	}
	
	public void debitar(double valorDebito) {
		if(!(this.getStatusId() == 3 && this.getStatusId() == 2)) {
			if(valorDebito > 0 && this.getBalance() > valorDebito) {
				this.accountBalance -= valorDebito;
			}
		}
	}
}