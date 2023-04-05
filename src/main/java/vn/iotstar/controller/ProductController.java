package vn.iotstar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService product;
	
	@GetMapping("list")
	public List<Product> getProductAll() {
		return product.findAll();
	}
	
	@GetMapping("/my/{barcode}")
	public ResponseEntity<Product> getProductByBarcode(@PathVariable("barcode") String barcode) {
		  List<Product> lists = product.getProductByBarcode(barcode);
		  if(lists != null) {
			  return new ResponseEntity<>(lists.get(0), HttpStatus.OK);
		  }
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	 }
	
	@PostMapping("get")
	public List<Product> getProductByBar(@RequestParam(name = "barcode", required = false) String barcode){
		return product.getProductByBarcode(barcode);
	}
	
	@PostMapping("getId")
	public Optional<Product> getProductById(@RequestParam(name = "id", required = false) String id){
		return product.findById(id);
	}
	
	@PostMapping("byCate")
	public List<Product> getProductByCategory(@RequestBody Category category){
		return product.findByCategory(category);
	}
	
}
