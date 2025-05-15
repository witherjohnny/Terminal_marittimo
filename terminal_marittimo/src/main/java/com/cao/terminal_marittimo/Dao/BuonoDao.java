package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Buono_consegna;
import com.cao.terminal_marittimo.Models.Fornitore;
import com.cao.terminal_marittimo.Models.Merce;
import com.cao.terminal_marittimo.Models.Polizza;
import com.cao.terminal_marittimo.Models.Viaggio;

public class BuonoDao {
    public List<Buono_consegna> getAllNotApprovedBuoni() {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Buono_consegna> buoni = new ArrayList<>();
            String sql = "SELECT * FROM buono_consegna AS b JOIN polizza AS p ON b.id_polizza = p.id JOIN viaggio as v ON p.id_viaggio = v.id JOIN merce as m ON p.id_merce = m.id JOIN fornitore as f ON p.id_fornitore = f.id WHERE b.approvato = 0";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Buono_consegna buono = new Buono_consegna(
                    rs.getInt("id"),
                    rs.getFloat("peso"),
                    rs.getInt("id_cliente"),
                    new Polizza(
                        rs.getInt("id_polizza"),
                        new Viaggio(
                            rs.getInt("id_viaggio"),
                            rs.getInt("id_nave"),
                            rs.getDate("data_partenza"),
                            rs.getInt("id_porto_partenza"),
                            rs.getInt("id_porto_arrivo"),
                            rs.getDate("data_allibramento")
                        ),
                        
                        new Fornitore(
                            rs.getInt("id_fornitore"),
                            rs.getString("nome")
                        ),
                        rs.getFloat("peso"),
                        new Merce(
                            rs.getInt("id_merce"),
                            rs.getString("tipologia_merce")
                        ),
                        rs.getInt("durata_franchigia"),
                        rs.getFloat("costo_franchigia")
                    )
                );
                buoni.add(buono);
            }
            return buoni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
    public boolean approvaBuono(int id) {
        String sql = "UPDATE buono_consegna SET approvato = 1 WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD);
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBuono(int id) {
        String sql = "DELETE FROM buono_consegna WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD);
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
