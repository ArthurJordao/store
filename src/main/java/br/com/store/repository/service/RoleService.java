package br.com.store.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.store.model.Role;
import br.com.store.repository.RoleRepository;

@Repository
public class RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role roleByName(String name) {
		return roleRepository.findOne(name);
	}

}
