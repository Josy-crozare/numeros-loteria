package br.com.josimeire.numerosloteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.josimeire.numerosloteria.Loteria.LOTO;

@Controller
public class LoteriaController {
     
	@Autowired
	private UserService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);		
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "new_user";
	}
	
	@RequestMapping("/get")
	public String getNumberUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
			
		return "get_number_user";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveNumber(Model model, @ModelAttribute("user") User user) {
		Loteria loteria = new Loteria(LOTO.MEGASENA);
		String numeros = loteria.showNumbers();
		
		model.addAttribute("numero", numeros);		
		
		user.setNumero(numeros);
		service.save(user);
		
		return "new_user";
	}	

	@RequestMapping(value = "/recuperar", method = RequestMethod.GET)
	public String getUser(Model model, @ModelAttribute("user") User user) {
		List<User> listUsers = service.getUser(user.getEmail());
		model.addAttribute("listUsers", listUsers);		
		
		return "get_number_user";
	}
	
}
