package jana60.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.Model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

	public Integer countByName(String name);

	public List<Pizza> findByNameContaining(String query);
}
