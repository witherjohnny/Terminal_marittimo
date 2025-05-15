package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cao.terminal_marittimo.DbConnection;

public class PolizzaDao {
    public boolean registra(int viaggio,int fornitore, String peso, int merce, int durata_franchigia, int costo_franchigia) {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "INSERT INTO polizza (id_viaggio, id_fornitore, peso, id_merce, durata_franchigia, costo_franchigia) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, viaggio);
            stmt.setInt(2, fornitore);
            stmt.setString(3, peso);
            stmt.setInt(4, merce);
            stmt.setInt(5, durata_franchigia);
            stmt.setInt(6, costo_franchigia);
            int rowsInserted = stmt.executeUpdate();
    
            return  rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
