package entity;

import entity.enums.UserType;

public class Users {

    private Long id;
    private String name;
    private final int age;
    private double weight;
    private final int height;
    private String username;
    private String password;
    private final UserType userType;

    public Users(String name, int age, double weight, int height, String username, String password, UserType userType) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Users(Long id, String name, int age, double weight, int height, UserType userType) {
        this.id = id;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
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
