package entity;

import java.time.LocalDate;

public class Meal {

    private Long id;
    private Users user;
    private Product product;
    private double quantity;
    private LocalDate consumedAt;

    public Meal(Long id, Users user, Product product, double quantity, LocalDate consumedAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.consumedAt = consumedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getConsumedAt() {
        return consumedAt;
    }

    public void setConsumedAt(LocalDate consumedAt) {
        this.consumedAt = consumedAt;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", quantity=" + quantity +
                ", consumedAt=" + consumedAt +
                '}';
    }
}
