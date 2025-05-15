package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Fornitore;

public class FornitoreDao {
     public List<Fornitore> getAllFornitori() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Fornitore> fornitori = new ArrayList<>();
            String sql = "SELECT * FROM fornitore";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Fornitore fornitore = new Fornitore(rs.getInt("id"), rs.getString("nome"));
                fornitori.add(fornitore);
            }
            
            return fornitori;
            
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
