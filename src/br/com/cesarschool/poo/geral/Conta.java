package br.com.cesarschool.poo.geral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Produto{
    private long number;
    private int statusAccount;
    private double accountBalance = 0;
    private String openingDate;
	Scanner sc = new Scanner(System.in);

    
    public Produto(long number, int statusAccount, double accountBalance, String openingDate) {
		this.number = number;
		this.statusAccount = statusAccount;
		this.accountBalance = accountBalance;
		this.openingDate = openingDate;
	}

    public long getNumber(){
        return number;
    }

    public void setNumber(long number){
        this.number = number;
    }
    
    //fazer status

    public double getAccountBalance(){
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }
    
    public boolean validateNumber(){
        if(this.number < 0){
            return false;
        }else if(this.number < 0){
            return false;
        }else{
            return true;
        }
    }

    public LocalDate setDate(String openingDate){
        this.openingDate = openingDate;
        openingDate = sc.next();
        LocalDate newDate = dateInput(openingDate);
        return newDate;
    }

    public static LocalDate dateInput(String userInput) { 
        // estudar melhor esse input
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
    
    
        System.out.println(date);
        return date ;
    }

    public double creditar(){
        //fazer condicional do status
		double addValor = sc.nextDouble();
        if(addValor > 0){
            return accountBalance = accountBalance + addValor;
        } else {
            return accountBalance;
        }
	}

    public double debitar(){
        //fazer condicional do status
		double subValor = sc.nextDouble();
        if(subValor > 0){
            return accountBalance = accountBalance - subValor;
        } else {
            return accountBalance;
        }
	}
}