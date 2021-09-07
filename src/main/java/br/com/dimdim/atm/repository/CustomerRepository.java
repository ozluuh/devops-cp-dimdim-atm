package br.com.dimdim.atm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dimdim.atm.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("from Customer c where c.account.number = ?1 and c.account.agency = ?2")
	Optional<Customer> findCustomerByAccountNumberAndAgency(String number, String agency);
}
