package br.com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.store.model.Carrinho;
import br.com.store.model.Produto;
import br.com.store.repository.service.CarrinhoService;
import br.com.store.repository.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	private ProdutoService produtoService;
	private CarrinhoService carrinhoService;

	@Autowired
	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Autowired
	public void setCarrinhoService(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}

	@GetMapping("/lista")
	public String listaProdutosNoCarrinho(Model model) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);
		List<Produto> produtos = carrinho.getProdutos();

		model.addAttribute("produtos", produtos);
		model.addAttribute("valorTotal", carrinho.getValorTotal());
		return "/carrinho/lista";

	}

	@PostMapping("/add")
	public String adicionaProduto(Model model, @RequestParam("id") Integer id) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);
		Produto produto = produtoService.getById(id);
		carrinho.addProduto(produto);
		carrinhoService.saveCarrinho(carrinho);

		return "redirect:/carrinho/lista";

	}

	@PostMapping("/deleta")
	public String deletaProduto(Model model, @RequestParam("id") Integer id) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);
		Produto produto = produtoService.getById(id);
		carrinho.remove(produto);
		carrinhoService.saveCarrinho(carrinho);

		return "redirect:/carrinho/lista";

	}

}
