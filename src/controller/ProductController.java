package controller;

import dto.ProductDTO;
import dto.ProductResponseDTO;
import service.ProductService;
import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();

    public void addNewProduct(ProductDTO productDTO) {
        productService.addNewProduct(productDTO);
    }

    public List<ProductResponseDTO> searchProducts(String query) {
        return productService.searchProducts(query);
    }
}
