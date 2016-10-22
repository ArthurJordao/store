package br.com.store.controller;

import org.springframework.stereotype.Controller;
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

}
