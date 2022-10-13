package br.com.cesarschool.poo.geral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
    public boolean isAccount() {
		LocalDate now = LocalDate.now();
		LocalDate monthVerification = now.minusMonths(1);
		if(isNumber()) {
			return false;
		} else if (isStatusId()) {
			return false;
		} else if (isDate(now, monthVerification)) {
			return false;
		}
		
		return true;
	}

    public void creditar(double valorCredito) {
		if(!(this.getStatusId() == StatusConta.ENCERRADA)) {
			if(valorCredito >= 0) {
				this.setSaldo(valorCredito + this.getBalance());
			}
		}
	}
	
	public void debitar(double valorDebito) {
		if(!(this.getStatusId() == StatusConta.BLOQUEADA)) {
			if(valorDebito > 0 && this.getBalance() > valorDebito) {
				this.accountBalance -= valorDebito;
			}
		}
	}
}