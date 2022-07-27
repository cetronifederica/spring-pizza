package jana60.Model;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Non esistono pizze senza nome")
	@Column(nullable = false)
	private String name;

	@Lob
	private String description;

	@NotNull
	@DecimalMin("0.00")
	@DecimalMax("100.00")
	private Double price;

	@ManyToMany
	@JoinTable(name = "pizza_ingredienti", joinColumns = { @JoinColumn(name = "pizza_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ingredienti_id") })
	private List<Ingredienti> ingredienti;

	// getter e setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getFormattedPrice(Double price) {
		DecimalFormat df = new DecimalFormat("##.00€");

		return df.format(price);
	}

	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}

}
