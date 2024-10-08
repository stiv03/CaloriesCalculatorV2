package entity;

import entity.enums.ProductType;

public class Product {
    private Long id;
    private String name;
    private ProductType productType;
    private double caloriesPer100Grams;
    private double proteinPer100Grams;
    private double fatPer100Grams;
    private double carbsPer100Grams;

    public Product(Long id, String name, ProductType productType, double caloriesPer100Grams,
                   double proteinPer100Grams, double fatPer100Grams, double carbsPer100Grams) {
        this.id = id;
        this.name = name;
        this.productType = productType;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.proteinPer100Grams = proteinPer100Grams;
        this.fatPer100Grams = fatPer100Grams;
        this.carbsPer100Grams = carbsPer100Grams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public void setCaloriesPer100Grams(double caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public double getProteinPer100Grams() {
        return proteinPer100Grams;
    }

    public void setProteinPer100Grams(double proteinPer100Grams) {
        this.proteinPer100Grams = proteinPer100Grams;
    }

    public double getFatPer100Grams() {
        return fatPer100Grams;
    }

    public void setFatPer100Grams(double fatPer100Grams) {
        this.fatPer100Grams = fatPer100Grams;
    }

    public double getCarbsPer100Grams() {
        return carbsPer100Grams;
    }

    public void setCarbsPer100Grams(double carbsPer100Grams) {
        this.carbsPer100Grams = carbsPer100Grams;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productType=" + productType +
                ", caloriesPer100Grams=" + caloriesPer100Grams +
                ", proteinPer100Grams=" + proteinPer100Grams +
                ", fatPer100Grams=" + fatPer100Grams +
                ", carbsPer100Grams=" + carbsPer100Grams +
                '}';
    }
}
