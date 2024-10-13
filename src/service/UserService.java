package service;

import dto.*;
import entity.MeasurementsRecord;
import entity.Users;
import entity.WeightRecord;
import entity.enums.UserType;
import mapper.MeasurementsRecordMapper;
import mapper.WeightRecordMapper;
import repository.MeasurementsRecordRepository;
import repository.UserRepository;
import repository.WeightRecordRepositiry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository usersRepository = new UserRepository();
    private final WeightRecordRepositiry weightRecordRepositiry = new WeightRecordRepositiry();
    private final MeasurementsRecordRepository measurementsRecordRepository = new MeasurementsRecordRepository();

    public Long login(LoginDTO loginDTO) {
        String username = loginDTO.username();
        String password = loginDTO.password();

        return usersRepository.login(username, password);
    }

    public void register(RegisterDTO registerDTO) {
        Users user = new Users(
                registerDTO.name(),
                registerDTO.age(),
                registerDTO.weight(),
                registerDTO.height(),
                registerDTO.username(),
                registerDTO.password(),
                UserType.USER
        );

        usersRepository.registerUser(user);
    }


    public UserDTO getUserById(final long userId) {
        Users user = usersRepository.getUserById(userId);

        return new UserDTO(
                user.getName(),
                user.getAge(),
                user.getWeight(),
                user.getHeight(),
                user.getUserType()
        );
    }

    public void updateHeight(final long userId, final int newHeight) {
        usersRepository.updateUserHeight(userId, newHeight);
    }

    public void updateAge(final long userId, final int newAge) {
        usersRepository.updateUserAge(userId, newAge);
    }

    public void deleteUserById(final long userId) {
        usersRepository.deleteByUserID(userId);
    }

    public void updateWeight(final long userId, final double newWeight) {
        final var user = usersRepository.getUserById(userId);

        var weightRecord = new WeightRecord();
        weightRecord.setUser(user);
        weightRecord.setWeight(newWeight);
        weightRecord.setDate(LocalDate.now());
        weightRecordRepositiry.addWeightRecord(weightRecord);
        usersRepository.updateUserWeight(userId, newWeight);

    }

    public List<WeightRecordDTO> getWeightRecords(final Long userId) {


        return weightRecordRepositiry.getWeightRecords(userId).stream()
                .map(WeightRecordMapper::toDTO).toList();
    }

    public void addMeasurement(final Long userId, UpdateUserMeasurementsRequestDTO requestDTO) {
        final var user = usersRepository.getUserById(userId);
        var measurementsRecord = new MeasurementsRecord();
        measurementsRecord.setUser(user);
        measurementsRecord.setShoulder(requestDTO.shoulder());
        measurementsRecord.setChest(requestDTO.chest());
        measurementsRecord.setBiceps(requestDTO.biceps());
        measurementsRecord.setWaist(requestDTO.waist());
        measurementsRecord.setHips(requestDTO.hips());
        measurementsRecord.setThigh(requestDTO.thigh());
        measurementsRecord.setCalf(requestDTO.calf());
        measurementsRecord.setDate(LocalDate.now());

        measurementsRecordRepository.addMeasurementsRecord(measurementsRecord);

    }

    public List<MeasurementsRecordDTO> getMeasurementsByUser(final Long userId) {
        return measurementsRecordRepository.findAllByUserId(userId).stream()
                .map(MeasurementsRecordMapper::toDTO).toList();
    }

    public MeasurementsRecordDTO getLatestMeasurement(final Long userId) {
        return MeasurementsRecordMapper.toDTO(measurementsRecordRepository.getLastMeasurementByUserId(userId));
    }
}
