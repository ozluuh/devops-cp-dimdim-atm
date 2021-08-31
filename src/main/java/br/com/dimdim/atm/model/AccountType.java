package br.com.dimdim.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SPECIAL(500d), PREMIUM(1000d), DEFAULT(100d);

    private final Double limit;
}
