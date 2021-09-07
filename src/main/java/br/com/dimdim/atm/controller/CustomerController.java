package br.com.dimdim.atm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.dimdim.atm.model.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @GetMapping
    public String index(HttpSession session, Model model, Customer customer) {
		Object attribute = session.getAttribute("customer");
		model.addAttribute("customer", attribute);
		log.info("Attribute: {}", attribute);

        return "profile";
    }

	@PostMapping
	public String update(Customer customer){
		log.info("POST data: {}", customer);
		return "profile";
	}
}
