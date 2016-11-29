package br.com.store.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Carrinho implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Produto> produtos;

	public void addProduto(Produto produto) {
		produtos.add(produto);
	}

	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}

	public void remove(Produto produto) {
		produtos.remove(produto);
	}

	public void removeTodos(Produto produto) {
		while (produtos.contains(produto))
			produtos.remove(produto);
	}

	public void limpaCarrinho() {
		produtos = new ArrayList<>();
	}

	public double getValorTotal() {
		return produtos.stream().mapToDouble(p -> p.getValor()).sum();
	}

	public Map<Produto, Integer> getProdutoEQuantidade() {

		Map<Produto, Integer> produtoEQuantidade = new HashMap<>();

		produtos.forEach(produto -> {
			if (produtoEQuantidade.containsKey(produto))
				produtoEQuantidade.put(produto, produtoEQuantidade.get(produto) + 1);
			else
				produtoEQuantidade.put(produto, 1);
		});

		return produtoEQuantidade;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public String getUser() {
		return username;
	}
}
