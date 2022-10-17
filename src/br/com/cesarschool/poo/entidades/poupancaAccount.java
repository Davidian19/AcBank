package br.com.cesarschool.poo.entidades;

import java.time.LocalDate;

public class poupancaAccount extends Account {

    private double taxaJuros;
    private int totalDepositos;

    public poupancaAccount(long number, int statusAccountId, String openingDate, boolean poupanca, Correntista correntista, 
            double taxaJuros, int totalDepositos) {
        super(number, statusAccountId, openingDate, poupanca);
        setCorrentist(correntista);
        this.taxaJuros = taxaJuros;
        this.totalDepositos = totalDepositos;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public int getTotalDepositos() {
        return totalDepositos;
    }

    public void setTotalDepositos(int totalDepositos) {
        this.totalDepositos = totalDepositos;
    }

}