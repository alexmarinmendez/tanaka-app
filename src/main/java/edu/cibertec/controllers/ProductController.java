package edu.cibertec.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
