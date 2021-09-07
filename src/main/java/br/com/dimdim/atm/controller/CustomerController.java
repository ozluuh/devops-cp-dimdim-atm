package br.com.dimdim.atm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dimdim.atm.model.Customer;
import br.com.dimdim.atm.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerRepository repo;

	@GetMapping
	public String index(HttpSession session, Model model, Customer customer) {
		Object attribute = session.getAttribute("customer");
		model.addAttribute("customer", attribute);
		log.debug("Attribute: {}", attribute);

		return "profile";
	}

	@PostMapping
	public ModelAndView update(HttpSession session, Customer customer) {
		log.debug("ProfilePOSTData: {}", customer);
		repo.save(customer);
		log.info("CustomerProfileUpdated");
		session.setAttribute("customer", customer);
		log.info("CustomerObjectSessionToUpdate");

		return new ModelAndView("redirect:/app");
	}
}
