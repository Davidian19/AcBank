package br.com.cesarschool.poo.geral;


public class Produto{
    public long number;
    public int status;
    public double accountBalance;
    public String openingDate;
    private long getNumber;

    public Produto(long number, int status, double accountBalance, String openingDate) {
		this.number = number;
		this.status = status;
		this.accountBalance = accountBalance;
		this.openingDate = openingDate;
	}

    public long getNumber(){
        return number;
    }

    public void setNumber(long number){
        this.getNumber = number;
    }
    
    //fazer status

    public double getAccountBalance(){
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }
    
    boolean validateNumber(){
        if(this.number < 0){
            return false;
        }else if(this.number < 0){
            return false;
        }else{
            return true;
        }
    }

    //inserir data link: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#equals-java.lang.Object-
    
}