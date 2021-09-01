package br.com.dimdim.atm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimdim.atm.model.BankStatement;

@Repository
public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {

    List<BankStatement> findAllByCustomerId(Long id);
}
