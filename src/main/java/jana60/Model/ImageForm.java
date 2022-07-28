package jana60.Model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

	// IMAGE FORM è il traspoetatore

	private Integer id;

	private Pizza pizza;

	private MultipartFile contenutoMultipart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public MultipartFile getContenutoMultipart() {
		return contenutoMultipart;
	}

	public void setContenutoMultipart(MultipartFile contenutoMultipart) {
		this.contenutoMultipart = contenutoMultipart;
	}

}
