package br.com.store.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.store.model.User;
import br.com.store.repository.UserRepository;

@Repository
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
		return userRepository.findOne(username);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public Iterable<User> listAll() {
		return userRepository.findAll();
	}

	public User userById(String user) {
		return userRepository.findOne(user);
	}

	public void delete(String user) {
		userRepository.delete(user);
	}

}
