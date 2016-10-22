package br.com.store.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.store.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
