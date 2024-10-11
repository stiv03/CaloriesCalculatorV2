package entity;

import entity.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private Long id;
    private String name;
    private int age;
    private double weight;
    private int height;
    private String username;
    private String password;
    private UserType userType;

    private List<WeightRecord> weightRecords = new ArrayList<>();

    public Users(String name, int age, double weight, int height, String username, String password, UserType userType) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Users(String name, int age, double weight, int height, UserType userType) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.userType = userType;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<WeightRecord> getWeightRecords() {
        return weightRecords;
    }

    public void setWeightRecords(List<WeightRecord> weightRecords) {
        this.weightRecords = weightRecords;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
