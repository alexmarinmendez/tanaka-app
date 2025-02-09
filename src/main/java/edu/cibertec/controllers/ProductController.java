package edu.cibertec.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cibertec.entities.Product;
import edu.cibertec.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/")
	public String findAllOrFilterByName(@RequestParam(required = false) String name, Model model) {
		List<Product> products = productService.findAllOrFilterByName(name);
		model.addAttribute("datos", products);
		return "productos";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {
		Optional<Product> product = productService.findById(id);
		model.addAttribute("producto", product.get());
		return "detalle-producto";
	}
	
	@GetMapping("/nuevo")
	public String saveProduct(Model model) {
		model.addAttribute("producto", new Product()); // Objeto vacío para el formulario
		return "formulario-nuevo";
	}
	
}
