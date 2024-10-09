package entity;

import java.time.LocalDate;

public class MeasurementsRecord {
    private Long id;
    private Users user;
    private double shoulder;
    private double chest;
    private double biceps;
    private double waist;
    private double hips;
    private double thigh;
    private double calf;
    private LocalDate date;



    public MeasurementsRecord() {
    }

    public MeasurementsRecord( double shoulder, double chest, double biceps,
                              double waist, double hips, double thigh, double calf, LocalDate date) {

        this.shoulder = shoulder;
        this.chest = chest;
        this.biceps = biceps;
        this.waist = waist;
        this.hips = hips;
        this.thigh = thigh;
        this.calf = calf;
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getShoulder() {
        return shoulder;
    }

    public void setShoulder(double shoulder) {
        this.shoulder = shoulder;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getBiceps() {
        return biceps;
    }

    public void setBiceps(double biceps) {
        this.biceps = biceps;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getCalf() {
        return calf;
    }

    public void setCalf(double calf) {
        this.calf = calf;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}


