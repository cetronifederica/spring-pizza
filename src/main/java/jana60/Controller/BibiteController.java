package jana60.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.Model.Bibite;
import jana60.Repository.BibiteRepository;

@Controller
@RequestMapping("/bibite")
public class BibiteController {

	@Autowired
	private BibiteRepository repo;

	@GetMapping
	private String drink(Model model) {
		model.addAttribute("drinkList", repo.findAll());
		model.addAttribute("newDrink", new Bibite());
		return "/bibite/bibite";
	}

	@PostMapping("/save")
	public String saveDrink() {

		return "";
	}
}
