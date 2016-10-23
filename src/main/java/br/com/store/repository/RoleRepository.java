package br.com.store.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.store.model.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

}
