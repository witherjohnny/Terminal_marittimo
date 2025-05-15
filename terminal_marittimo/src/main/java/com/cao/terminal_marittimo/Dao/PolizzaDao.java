package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Fornitore;
import com.cao.terminal_marittimo.Models.Merce;
import com.cao.terminal_marittimo.Models.Polizza;
import com.cao.terminal_marittimo.Models.Viaggio;

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
     public List<Polizza> getAllPolizze() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "SELECT * FROM polizza as p JOIN viaggio as v ON p.id_viaggio = v.id JOIN merce as m ON p.id_merce = m.id JOIN fornitore as f ON p.id_fornitore = f.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Polizza> polizze = new ArrayList<>();
            while (rs.next()) {
                Polizza polizza = new Polizza(
                    rs.getInt("id"),
                    new Viaggio(
                        rs.getInt("id_viaggio"),
                        rs.getInt("id_nave"),
                        rs.getDate("data_partenza"),
                        rs.getInt("id_porto_partenza"),
                        rs.getInt("id_porto_arrivo"),
                        rs.getDate("data_allibramento")
                    ),
                    new Fornitore(rs.getInt("id_fornitore"), rs.getString("nome")),
                    rs.getFloat("peso"),
                    new Merce(rs.getInt("id_merce"), rs.getString("tipologia_merce")),
                    rs.getInt("durata_franchigia"),
                    rs.getFloat("costo_franchigia")
                );

                polizze.add(polizza);
            }
            return polizze;
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return List.of();
    }
}
