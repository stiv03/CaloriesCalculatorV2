package client.command.product;

import client.command.MenuCommand;
import controller.ProductController;
import dto.ProductDTO;
import view.ProductView;

public class AddProductCommand implements MenuCommand {
    private final ProductController productController;
    private final ProductView productView;

    public AddProductCommand(ProductController productController, ProductView productView) {
        this.productController = productController;
        this.productView = productView;
    }

    @Override
    public void execute() {
        ProductDTO productDTO = productView.getProductInput();
        productController.addNewProduct(productDTO);
        productView.displayMessage("Product added successfully.");
    }
}