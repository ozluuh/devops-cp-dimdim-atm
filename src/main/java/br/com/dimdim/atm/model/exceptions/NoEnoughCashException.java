package br.com.dimdim.atm.model.exceptions;

public class NoEnoughCashException extends RuntimeException {
    
    public NoEnoughCashException(){
        super("No Enough Cash. Aborted operation.");
    }
}
