package repository;

import config.DatabaseConfig;
import entity.WeightRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
