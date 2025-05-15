package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Merce;

public class MerceDao {
     public List<Merce> getAllMerce() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Merce> merceList = new ArrayList<>();
            String sql = "SELECT * FROM merce";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Merce merce = new Merce(rs.getInt("id"), rs.getString("tipologia_merce"));
                merceList.add(merce);
            }
            return merceList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
