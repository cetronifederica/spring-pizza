package jana60.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.Model.Ingredienti;
import jana60.Repository.IngredientiRepository;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	private IngredientiRepository repo;

	@GetMapping
	public String IngredientiList(Model model) {
		model.addAttribute("ingredienti", repo.findAllByOrderByName());
		model.addAttribute("newIngredienti", new Ingredienti());
		return "/ingredienti/ingredienti";
	}

	@PostMapping("/save")
	public String saveCategory(@Valid @ModelAttribute("newIngredienti") Ingredienti formIngredienti, BindingResult br,
			Model model) {
		if (br.hasErrors()) {
			// ricarico la pagina
			model.addAttribute("ingredienti", repo.findAllByOrderByName());
			return "ingredienti/ingredienti";

		} else {
			// salvo la category
			repo.save(formIngredienti);
			return "redirect:/";
		}

	}

}
