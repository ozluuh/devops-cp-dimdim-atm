package br.com.dimdim.atm.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "bankstatement")
@SequenceGenerator(name = "bank", allocationSize = 1, initialValue = 1, sequenceName = "sq_bankstatement")
public class BankStatement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;

	private Double value;

	private Double balance;

	private String history;

	@Builder.Default
	@Column(name = "movement_date")
	private final LocalDate movementDate = LocalDate.now();

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JsonIgnore
	private Customer customer;
}
