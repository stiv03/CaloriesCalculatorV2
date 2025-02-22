package dto;

import entity.enums.UserType;

public record UserDTO(String name, int age, double weight, int height, UserType userType) {
}
