package br.com.josimeire.numerosloteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> listAll() {
		return repo.findAll();
	}	
	
	public void save(User user) {
		repo.save(user);
	}
	
	public List<User> getUser(String email) {
		return repo.getUser(email);
	}	
}
