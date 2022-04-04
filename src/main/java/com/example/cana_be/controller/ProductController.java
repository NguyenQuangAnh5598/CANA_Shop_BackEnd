package com.example.cana_be.controller;

import com.example.cana_be.model.Product;
import com.example.cana_be.repository.IProductRepo;
import com.example.cana_be.service.extend.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<?> showAllProduct() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @GetMapping("/page")
    public ResponseEntity<?> showAllProductByPage(@PageableDefault(value = 5) Pageable pageable) {
        Page<Product> productList = productService.findAllProduct(pageable);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/findProductByCategory")
    public ResponseEntity<?> findProductByCategoryName(@RequestParam String name) {
        List<Product> productList = productService.findProductByCategoryName(name);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(product.getId());
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("byPrice")
    public ResponseEntity<List<Product>> getProductByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Product> list = productService.findProductByPrice(minPrice, maxPrice);
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @GetMapping("/findbyname")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam  String name) {
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }
    @GetMapping("/top3BestSale")
    public  ResponseEntity<List<Product>> top3BestSale(){
        return new ResponseEntity<>(productService.top3BestSale(),HttpStatus.OK);
    }
}
