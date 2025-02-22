package client.command.product;

import client.command.MenuCommand;
import controller.ProductController;
import dto.ProductResponseDTO;
import view.ProductView;
import java.util.List;

public class SearchProductCommand implements MenuCommand {
    private final ProductController productController;
    private final ProductView productView;

    public SearchProductCommand(ProductController productController, ProductView productView) {
        this.productController = productController;
        this.productView = productView;
    }

    @Override
    public void execute() {
        String query = productView.getProductSearchQuery();
        List<ProductResponseDTO> products = productController.searchProducts(query);
        if (products != null && !products.isEmpty()) {
            productView.displayProducts(products);
        } else {
            productView.displayMessage("No products found.");
        }
    }
}