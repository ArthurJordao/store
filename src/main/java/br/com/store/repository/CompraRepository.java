package br.com.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.store.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

	public List<Compra> findByUsername(String username);

}
