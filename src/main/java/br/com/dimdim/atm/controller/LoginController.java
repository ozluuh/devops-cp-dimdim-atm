package br.com.dimdim.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.dimdim.atm.model.Customer;
import br.com.dimdim.atm.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(path = { "/", "/login" })
@RequiredArgsConstructor
public class LoginController {

	private final CustomerRepository repo;

    @GetMapping
    public String index(final Customer customer) {
        return "login";
    }

    @PostMapping
    public String home(final Customer customer) {
		log.info("Received: {}", customer);

        return "index";
    }
}
