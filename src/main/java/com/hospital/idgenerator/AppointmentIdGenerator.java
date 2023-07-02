package com.hospital.idgenerator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AppointmentIdGenerator implements IdentifierGenerator {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final int INITIAL_VALUE = 1;
    private static final String name = "id_count";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "A";
        int id = getNextSequenceValue();
        String generatedId = prefix + String.format("%06d", id);
        return generatedId;
    }

    private int getNextSequenceValue() {
        createSequenceTableIfNotExists();
        String query = "SELECT appointment_id_count FROM " + name + " FOR UPDATE";
        Integer sequenceValue = jdbcTemplate.query(query, (rs) -> {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                initializeSequenceValue();
                return INITIAL_VALUE;
            }
        });
        updateSequenceValue(sequenceValue + 1);
        return sequenceValue;
    }

    private void createSequenceTableIfNotExists() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + name + " (appointment_id_count INT NOT NULL,patient_id_count INT NOT NULL,id INT NOT NULL)";
        jdbcTemplate.execute(createTableQuery);
    }

    private void initializeSequenceValue() {
        String insertQuery = "INSERT INTO " + name + " (id, appointment_id_count, patient_id_count) VALUES (1,?,1)";
        jdbcTemplate.update(insertQuery, INITIAL_VALUE);
    }

    private void updateSequenceValue(int newValue) {
        String updateQuery = "UPDATE " + name + " SET appointment_id_count = ?";
        jdbcTemplate.update(updateQuery, newValue);
    }
    
}

//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//public class AppointmentIdGenerator implements IdentifierGenerator {
//
//    private static final int INITIAL_VALUE = 1;
//    private static final String name = "appointment_id_count";
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//        String prefix = "A";
//        Connection connection = session.connection();
//
//        try {
//            int id = getNextSequenceValue(connection);
//            String generatedId = prefix + String.format("%06d", id);
//            return generatedId;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to generate appointment ID: " + e.getMessage());
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
////    private int getNextSequenceValue(Connection connection) throws SQLException {
////        createSequenceTableIfNotExists(connection);
////        String query = "SELECT appointment_id_count FROM " + name;
////        try (PreparedStatement statement = connection.prepareStatement(query)) {
////            try (ResultSet rs = statement.executeQuery()) {
////                if (rs.next()) {
////                    int currentValue = rs.getInt(1);
////                    int nextValue = currentValue + 1;
////                    updateSequenceValue(connection, nextValue);
////                    return currentValue;
////                } else {
////                    initializeSequenceValue(connection);
////                    return INITIAL_VALUE;
////                }
////            }
////        }
////    }
//    
//    private int getNextSequenceValue(Connection connection) throws SQLException {
//        createSequenceTableIfNotExists(connection);
//        String query = "SELECT appointment_id_count FROM " + name;
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    int currentValue = rs.getInt(1);
//                    int nextValue = currentValue + 1;
//                    updateSequenceValue(connection, nextValue);
//                    return currentValue;
//                } else {
//                    initializeSequenceValue(connection);
//                    return INITIAL_VALUE;
//                }
//            }
//        }
//    }
//
//
////    private void createSequenceTableIfNotExists(Connection connection) throws SQLException {
////        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + name + " (appointment_id_count INT NOT NULL)";
////        try (Statement statement = connection.createStatement()) {
////            statement.execute(createTableQuery);
////        }
////    }
//    
//    private void createSequenceTableIfNotExists(Connection connection) throws SQLException {
//        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + name + " (appointment_id_count INT NOT NULL)";
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(createTableQuery);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create appointment ID sequence table: " + e.getMessage());
//        }
//    }
//
//
//    private void initializeSequenceValue(Connection connection) throws SQLException {
//        String insertQuery = "INSERT INTO " + name + " (appointment_id_count) VALUES (?)";
//        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
//            statement.setInt(1, INITIAL_VALUE);
//            statement.executeUpdate();
//        }
//    }
//
//    private void updateSequenceValue(Connection connection, int newValue) throws SQLException {
//        String updateQuery = "UPDATE " + name + " SET appointment_id_count = ?";
//        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
//            statement.setInt(1, newValue);
//            statement.executeUpdate();
//        }
//    }
//}
