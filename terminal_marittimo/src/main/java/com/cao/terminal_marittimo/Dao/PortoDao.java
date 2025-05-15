package com.cao.terminal_marittimo.Dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Porto;

public class PortoDao {
    

    
    public List<Porto> getAllPorti() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Porto> porti = new ArrayList<>();
            String sql = "SELECT * FROM porto";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Porto porto = new Porto(rs.getInt("id"), rs.getString("nome"),rs.getString("nazionalita"));
                porti.add(porto);
            }
            
            return porti;
            
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
