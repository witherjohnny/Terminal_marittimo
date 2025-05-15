package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Viaggio;

public class ViaggioDao {
    public int registra(int id_nave , String data, int id_porto_partenza){
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "INSERT INTO viaggio (id_nave, data_partenza,id_porto_partenza) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id_nave);
            stmt.setDate(2, java.sql.Date.valueOf(data));
            stmt.setInt(3, id_porto_partenza);
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
    public boolean updateArrivo(int viaggio , String data, int id_porto_arrivo){
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "UPDATE viaggio SET data_allibramento = ?, id_porto_arrivo = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(data));
            stmt.setInt(2, id_porto_arrivo);
            stmt.setInt(3, viaggio);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Viaggio> getAllViaggi() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "SELECT * FROM viaggio";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Viaggio> viaggi = new ArrayList<>();
            while (rs.next()) {
                Viaggio viaggio = new Viaggio(rs.getInt("id"), rs.getInt("id_nave"), rs.getDate("data_partenza"), rs.getInt("id_porto_partenza"));
                viaggi.add(viaggio);
            }
            return viaggi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
