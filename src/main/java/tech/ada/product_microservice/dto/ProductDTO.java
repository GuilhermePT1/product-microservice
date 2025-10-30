package tech.ada.product_microservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "O sku nao pode ser nulo")
    @Positive(message = "O campo so aceita numero positivos acima de zero")
    private Long sku;

    private String description;

    @Positive(message = "O preco deve ser maior que zero")
    private BigDecimal price;

}
