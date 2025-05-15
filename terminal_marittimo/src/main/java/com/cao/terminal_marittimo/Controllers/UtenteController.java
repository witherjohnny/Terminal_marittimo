package com.cao.terminal_marittimo.Controllers;
import java.util.Date;
import java.util.Map;
import java.security.Key;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.UtenteDao;
import com.cao.terminal_marittimo.Models.Utente;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/utenti")      
public class UtenteController {
    
    //creo un oggetto che mi permette di comunicare con il DB 
    private final UtenteDao dao = new UtenteDao();
     //http://localhost:8080/utenti/login?username=admin&password=admin
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam(name = "username" ,required = true) String nome, @RequestParam(name = "password" ,required = true) String password) {
        Utente utente = dao.login(nome, password);
        if(utente == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenziali non valide"));
        }
        String token = UsersKeySingleton.makeKey(nome);
        Map<String, String> response = Map.of(
            "utente", utente.getNome(),
            "token", token
            );
        if(utente.getRuolo().equals("cliente")){
            UsersKeySingleton.getInstance().addClienteKey(token);
        }else if(utente.getRuolo().equals("fornitore")){
            UsersKeySingleton.getInstance().addFornitoreKey(token);
        }else if(utente.getRuolo().equals("autista")){
            UsersKeySingleton.getInstance().addAutistaKey(token);
        }else if(utente.getRuolo().equals("admin")){
            UsersKeySingleton.getInstance().addAdminKey(token);
        }
        
        return ResponseEntity.ok(response);
    }
    @GetMapping("/test")
    public String test(@RequestParam(name = "token") String token) {
        return "hello "+token;  
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestParam(name = "username" ,required = true) String nome, @RequestParam(name = "password" ,required = true) String password) {
        if(dao.registra(nome, password)){
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo"));
        }
        return ResponseEntity.status(400).body(Map.of("error", "Registrazione fallita"));
        
    }

    /*

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.elimina(id);
        return "OK";
    } */
}