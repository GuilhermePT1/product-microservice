package tech.ada.product_microservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.product_microservice.dto.ProductDTO;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(this.productService.allProducts());
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<Product>> allProducts(Pageable pageable) {
        return ResponseEntity.ok(this.productService.allProducts(pageable));
    }

    //GET BY ID
    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable Long sku) {
        //return ResponseEntity.ok(this.productService.getProductBySku(sku));
        return ResponseEntity.ok(this.productService.searchBySku(sku));
    }

    @GetMapping("/search-by-description")
    public ResponseEntity<List<Product>> getProduct(@RequestParam("description") String description) {
        return ResponseEntity.ok(this.productService.searchByDescription(description));
    }

    //POST - CREATE
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
    }

    //PUT - UPDATE ALL
    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long sku,
                                                 @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.updateProduct(sku, product));
    }

    //PATCH - PARTIAL UPDATE
    @PatchMapping("/{sku}")
    public ResponseEntity<Product> partialUpdate(@PathVariable Long sku,
                                                 @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.partialUpdate(sku, product));
    }

    //DELETE - REMOVE
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku) {
        this.productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

}
