package br.com.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.store.model.Carrinho;
import br.com.store.model.Role;
import br.com.store.model.User;
import br.com.store.repository.service.CarrinhoService;
import br.com.store.repository.service.RoleService;
import br.com.store.repository.service.UserService;

@Controller
@RequestMapping("/usuarios")
public class UserController {

	private UserService userService;
	private RoleService roleService;
	private CarrinhoService carrinhoService;

	@Autowired
	public void setCarrinhoService(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/form")
	public String formUser(Model model) {
		model.addAttribute("user", new User());
		return "usuarios/form";
	}

	@PostMapping
	public String addNormalUser(@ModelAttribute @Valid User user, BindingResult br) {
		if (userService.contains(user))
			br.rejectValue("username", "error.user", "Uma conta já existe para esse nome de usuário");
		if (br.hasErrors())
			return "usuarios/form";
		if (!roleService.exist("ROLE_NORMAL")) {
			Role newRole = new Role();
			newRole.setNome("ROLE_NORMAL");
			roleService.addRole(newRole);
		}
		Role role = roleService.roleByName("ROLE_NORMAL");
		user.addRole(role);

		userService.save(user);

		Carrinho carrinho = new Carrinho();
		carrinho.setUser(user.getUsername());
		carrinhoService.saveCarrinho(carrinho);

		return "redirect:/login";
	}

	@GetMapping("/edit")
	public String editarForm(Model model) {
		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		String username = authenticated.getName();

		User user = new User();
		user.setUsername(username);

		model.addAttribute("user", user);
		return "usuarios/editform";
	}

	@PostMapping("/edit")
	public String editar(@ModelAttribute @Valid User user, BindingResult br) {
		Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
		String name = authenticated.getName();
		if (name.equals(user.getUsername()))
			br.rejectValue("username", "error.user", "O usuário logado deve ser o mesmo que está sendo editado");
		if (br.hasErrors())
			return "usuarios/edit";
		
		User bdUser = userService.userById(name);
		bdUser.setPassword(user.getPassword());
		
		userService.save(bdUser);

		return "redirect:/";
	}
}
