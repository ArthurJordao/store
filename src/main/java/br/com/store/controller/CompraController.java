package br.com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.store.model.Compra;
import br.com.store.repository.service.CompraService;

@Controller
@RequestMapping("/compras")
public class CompraController {

	private CompraService compraService;

	@Autowired
	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}

	@GetMapping("/minhasCompras")
	public String comprasDoUsuario(Model model) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();

		List<Compra> compras = compraService.comprasDe(username);

		model.addAttribute("compras", compras);

		return "/compra/listaCompras";

	}

	@GetMapping("/compra/{id}")
	public String compraPorId(Model model, @PathVariable("id") int id) {
		Compra compra = compraService.findById(id);

		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();

		if (!username.equals(compra.getUsername()))
			return "redirect:/";

		model.addAttribute("compra", compra);
		return "/compra/compra";
	}

}
