package br.com.store.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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

	public double getValorTotal() {
		return produtos.stream().mapToDouble(p -> p.getValor()).sum();
	}

	public void setUser(String username) {
		this.username = username;
	}
}
