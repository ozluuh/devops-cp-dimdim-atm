package br.com.dimdim.atm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dimdim.atm.model.BankStatement;
import br.com.dimdim.atm.model.Customer;
import br.com.dimdim.atm.model.TransactionType;
import br.com.dimdim.atm.repository.BankStatementRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

	private Customer customer;

	private static final String CUSTOMER_ATTRIBUTE = "customer";

	private final BankStatementRepository bankRepo;

	@PostMapping("/validation")
	public String validation(HttpSession session) {
		if (session.getAttribute(CUSTOMER_ATTRIBUTE) == null) {
			log.info("customerNotFoundOnSession: Raising error message");

			Map<String, Object> response = new HashMap<>();
			response.put("error", true);
			response.put("errorTitle", "Cliente não encontrado");
			response.put("errorMessage", "Verifique suas credenciais e tente novamente.");

			response.forEach(session::setAttribute);

			return "redirect:/login";
		}

		Object sessionCustomer = session.getAttribute(CUSTOMER_ATTRIBUTE);
		if (customer == null || !customer.equals(sessionCustomer)) {
			customer = (Customer) sessionCustomer;
			session.removeAttribute(CUSTOMER_ATTRIBUTE);
		}

		return "redirect:/app";
	}

	@GetMapping
	public String home(Model model) {
		Map<String, Double> items = new HashMap<>();
		items.put("C", .0);
		items.put("D", .0);

		List<BankStatement> statements = bankRepo.findAllByCustomerId(customer.getId());

		statements.forEach(statement -> {
			String key = statement.getTransactionType().toString();
			Double value = statement.getValue();

			items.merge(key, value, Double::sum);
		});

		model.addAttribute(CUSTOMER_ATTRIBUTE, customer);
		model.addAttribute("statements", statements.isEmpty() ? null : statements);
		model.addAllAttributes(items);

		return "index";
	}

	@GetMapping("/transaction")
	public String transaction(Model model, BankStatement statement) {
		model.addAttribute(CUSTOMER_ATTRIBUTE, customer);

		return "transaction";
	}

	@PostMapping("/transaction")
	public String transaction(BankStatement statement) {
		statement.setCustomer(customer);

		if (statement.getTransactionType() == TransactionType.C) {
			customer.getAccount().deposit(statement.getValue());
			log.info("Deposit realized");
		} else {
			customer.getAccount().withdraw(statement.getValue());
			log.info("Withdraw realized");
		}
		statement.setBalance(customer.getAccount().getBalance());
		log.debug("TransactionPost: {}", statement);

		bankRepo.save(statement);
		log.info("Statement registered");

		return "redirect:/app";
	}

	@GetMapping("/profile")
	public ModelAndView profile(HttpSession session){
		session.setAttribute(CUSTOMER_ATTRIBUTE, customer);
		return new ModelAndView("forward:/customer");
	}

}
