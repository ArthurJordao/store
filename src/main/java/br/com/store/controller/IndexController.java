package br.com.store.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping
	public String index() {
		return "/index";
	}

	@RequestMapping("/sobre")
	public String sobre() {
		return "/sobre";
	}

	@RequestMapping("/contato")
	public String contato() {
		return "/contato";
	}

	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext()
		    .getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken))
			return "forward:/";
		return "/login";
	}

}
