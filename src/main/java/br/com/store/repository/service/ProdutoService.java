package br.com.store.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.store.model.Produto;
import br.com.store.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	@Autowired
	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public Produto getById(Integer id) {
		return produtoRepository.findOne(id);
	}

	public Iterable<Produto> getAll() {
		return produtoRepository.findAll();
	}

	public void save(Produto produto) {
		produtoRepository.save(produto);
	}

	public void delete(Integer id) {
		produtoRepository.delete(id);
	}

}
