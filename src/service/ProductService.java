package service;

import dto.ProductDTO;
import entity.Product;
import mapper.ProductMapper;
import repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public ProductDTO addNewProduct(ProductDTO productDTO){
        var newProduct = new Product();

        newProduct.setProductType(productDTO.productType());
        newProduct.setCaloriesPer100Grams(productDTO.caloriesPer100Grams());
        newProduct.setProteinPer100Grams(productDTO.proteinPer100Grams());
        newProduct.setFatPer100Grams(productDTO.fatPer100Grams());
        newProduct.setCarbsPer100Grams(productDTO.carbsPer100Grams());

        productRepository.addNewProduct(newProduct);

        return ProductMapper.mapToProductDTO(newProduct);
    }


    public List<ProductDTO> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query).stream().map(ProductMapper::mapToProductDTO).toList();
    }
}


