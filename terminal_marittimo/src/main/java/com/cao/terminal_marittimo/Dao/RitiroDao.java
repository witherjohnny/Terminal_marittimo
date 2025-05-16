package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Autista;
import com.cao.terminal_marittimo.Models.Buono_consegna;
import com.cao.terminal_marittimo.Models.Camion;
import com.cao.terminal_marittimo.Models.Cliente;
import com.cao.terminal_marittimo.Models.Fornitore;
import com.cao.terminal_marittimo.Models.Guida;
import com.cao.terminal_marittimo.Models.Merce;
import com.cao.terminal_marittimo.Models.Polizza;
import com.cao.terminal_marittimo.Models.Ritiro;
import com.cao.terminal_marittimo.Models.Viaggio;

public class RitiroDao {
    public boolean richiediRitiro(int id_buono ,int id_guida) {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "INSERT INTO ritiro (id_buono, id_guida, data) VALUES (?, ?, ?)";
            var stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id_buono);
            stmt.setInt(2, id_guida);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Ritiro> getAllNotApprovedRitiri(){
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Ritiro> ritiri = new ArrayList<>();
            String sql = "SELECT b.id, b.id_buono, b.id_guida, b.data, " +
                         "bc.id_cliente, bc.peso AS buono_peso, " +
                         "p.id AS id_polizza, p.id_viaggio, p.id_fornitore, p.peso AS polizza_peso, p.id_merce, p.durata_franchigia, p.costo_franchigia, " +
                         "v.id AS id_viaggio, v.id_nave, v.data_partenza, v.id_porto_partenza, v.id_porto_arrivo, v.data_allibramento, " +
                         "m.id AS id_merce, m.tipologia_merce, " +
                         "f.id AS id_fornitore, f.nome, " +
                         "g.id AS id_guida, g.id_camion, g.id_autista, " +
                         "c.id AS id_camion, c.targa AS targa_camion, " +
                         "a.id AS id_autista, a.nome AS nome_autista, a.cognome AS cognome_autista, " +
                         "cl.id AS cliente_id, cl.nome AS cliente_nome, cl.cognome AS cliente_cognome, cl.id_utente AS cliente_id_utente " +
                         "FROM ritiro AS b " +
                         "JOIN buono_consegna AS bc ON b.id_buono = bc.id " +
                         "JOIN polizza AS p ON bc.id_polizza = p.id " +
                         "JOIN viaggio AS v ON p.id_viaggio = v.id " +
                         "JOIN merce AS m ON p.id_merce = m.id " +
                         "JOIN fornitore AS f ON p.id_fornitore = f.id " +
                         "JOIN guida AS g ON b.id_guida = g.id " +
                         "JOIN camion AS c ON g.id_camion = c.id " +
                         "JOIN autista AS a ON g.id_autista = a.id " +
                         "JOIN cliente AS cl ON bc.id_cliente = cl.id " +
                         "WHERE b.approvato = 0";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Ritiro ritiro = new Ritiro(
                    rs.getInt("id"),
                    new Buono_consegna(
                        rs.getInt("id_buono"),
                        rs.getFloat("buono_peso"),
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
                            rs.getFloat("polizza_peso"),
                            new Merce(
                                rs.getInt("id_merce"),
                                rs.getString("tipologia_merce")
                            ),
                            rs.getInt("durata_franchigia"),
                            rs.getFloat("costo_franchigia")
                        )
                    ),
                    new Guida(
                        rs.getInt("id_guida"),
                        new Camion(
                            rs.getInt("id_camion"),
                            rs.getString("targa_camion")
                        ),
                        new Autista(
                            rs.getInt("id_autista"),
                            rs.getString("nome_autista"),
                            rs.getString("cognome_autista"),
                            new Cliente(
                                rs.getInt("cliente_id"),
                                rs.getString("cliente_nome"),
                                rs.getString("cliente_cognome"),
                                rs.getInt("cliente_id_utente")
                            )
                        )
                    ),
                    rs.getString("data")
                );
                ritiri.add(ritiro);
            }
            return ritiri;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
    public boolean approvaRitiro(int id) {
        String sql = "UPDATE ritiro SET approvato = 1 WHERE id = " + id;
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD);
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteRitiro(int id) {
        String sql = "DELETE FROM ritiro WHERE id = " + id;
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
