package br.com.dimdim.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    C("Depósito"), D("Saque");

	private final String description;
}
