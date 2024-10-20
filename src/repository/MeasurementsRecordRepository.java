package repository;

import config.DatabaseConfig;
import entity.MeasurementsRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MeasurementsRecordRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addMeasurementsRecord(MeasurementsRecord measurementsRecord) {
        String query = "INSERT INTO measurements_records (user_id, shoulder, chest, biceps, " +
                "waist, hips, thigh, calf, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, measurementsRecord.getUser().getId());
            statement.setDouble(2, measurementsRecord.getShoulder());
            statement.setDouble(3, measurementsRecord.getChest());
            statement.setDouble(4, measurementsRecord.getBiceps());
            statement.setDouble(5, measurementsRecord.getWaist());
            statement.setDouble(6, measurementsRecord.getHips());
            statement.setDouble(7, measurementsRecord.getThigh());
            statement.setDouble(8, measurementsRecord.getCalf());
            statement.setDate(9, java.sql.Date.valueOf(measurementsRecord.getDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating new Measurements Record: " + e.getMessage());
        }
    }


    public List<MeasurementsRecord> findAllByUserId(Long userId) {
        String query = "SELECT shoulder, chest, biceps, waist, hips, thigh, calf, date " +
                "FROM measurements_records WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            List<MeasurementsRecord> measurementsRecords = new ArrayList<>();

            while (resultSet.next()) {
                MeasurementsRecord measurementRecord = new MeasurementsRecord(
                        resultSet.getDouble("shoulder"),
                        resultSet.getDouble("chest"),
                        resultSet.getDouble("biceps"),
                        resultSet.getDouble("waist"),
                        resultSet.getDouble("hips"),
                        resultSet.getDouble("thigh"),
                        resultSet.getDouble("calf"),
                        resultSet.getDate("date").toLocalDate()
                );
                measurementsRecords.add(measurementRecord);
            }

            return measurementsRecords;

        } catch (SQLException e) {
            System.out.println("Error retrieving measurement records: " + e.getMessage());
            return List.of();
        }
    }

    public MeasurementsRecord getLastMeasurementByUserId(Long userId) {
        String query = "SELECT shoulder, chest, biceps, waist, hips, thigh, calf, date " +
                "FROM measurements_records WHERE user_id = ? ORDER BY date DESC, id DESC LIMIT 1";

        MeasurementsRecord measurementsRecord = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    measurementsRecord = new MeasurementsRecord();
                    measurementsRecord.setShoulder(resultSet.getDouble("shoulder"));
                    measurementsRecord.setChest(resultSet.getDouble("chest"));
                    measurementsRecord.setBiceps(resultSet.getDouble("biceps"));
                    measurementsRecord.setWaist(resultSet.getDouble("waist"));
                    measurementsRecord.setHips(resultSet.getDouble("hips"));
                    measurementsRecord.setThigh(resultSet.getDouble("thigh"));
                    measurementsRecord.setCalf(resultSet.getDouble("calf"));
                    measurementsRecord.setDate(resultSet.getDate("date").toLocalDate());
                }
            }
        } catch (SQLException e) {
            System.out.println("MeasurementsRecord not found");
        }
        return measurementsRecord;
    }


}
