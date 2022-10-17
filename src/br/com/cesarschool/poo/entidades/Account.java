package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Account{
	public long number;
    public int statusAccountId;
    public String statusAccountDescription;
    public double accountBalance;
    public LocalDate openingDate;
	private Correntista correntista; 
	private boolean poupanca;

    //constructor
    public Account(long number, int statusAccountId, String openingDate, boolean poupanca) {
		super();
		this.setNumber(number);
		this.setStatus(statusAccountId);
        this.setBalance(accountBalance = 0);
		this.setOpeningDate(openingDate);
		this.setPoupanca(poupanca);
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
	//correntista
	public Correntista getCorrentista() {
		return correntista;
	}
	public void setCorrentist(Correntista correntista) {
		this.correntista = correntista;
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
		long scoreReferency;
		if(this.getStatusId() == 3) {
			score = -1;
			return score;
		} else if(this.getStatusId() == 2) {
			score = 0;
			return score;
			
		} else if(this.getStatusId() == 1) {
			long lifeTime = openingDate.until(dataAgora,ChronoUnit.DAYS);
			scoreReferency = (long) (this.getBalance() * 3.0 + lifeTime * 2L);
			if(scoreReferency < 5800) {
				score = 1;
			} else if (scoreReferency > 5800 && scoreReferency < 13000) {
				score = 2;
			} else if(scoreReferency > 13000 && scoreReferency < 39000) {
				score = 3;
			} else {
				score = 4;
			}
		}
		return score;
	}

	public boolean isPoupanca() {
        return poupanca;
    }
    public void setPoupanca(boolean poupanca) {
        this.poupanca = poupanca;
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