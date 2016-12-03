package br.com.store.controller;

import java.util.Map;

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
import br.com.store.model.Compra;
import br.com.store.model.Produto;
import br.com.store.repository.service.CarrinhoService;
import br.com.store.repository.service.CompraService;
import br.com.store.repository.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	private ProdutoService produtoService;
	private CarrinhoService carrinhoService;
	private CompraService compraService;

	@Autowired
	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}

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
		Map<Produto, Integer> produtosEQuantidade = carrinho
		    .getProdutoEQuantidade();

		model.addAttribute("produtos", produtosEQuantidade);
		model.addAttribute("valorTotal", carrinho.getValorTotal());
		return "carrinho/lista";

	}

	@PostMapping("/add")
	public String adicionaProduto(Model model, @RequestParam("id") Integer id) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);
		Produto produto = produtoService.getById(id);
		produto.compraProduto();
		produtoService.save(produto);
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
		carrinho.removeTodos(produto);
		carrinhoService.saveCarrinho(carrinho);

		return "redirect:/carrinho/lista";

	}

	@PostMapping("/removerUmItem")
	public String deletaUmItem(Model model, @RequestParam("id") Integer id) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);
		Produto produto = produtoService.getById(id);
		produto.devolveProduto();
		produtoService.save(produto);
		carrinho.remove(produto);
		carrinhoService.saveCarrinho(carrinho);

		return "redirect:/carrinho/lista";

	}

	@PostMapping("/concluirCompra")
	public String concluiCompra(Model model) {
		Authentication authenticated = SecurityContextHolder.getContext()
		    .getAuthentication();
		String username = authenticated.getName();
		Carrinho carrinho = carrinhoService.getCarrinhoByUserName(username);

		Compra compra = new Compra(carrinho);
		carrinho.limpaCarrinho();

		compraService.saveCompra(compra);
		carrinhoService.saveCarrinho(carrinho);

		model.addAttribute("compra", compra);

		return "carrinho/conclusao";
	}
}
