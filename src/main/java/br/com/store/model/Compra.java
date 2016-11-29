package br.com.store.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String username;

	@ManyToMany
	private List<Produto> produtos;

	private Double valor;

	@Deprecated
	public Compra() {

	}

	public Compra(Carrinho carrinho) {
		username = carrinho.getUser();
		produtos = carrinho.getProdutos();
		valor = produtos.stream().mapToDouble(p -> p.getValor()).sum();
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

	public String getUsername() {
		return username;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public double getValor() {
		return valor.doubleValue();
	}

	public int getQuantidadeProdutos() {
		return produtos.size();
	}

	public Integer getId() {
		return id;
	}

}
