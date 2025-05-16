package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public List<Buono_consegna> getBuoni(int id_cliente) {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Buono_consegna> buoni = new ArrayList<>();
            String sql = "SELECT * FROM buono_consegna AS b JOIN polizza AS p ON b.id_polizza = p.id JOIN viaggio as v ON p.id_viaggio = v.id JOIN merce as m ON p.id_merce = m.id JOIN fornitore as f ON p.id_fornitore = f.id WHERE b.approvato = 1 AND b.id_cliente = ? AND id_autista IS NULL";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            ResultSet rs = stmt.executeQuery();
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

    public boolean assegnaBuono(int id, int id_autista){
         try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            String sql = "UPDATE buono_consegna SET id_autista = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_autista);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Buono_consegna> getBuoniAutista(int id_autista) {
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Buono_consegna> buoni = new ArrayList<>();
            String sql = "SELECT * FROM buono_consegna AS b JOIN polizza AS p ON b.id_polizza = p.id JOIN viaggio as v ON p.id_viaggio = v.id JOIN merce as m ON p.id_merce = m.id JOIN fornitore as f ON p.id_fornitore = f.id WHERE b.approvato = 1 AND b.id_autista = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_autista);
            ResultSet rs = stmt.executeQuery();
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

    
}
