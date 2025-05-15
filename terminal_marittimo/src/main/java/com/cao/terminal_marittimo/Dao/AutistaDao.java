package com.cao.terminal_marittimo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cao.terminal_marittimo.DbConnection;
import com.cao.terminal_marittimo.Models.Autista;
import com.cao.terminal_marittimo.Models.Cliente;

public class AutistaDao {
    public List<Autista> getAutisti(int id_cliente){
        
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {
            List<Autista> autisti = new ArrayList<>();
            String sql = "SELECT c.id_utente AS cliente_id_utente, a.id, a.nome AS autista_nome, a.cognome AS autista_cognome, c.id AS cliente_id, c.nome AS cliente_nome, c.cognome AS cliente_cognome"
                       + " FROM autista a JOIN cliente c ON a.id_cliente = c.id WHERE a.id_cliente = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_cliente);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Autista autista = new Autista(
                    rs.getInt("id"),
                    rs.getString("autista_nome"),
                    rs.getString("autista_cognome"),
                    new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("cliente_nome"),
                        rs.getString("cliente_cognome"),
                        rs.getInt("cliente_id_utente")
                    )
                );
                autisti.add(autista);
            }

            return autisti;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    
    }
    public boolean registraAutista(String nome, String cognome, String username, String password, int id_cliente){
        try (Connection conn = DriverManager.getConnection(DbConnection.URL, DbConnection.USER, DbConnection.PASSWORD)) {

            UtenteDao utenteDao = new UtenteDao();
            int idUtente = utenteDao.registra(username, password,"autista");
            if (idUtente <= 0) {
                return false;
            }
            String sql = "INSERT INTO autista (nome, cognome, id_cliente,id_utente) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setInt(3, id_cliente);
            stmt.setInt(4,idUtente);
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
