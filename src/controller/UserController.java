package controller;

import client.PasswordUtils;
import client.UserSession;
import dto.*;
import service.UserService;
import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public Long login(String username, String password){
        String hashedPassword = PasswordUtils.hashPassword(password);
        Long loggedUserId = userService.login(new LoginDTO(username, hashedPassword));

        if (loggedUserId != null) {
            UserSession.setLoggedInUserId(loggedUserId);
        }
        return loggedUserId;
    }

    public void registerUser(RegisterDTO registerDTO) {
        userService.register(registerDTO);
    }

    public UserDTO displayUserProfile(Long userId) {
        return userService.getUserById(userId);
    }

    public void updateHeight(Long userId, int newHeight) {
        userService.updateHeight(userId, newHeight);
    }

    public void updateAge(Long userId, int newAge) {
        userService.updateAge(userId, newAge);
    }

    public void updateWeight(Long userId, double newWeight) {
        userService.updateWeight(userId, newWeight);
    }

    public List<WeightRecordDTO> getWeightRecords(Long userId) {
        return userService.getWeightRecords(userId);
    }

    public void addMeasurement(Long userId, UpdateUserMeasurementsRequestDTO measurements) {
        userService.addMeasurement(userId, measurements);
    }

    public List<MeasurementsRecordDTO> getAllUserMeasurements(Long userId) {
        return userService.getMeasurementsByUser(userId);
    }

    public MeasurementsRecordDTO getLastUserMeasurement(Long userId) {
        return userService.getLatestMeasurement(userId);
    }
}
