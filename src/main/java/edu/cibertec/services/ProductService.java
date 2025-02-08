package edu.cibertec.services;

import java.util.List;
import java.util.Optional;
import edu.cibertec.entities.Product;

public interface ProductService {
	
	List<Product> findAllOrFilterByName(String name);
	Optional<Product> findById(Long id);
	Product save(Product product);
	Product udpate(Long id, Product product);
	void delete(Long id);

}
