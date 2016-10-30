package br.com.store.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.store.model.Carrinho;

public interface CarrinhoRepository extends CrudRepository<Carrinho, String> {

}
