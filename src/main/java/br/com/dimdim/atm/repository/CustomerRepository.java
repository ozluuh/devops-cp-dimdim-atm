package br.com.dimdim.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimdim.atm.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
