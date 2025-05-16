package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cao.terminal_marittimo.DbConnection;

public class GuidaDao {

    public int addGuida(String targa, int id_autista) {
        String sql = "INSERT INTO guida (id_camion, id_autista) VALUES ((SELECT id FROM camion WHERE targa = ?), ?)";
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, targa);
            stmt.setInt(2, id_autista);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
            return -1;
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
