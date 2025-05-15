package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import com.cao.terminal_marittimo.DbConnection;


public class ClienteDao {
    public boolean richiediBuono(int id_polizza, float peso, int idCliente) {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            // Check if polizza.peso is greater than buono peso
            String checkSql = "SELECT peso FROM polizza WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id_polizza);
            var rs = checkStmt.executeQuery();
            if (rs.next()) {
                float polizzaPeso = rs.getFloat("peso");
                if (polizzaPeso < peso) {
                    // Not enough peso in polizza
                    return false;
                }
            } else {
                // Polizza not found
                return false;
            }

            String sql = "INSERT INTO buono_consegna (peso, id_cliente, id_polizza) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, peso);
            stmt.setInt(2, idCliente);
            stmt.setInt(3, id_polizza);
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
