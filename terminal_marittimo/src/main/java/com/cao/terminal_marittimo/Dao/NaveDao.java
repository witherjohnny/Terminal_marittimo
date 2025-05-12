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
import com.cao.terminal_marittimo.Models.Nave;

public class NaveDao {
    

    
    public List<Nave> getTutteLeNavi() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Nave> navi = new ArrayList<>();
            String sql = "SELECT * FROM nave";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Nave nave = new Nave(rs.getInt("id"), rs.getString("nome"));
                navi.add(nave);
            }
            
            return navi;
            
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
