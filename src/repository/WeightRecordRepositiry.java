package repository;

import config.DatabaseConfig;

import dto.WeightRecordDTO;
import entity.WeightRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeightRecordRepositiry {
    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addWeightRecord(WeightRecord weightRecord) {
        String query = "INSERT INTO weight_records (user_id, weight, date) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, weightRecord.getUser().getId());
            statement.setDouble(2, weightRecord.getWeight());
            statement.setDate(3, java.sql.Date.valueOf(weightRecord.getDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating new Weight Record");
        }
    }

    public List<WeightRecord> getWeightRecords(final Long userId) {
        String query = "SELECT * FROM weight_records WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            List<WeightRecord> weightRecords = new ArrayList<>();

            while (resultSet.next()) {
                WeightRecord weightRecord = new WeightRecord(
                        resultSet.getDouble("weight"),
                        resultSet.getDate("date").toLocalDate()

                );
                weightRecords.add(weightRecord);
            }

            return weightRecords;

        } catch (SQLException e) {
            System.out.println("Error retrieving weight records: " + e.getMessage());
            return List.of();
        }
    }
}
