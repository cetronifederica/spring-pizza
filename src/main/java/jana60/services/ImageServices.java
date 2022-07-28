package jana60.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.Model.Image;
import jana60.Model.ImageForm;
import jana60.Model.Pizza;
import jana60.Repository.ImageRepository;
import jana60.Repository.PizzaRepository;

@Service
public class ImageServices {

	@Autowired
	private ImageRepository repo;

	@Autowired
	private PizzaRepository pizzaRepo;

	public List<Image> getImageByPizzaId(Integer pizzaId) {
		Pizza pizza = pizzaRepo.findById(pizzaId).get();
		return repo.findByPizza(pizza);
	}

	public ImageForm createImageForm(Integer pizzaId) {
		Pizza pizza = pizzaRepo.findById(pizzaId).get();
		ImageForm img = new ImageForm(); // ora è vuoto
		img.setPizza(pizza); //
		return img;
	}

	// a partire da un ogetto ImageForm deve creare un oggetto di tipo Image e
	// salvarlo su database

	public Image createImage(ImageForm imageForm) throws IOException {
		// creo un oggetto image vuoto
		Image imgSave = new Image();
		// lo inizializzo con i dati di imageForm
		imgSave.setPizza(imageForm.getPizza());
		// trasformo il contenuto MultiPartFile in un byte[] e lo passo all'image da
		// salvare
		if (imageForm.getContenutoMultipart() != null) {
			byte[] contenutoSerialized = imageForm.getContenutoMultipart().getBytes();
			imgSave.setContenuto(contenutoSerialized);
		}
		// salvo image sul database

		return repo.save(imgSave);
	}
}
