package br.com.dimdim.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
	SPECIAL(500d, "Especial"), PREMIUM(1000d, "Premium"), DEFAULT(100d, "Padrão");

	private final Double limit;

	private final String display;
}
