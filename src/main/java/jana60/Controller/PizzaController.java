package jana60.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.Model.Pizza;
import jana60.Repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaRepository repo;

	@GetMapping
	private String pizza(Model model) {
		model.addAttribute("pizzaList", repo.findAll());
		return "/pizza/pizza";
	}

	@GetMapping("/pizza/add")
	public String pizzaForm(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "/pizza/add";
	}
}
