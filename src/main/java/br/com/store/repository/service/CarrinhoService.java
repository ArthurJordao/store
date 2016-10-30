package br.com.store.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.store.model.Carrinho;
import br.com.store.repository.CarrinhoRepository;

@Repository
public class CarrinhoService {

	private CarrinhoRepository carrinhoRepository;

	@Autowired
	public void setCarrinhoRepository(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}

	public Carrinho getCarrinhoByUserName(String username) {
		return carrinhoRepository.findOne(username);
	}

	public void saveCarrinho(Carrinho carrinho) {
		carrinhoRepository.save(carrinho);
	}

}
