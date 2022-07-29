package jana60.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jana60.Model.Pizza;
import jana60.Repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private PizzaRepository repo;

	@GetMapping
	public String home() {
		return "/home/home";

	}

	@GetMapping("/cerca")
	public String search(@RequestParam(name = "cerca") String cerca, Model model) {

		if (cerca != null && cerca.isEmpty()) {
			cerca = null;
		}

		List<Pizza> pizza = repo.findByNameContaining(cerca);
		model.addAttribute("pizzaList", pizza);
		return "/pizza/pizza";
	}
}
