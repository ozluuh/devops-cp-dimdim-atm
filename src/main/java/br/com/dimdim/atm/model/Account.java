package br.com.dimdim.atm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.dimdim.atm.model.exceptions.NoEnoughCashException;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "account", allocationSize = 1, initialValue = 1, sequenceName = "sq_account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account")
    private Long id;

    @Column(nullable = false)
    private String agency;

    @Column(nullable = false, unique = true, name = "account_number")
    private String number;

    private Double balance = .00;

    @Column(name="account_limit")
    private Double accountLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
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
