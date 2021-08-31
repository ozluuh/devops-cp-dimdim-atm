package br.com.dimdim.atm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimdim.atm.model.Customer;
import br.com.dimdim.atm.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repo;

    @PostMapping
    public void create(@RequestBody final Customer customer) {
        repo.save(customer);
        log.info("create: {}", customer);
    }

    @GetMapping
    public List<Customer> index() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> show(@PathVariable Long id) {
        return ResponseEntity.of(repo.findById(id));
    }

}
