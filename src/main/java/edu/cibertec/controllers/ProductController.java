package edu.cibertec.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String showForm(Model model) {
		model.addAttribute("producto", new Product()); // Objeto vacío para el formulario
		model.addAttribute("titulo", "Registrar Producto");
        model.addAttribute("action", "/guardar");
        model.addAttribute("textoBoton", "Guardar");
		return "formulario-nuevo";
	}
	
	@PostMapping("/guardar")
	public String saveProduct(Product product) {
		productService.save(product);
        return "redirect:/"; // Redirige a la lista de productos
	}
	
    // Mostrar el formulario de eliminación
    @GetMapping("/eliminar/{id}")
    public String showFormToDelete(@PathVariable Long id, Model model) {
    	Optional<Product> product = productService.findById(id);
        model.addAttribute("producto", product.get());
		model.addAttribute("titulo", "Eliminar Producto");
        model.addAttribute("action", "/borrar");
        model.addAttribute("textoBoton", "Borrar");
        return "formulario-nuevo";
    }
    
    @PostMapping("/borrar")
    public String delete(@RequestParam Long id) {
    	productService.delete(id);
        return "redirect:/"; // Redirige a la lista de productos
    }
    
    // Mostrar el formulario de editar
    @GetMapping("/editar/{id}")
    public String showFormToUpdate(@PathVariable Long id, Model model) {
    	Optional<Product> product = productService.findById(id);
        model.addAttribute("producto", product.get());
		model.addAttribute("titulo", "Actualizar Producto");
        model.addAttribute("action", "/actualizar");
        model.addAttribute("textoBoton", "Actualizar");
        return "formulario-nuevo";
    }
    
    @PostMapping("/actualizar")
    public String update(@RequestParam Long id, Product product) {
    	productService.update(id, product);
        return "redirect:/"; // Redirige a la lista de productos
    }
}
