package br.com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.store.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("SELECT p FROM Produto p WHERE p.nomeProduto like CONCAT('%',:nomeProduto,'%')")
	public Iterable<Produto> find(@Param("nomeProduto") String nomeProduto);

}
