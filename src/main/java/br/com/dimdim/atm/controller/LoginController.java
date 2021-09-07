package br.com.dimdim.atm.controller;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

	private static final String CUSTOMER_ATTRIBUTE = "customer";

	private final CustomerRepository repo;

	@GetMapping(path = { "/", "/login" })
	public String index(HttpSession session) {
		if (session.getAttribute(CUSTOMER_ATTRIBUTE) != null) {
			session.removeAttribute(CUSTOMER_ATTRIBUTE);
			log.info("hasCustomerOnLogin: Attribute Removed");

			session.setAttribute("error", true);
			session.setAttribute("errorTitle", "Sessão expirada");
			session.setAttribute("errorMessage", "Realize o acesso novamente.");
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if (!session.isNew()) {
			session.invalidate();
			log.info("Session invalidated");
		}

		return "redirect:/login";
	}
}
