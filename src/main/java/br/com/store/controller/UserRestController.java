package br.com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.store.model.Role;
import br.com.store.model.User;
import br.com.store.repository.service.RoleService;
import br.com.store.repository.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	private UserService userService;
	private RoleService roleService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public Iterable<User> users() {
		return userService.listAll();
	}

	@PostMapping
	public void add(@RequestParam(value = "user") String userName,
	    @RequestParam(value = "password") String password) {
		User user = new User();
		user.setUser(userName);
		user.setPassword(password);

		Role role = roleService.roleByName("ROLE_ADMIN");
		user.addRole(role);

		userService.save(user);
	}

	@GetMapping("/{username}")
	public User getByUsername(@PathVariable(value = "username") String user) {
		return userService.userById(user);
	}

	@DeleteMapping("/{username}")
	public void deleteUserByName(
	    @PathVariable(value = "username") String username) {
		userService.delete(username);
	}

}
