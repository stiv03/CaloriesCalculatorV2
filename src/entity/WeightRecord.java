package entity;

import java.time.LocalDate;

public class WeightRecord {
    private Long id;
    private Users user;
    private double weight;
    private LocalDate date;

    public WeightRecord(Users user, double weight, LocalDate date) {
        this.user = user;
        this.weight = weight;
        this.date = date;
    }

    public WeightRecord() {
    }

    public WeightRecord(double weight, LocalDate date) {
        this.weight = weight;
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}


