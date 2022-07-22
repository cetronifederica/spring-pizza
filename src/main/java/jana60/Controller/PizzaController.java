package jana60.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/add")
	public String pizzaForm(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "/pizza/add";
	}

	@PostMapping("/save") // sezione di aggiunta della pizza al database
	public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult br, Model model) {
		boolean hasErrors = br.hasErrors();
		boolean validateName = true;
		if (formPizza.getId() != null) {
			Pizza pizzaBeforeUpdate = repo.findById(formPizza.getId()).get();

			if (pizzaBeforeUpdate.getName().equals(formPizza.getName())) {
				validateName = false;
			}
		}
		if (validateName && repo.countByName(formPizza.getName()) != null) {
			br.addError(new FieldError("pizza", "name", "I nomi delle pizze devono essere unici"));
			hasErrors = true;
		}

		if (br.hasErrors()) {
			return "/pizza/add";
		}

		if (hasErrors) {
			// se ci sono errori non salvo la pizza su database ma ritorno alla form
			// precaricata
			return "/pizza/edit";
		} else {
			// se non ci sono errori salvo la pizza che arriva dalla form
			try {
				repo.save(formPizza);
			} catch (Exception e) { // gestisco eventuali eccezioni sql
				model.addAttribute("errorMessage", "Unable to save the Pizza");
				return "/pizza/edit";
			}
			return "redirect:/";
		}
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer pizzaId, Model model) {
		Optional<Pizza> result = repo.findById(pizzaId);

		if (result.isPresent()) {

			model.addAttribute("pizza", result.get());
			return "/pizza/add";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con id " + pizzaId + " non è presente");
		}

	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pizzaId, RedirectAttributes ra) {
		Optional<Pizza> result = repo.findById(pizzaId);
		if (result.isPresent()) {

			repo.delete(result.get());
			ra.addFlashAttribute("successMessage", "Pizza " + result.get().getName() + " deleted!");
			return "redirect:/";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza con id " + pizzaId + " non è presente");
		}

	}
}
