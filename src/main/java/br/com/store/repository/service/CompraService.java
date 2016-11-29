package br.com.store.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.store.model.Compra;
import br.com.store.repository.CompraRepository;

@Repository
public class CompraService {
	private CompraRepository compraRepository;

	@Autowired
	public void setCompraRepository(CompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}

	public void saveCompra(Compra compra) {
		compraRepository.save(compra);
	}

	public List<Compra> comprasDe(String username) {
		return compraRepository.findByUsername(username);
	}

	public Compra findById(Integer id) {
		return compraRepository.findOne(id);
	}

}
