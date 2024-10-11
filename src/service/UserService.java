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

import javax.xml.stream.events.DTD;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository usersRepository = new UserRepository();
    private final WeightRecordRepositiry weightRecordRepositiry = new WeightRecordRepositiry();
    private final MeasurementsRecordRepository measurementsRecordRepository = new MeasurementsRecordRepository();

    public Long login(LoginDTO loginDTO) throws SQLException {
        String username = loginDTO.username();
        String password = loginDTO.password();

        return usersRepository.login(username, password);
    }

    public void register(RegisterDTO registerDTO) throws SQLException {
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

    public void updateAge(final long userId, final int newAge){
        usersRepository.updateUserAge(userId,newAge);
    }

    public void deleteUserById(final long userId){
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

        user.getWeightRecords().add(weightRecord);
    }

    public List<WeightRecordDTO> getWeightRecords(final Long userId) {
        final var user = usersRepository.getUserById(userId);
        return user.getWeightRecords().stream().map(WeightRecordMapper::toDTO).toList();
    }

    public void addMeasurement(final Long userId, UpdateUserMeasurementsRequestDTO requestDTO) {
        final var user =  usersRepository.getUserById(userId);
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
        List<MeasurementsRecordDTO> measurementsList = new ArrayList<>();
        final var measurementsRecord =  measurementsRecordRepository.findByUserId(userId);

        MeasurementsRecordDTO dto = MeasurementsRecordMapper.toDTO(measurementsRecord);
        measurementsList.add(dto);

        return measurementsList;
    }

    public MeasurementsRecordDTO getLatestMeasurement(final Long userId) {
        return MeasurementsRecordMapper.toDTO(measurementsRecordRepository.getLastMeasurementByUserId(userId));
    }
}
