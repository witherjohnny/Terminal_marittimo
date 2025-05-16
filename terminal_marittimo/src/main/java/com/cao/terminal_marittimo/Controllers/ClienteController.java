package com.cao.terminal_marittimo.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.AutistaDao;
import com.cao.terminal_marittimo.Dao.BuonoDao;
import com.cao.terminal_marittimo.Dao.ClienteDao;
import com.cao.terminal_marittimo.Dao.PolizzaDao;
import com.cao.terminal_marittimo.Dao.UtenteDao;
import com.cao.terminal_marittimo.Models.Autista;
import com.cao.terminal_marittimo.Models.Buono_consegna;
import com.cao.terminal_marittimo.Models.Polizza;

@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/cliente")  
public class ClienteController {
    ClienteDao daoCLiente = new ClienteDao();
    UtenteDao daoUtente = new UtenteDao();  
    PolizzaDao daoPolizza = new PolizzaDao();
    BuonoDao daoBuono = new BuonoDao();
    AutistaDao daoAutista = new AutistaDao();

    @GetMapping("/getAllPolizze")
    public List<Polizza> getAllPolizze(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return List.of();
        }
        return daoPolizza.getAllPolizze();
    }
    @GetMapping("/richiediBuono")
    public ResponseEntity<Map<String, String>> richiediBuono(@RequestParam(name = "token" ,required=true) String token,
                                                            @RequestParam(name = "polizza" ,required=true) int polizza,
                                                            @RequestParam(name = "peso" ,required=true) float peso) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return ResponseEntity.status(500).body(Map.of("error", "Unauthorized"));
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_cliente = daoUtente.getClienteId(id_utente);
        if(id_cliente == -1){
            return ResponseEntity.status(500).body(Map.of("error", "Cliente non trovato"));
        }
        daoBuono.richiediBuono(polizza, peso, id_cliente);
        return ResponseEntity.ok(Map.of("message", "Richiesta inviata con successo"));
    }
    @GetMapping("/getBuoni")
    public List<Buono_consegna> getBuoni(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return List.of();
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_cliente = daoUtente.getClienteId(id_utente);
        if(id_cliente == -1){
            return List.of();
        }
        return daoBuono.getBuoni(id_cliente);
    }
    @GetMapping("/getAutisti")
    public List<Autista> getAutisti(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return List.of();
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_cliente = daoUtente.getClienteId(id_utente);
        if(id_cliente == -1){
            return List.of();
        }
        return daoAutista.getAutisti(id_cliente);
    }
    @GetMapping("/registraAutista")
    public ResponseEntity<Map<String, String>> registraAutista(
            @RequestParam(name = "token", required = true) String token,
            @RequestParam(name = "nome", required = true) String nome,
            @RequestParam(name = "cognome", required = true) String cognome,
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password) {
                
        if (!UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente")) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_cliente = daoUtente.getClienteId(id_utente);
        if (id_cliente == -1) {
            return ResponseEntity.status(404).body(Map.of("error", "Cliente non trovato"));
        }
        boolean success = daoAutista.registraAutista(nome, cognome, username, password, id_cliente);
        if (!success) {
            return ResponseEntity.status(400).body(Map.of("error", "Registrazione autista fallita"));
        }
        return ResponseEntity.ok(Map.of("message", "Autista registrato con successo"));
    }

    @GetMapping("/assegnaBuono")
    public ResponseEntity<Map<String, String>> assegnaBuono(@RequestParam(name = "token", required = true) String token,
                                            @RequestParam(name = "id", required = true) int id_buono,
                                            @RequestParam(name = "id_autista", required = true) int id_autista) {
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }

        boolean success = daoBuono.assegnaBuono(id_buono, id_autista);
        if(success){
            return ResponseEntity.ok(Map.of("message","buono assegnato"));
        }

        return ResponseEntity.status(500).body(Map.of("error", "Errore durante l'operazione sul buono"));
    }
}
