package br.com.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.store.model.Produto;
import br.com.store.repository.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoService produtoService;

	@Autowired
	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public String listaProdutos(Model model) {
		model.addAttribute("produtos", produtoService.getAll());
		return "produtos/lista";
	}

	@GetMapping("/{id}")
	public String produtoPorId(Model model, @PathVariable("id") int id) {
		model.addAttribute("produto", produtoService.getById(id));
		return "produtos/produto";
	}

	@PostMapping
	public String novoProduto(@Valid @ModelAttribute Produto produto,
	    BindingResult br, Model model) {
		if (br.hasErrors())
			return "produtos/form";

		produtoService.save(produto);

		return "redirect:/produtos";
	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("produto", new Produto());
		return "produtos/form";
	}

	@GetMapping("/edit/{id}")
	public String editForm(Model model, @PathVariable("id") int id) {
		model.addAttribute("produto", produtoService.getById(id));
		return "produtos/editform";
	}

	@PostMapping("/edit")
	public String edit(@Valid @ModelAttribute Produto produto, BindingResult br,
	    Model model) {
		if (br.hasErrors())
			return "produtos/editform";

		produtoService.save(produto);

		return "redirect:/produtos";
	}

	@GetMapping("/busca")
	public String busca(Model model,
	    @RequestParam("produtoBuscado") String produtoBuscado) {
		Iterable<Produto> produtos = produtoService.searchByProductName(
		    produtoBuscado);
		model.addAttribute("produtos", produtos);

		return "produtos/lista";
	}

}
