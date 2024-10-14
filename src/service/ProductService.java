package service;

import dto.ProductDTO;
import dto.ProductResponseDTO;
import entity.Product;
import mapper.ProductResponseMapper;
import repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public void addNewProduct(ProductDTO productDTO) {
        var newProduct = new Product();

        newProduct.setName(productDTO.name());
        newProduct.setProductType(productDTO.productType());
        newProduct.setCaloriesPer100Grams(productDTO.caloriesPer100Grams());
        newProduct.setProteinPer100Grams(productDTO.proteinPer100Grams());
        newProduct.setFatPer100Grams(productDTO.fatPer100Grams());
        newProduct.setCarbsPer100Grams(productDTO.carbsPer100Grams());

        productRepository.addNewProduct(newProduct);
    }

    public List<ProductResponseDTO> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query).stream().map(ProductResponseMapper::mapToProductResponseDTO).toList();
    }
}


