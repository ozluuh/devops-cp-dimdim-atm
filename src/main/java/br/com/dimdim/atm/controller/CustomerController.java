package br.com.dimdim.atm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dimdim.atm.model.AccountType;
import br.com.dimdim.atm.model.BankStatement;
import br.com.dimdim.atm.model.Customer;
import br.com.dimdim.atm.model.TransactionType;
import br.com.dimdim.atm.repository.BankStatementRepository;
import br.com.dimdim.atm.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repo;

    private final BankStatementRepository bankRepo;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody final Customer customer, UriComponentsBuilder uriBuilder) {

        switch (customer.getAccount().getType()) {
            case SPECIAL:
                customer.getAccount().setAccountLimit(AccountType.SPECIAL.getLimit());
                break;
            case PREMIUM:
                customer.getAccount().setAccountLimit(AccountType.PREMIUM.getLimit());
                break;
            default:
                customer.getAccount().setAccountLimit(AccountType.DEFAULT.getLimit());
                break;
        }

        repo.save(customer);
        log.debug("Created: {}", customer);

        URI uri = uriBuilder
                    .path("/customer/{id}")
                    .buildAndExpand(customer.getId())
                    .toUri();

        return ResponseEntity.created(uri).body(customer);
    }

    @GetMapping
    public List<Customer> index() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> show(@PathVariable final Long id) {
        return ResponseEntity.of(repo.findById(id));
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody final Customer customer) {
        if (show(customer.getId()).getStatusCodeValue() == 404) {
            return ResponseEntity.notFound().build();
        }

        repo.save(customer);
        log.debug("Updated: {}", customer);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/{id}/bankServices/deposit")
    public ResponseEntity<Object> deposit(@PathVariable Long id, @RequestParam(name = "value") Double value) {
        if (value == null) return ResponseEntity.badRequest().build();
        
        ResponseEntity<Customer> response = show(id);

        if(! response.hasBody()) return ResponseEntity.notFound().build();

        Customer customer = response.getBody();

        customer.getAccount().deposit(value);

        BankStatement history = BankStatement
                                    .builder()
                                    .transactionType(TransactionType.C)
                                    .value(value)
                                    .balance(customer.getAccount().getBalance())
                                    .history("Crédito em conta")
                                    .customer(customer)
                                    .build();

        repo.save(customer);
        log.info("Deposit");
        bankRepo.save(history);
        log.info("Deposit history created");
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/bankServices/withdraw")
    public ResponseEntity<Object> withdraw(@PathVariable Long id, @RequestParam(name = "value") Double value){
        if(value == null) return ResponseEntity.badRequest().build();
        
        ResponseEntity<Customer> response = show(id);

        if(!response.hasBody()) return ResponseEntity.notFound().build();

        Customer customer = response.getBody();

        customer.getAccount().withdraw(value);

        BankStatement history = BankStatement
                                    .builder()
                                    .transactionType(TransactionType.D)
                                    .value(value)
                                    .balance(customer.getAccount().getBalance())
                                    .history("Débito em conta")
                                    .customer(customer)
                                    .build();
        
        repo.save(customer);
        log.info("Withdraw");
        bankRepo.save(history);
        log.info("Withdraw history created");
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/bankServices/statement")
    public List<BankStatement> statement(@PathVariable Long id) {
        return bankRepo.findAllByCustomerId(id);
    }
}
