package br.com.dimdim.atm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.dimdim.atm.model.exceptions.NoEnoughCashException;

import lombok.Data;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String agency;

    @Column(nullable = false, unique = true)
    private String number;

    private Double balance = 0d;

    private Double accountLimit;

    @Enumerated(EnumType.STRING)
    private AccountType type = AccountType.DEFAULT;

    public void withdraw(Double value) {
        if (!((balance - value) > -accountLimit)) {
            throw new NoEnoughCashException();
        }
        this.balance -= value;
    }

    public void deposit(Double value) {
        this.balance += value;
    }
}
