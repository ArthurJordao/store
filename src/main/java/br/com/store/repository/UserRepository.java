package br.com.store.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.store.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
