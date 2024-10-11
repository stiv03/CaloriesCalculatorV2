package dto;

import entity.enums.UserType;

public record RegisterDTO(String name, int age, double weight, int height, String username, String password, UserType userType) {

}
