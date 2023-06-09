package vn.iotstar.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	ProductRepository proRepo;
	
	@Autowired
	CategoryService categoryService;

	public <S extends Product> S save(S entity) {
		
		return proRepo.save(entity);
	}

	public List<Product> findByCategory(Category category) {
		return proRepo.findByCategory(category);
	}

	public List<Product> findByIsselling(Boolean isselling) {
		return proRepo.findByIsselling(isselling);
	}

	public List<Product> findByQuantity(Integer quantity) {
		return proRepo.findByQuantity(quantity);
	}

	public <S extends Product> S insert(S entity) {
		return proRepo.insert(entity);
	}

	public List<Product> findAll() {
		return proRepo.findAll();
	}

	public Optional<Product> findById(String id) {
		return proRepo.findById(id);
	}

	public long count() {
		return proRepo.count();
	}

	public void deleteById(String id) {
		proRepo.deleteById(id);
	}

	public void delete(Product entity) {
		proRepo.delete(entity);
	}

	public void deleteAll() {
		proRepo.deleteAll();
	}
	
	public List<Product> getProductByBarcode(String barcode){
		return proRepo.findByBarcode(barcode);
	}

	public List<Product> findByCategory(String name ) {
		List<Category> categories = categoryService.findByName(name);
		
		List<Product> list = proRepo.findAll();
		List<Product> entity = new ArrayList<>();
		
		if(categories.size() > 0) {
			list = proRepo.findByCategory(categories.get(0));
			for (Product product : list) {
				if(product.getIsselling()) {
					entity.add(product);
				}
				
			}
		}
		return entity;
	}

	public List<Product> findTop5ByOrderBySoldDesc() {
		return proRepo.findTop5ByOrderBySoldDesc();
	}

	
	public List<Product> findTop5Discount() {
	    List<Product> listAll = proRepo.findAll();

	    List<Product> top5DiscountProducts = listAll.stream()
	    		.filter(Product::getIsselling)
	            .sorted(new Comparator<Product>() {
	                @Override
	                public int compare(Product p1, Product p2) {
	                    double discount1 = (double) (p1.getPrice() - p1.getPromotionaprice()) / p1.getPrice();
	                    double discount2 = (double) (p2.getPrice() - p2.getPromotionaprice()) / p2.getPrice();

	                    return Double.compare(discount2, discount1);
	                }
	            })
	            .limit(5)
	            .collect(Collectors.toList());

	    return top5DiscountProducts;
	}

	public List<Product> findAllByOrderByCreateatDesc() {
		return proRepo.findTop5ByOrderByCreateatDesc();
	}
	

}
