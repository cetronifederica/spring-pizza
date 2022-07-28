package jana60.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.Model.Image;
import jana60.Model.ImageForm;
import jana60.services.ImageServices;

@Controller
@RequestMapping("/image")
public class ImageController {

	// visto che c'è molta logica invece di usare direttamente il repo nel
	// controller
	// creiamo una classe SERVICE che ha i metodi che ci servono e si occupa della
	// logica

	@Autowired // fase intermedia che ci serve per le immagini
	private ImageServices service;

	// crea la view con la lista delle immagini collegate a una pizza e la form per
	// aggiungerne una

	@GetMapping("/{pizzaId}")
	public String pizzaImages(@PathVariable("pizzaId") Integer pizzaId, Model model) {
		// chiedo al service la lista delle immagini legate a quella pizzaId
		List<Image> image = service.getImageByPizzaId(pizzaId);

		// chiedo al service di istanziare una ImageForm iniliazzata con quella pizza
		ImageForm ImageForm = service.createImageForm(pizzaId);

		model.addAttribute("imageList", image);
		model.addAttribute("imageForm", ImageForm);
		return "/image/list";

	}

	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		// devo salvare l'immagine su database

		try {
			Image imageSave = service.createImage(imageForm);
			return "redirect:/image/" + imageSave.getPizza().getId();
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "unable to save image");
		}
	}
}
