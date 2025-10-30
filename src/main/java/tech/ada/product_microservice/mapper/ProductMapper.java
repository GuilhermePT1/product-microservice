package tech.ada.product_microservice.mapper;

import org.springframework.stereotype.Component;
import tech.ada.product_microservice.dto.ProductDTO;
import tech.ada.product_microservice.model.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(product.getSku());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }


}
