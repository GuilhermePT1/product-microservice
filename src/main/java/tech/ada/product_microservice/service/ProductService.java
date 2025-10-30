package tech.ada.product_microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.dto.ProductDTO;
import tech.ada.product_microservice.mapper.ProductMapper;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Cacheable("produtos")
    public List<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> allProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product getProductBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

    public ProductDTO create(ProductDTO productDTO) {
        Product product = this.productMapper.toEntity(productDTO);
        return this.productMapper.toDTO(this.productRepository.save(product));
    }

    public Product partialUpdate(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);
        this.productRepository.updateProduct(productBySku.getId(), product.getPrice());
        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        product.setDescription(productBySku.getDescription());
        return product;
    }

    public Product updateProduct(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long sku) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        //this.productRepository.delete(productBySku);
        this.productRepository.deleteById(productBySku.getId());
    }

    public List<Product> searchByDescription(String description) {
        return this.productRepository.searchByDescription(description);
    }

    public Product searchBySku(Long sku) {
        return this.productRepository.searchBySku(sku).stream().findFirst().orElse(null);
    }
}
