package mapper;

import dto.ProductResponseDTO;
import entity.Product;

public final class ProductResponseMapper {

    private ProductResponseMapper() {
    }

    public static ProductResponseDTO mapToProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName());

    }


}
