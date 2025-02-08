package edu.cibertec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.cibertec.entities.Product;
import edu.cibertec.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAllOrFilterByName(String name) {
		Optional<String> optionalName = Optional.ofNullable(name);
		if (optionalName.isPresent()) {
			return productRepository.findByNameLike("%"+name+"%");
		}
		return productRepository.findByState(true);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product product) {
		product.setState(true);
		return productRepository.save(product);
	}

	@Override
	public Product udpate(Long id, Product product) {
		Optional<Product> oProduct = productRepository.findById(id);
		if (oProduct.isPresent()) {
			Product productToUpdate = oProduct.get();
			productToUpdate.setName(product.getName());
			productToUpdate.setDescription(product.getDescription());
			productToUpdate.setStock(product.getStock());
			productToUpdate.setPrice(product.getPrice());
			productToUpdate.setImage(product.getImage());
			return productRepository.save(productToUpdate);
		}
		return productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		Product oProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		oProduct.setState(false);
		productRepository.save(oProduct);
	}

}
