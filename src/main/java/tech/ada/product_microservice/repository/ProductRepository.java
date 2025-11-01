package tech.ada.product_microservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(Long sku);
    Product findByDescriptionContainingAndPrice(String description, BigDecimal price);

    @Query(value = "SELECT * FROM tb_products where sku = :sku", nativeQuery = true)
    Product superQuery(@Param("sku") Long sku);

    @Query(value = "SELECT p FROM Product p WHERE p.sku = :sku")
    Product superQuery2(@Param("sku") Long sku);

    //DELETE
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_PRODUCTS p WHERE p.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    //UPDATE
    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_PRODUCTS p set p.price = :price WHERE p.id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("price") BigDecimal price);

    List<Product> searchByDescription(@Param("description") String description);

    List<Product> searchBySku(@Param("sku") Long sku);

    List<Product> searchProducts(String description, BigDecimal minPrice, BigDecimal maxPrice);
}