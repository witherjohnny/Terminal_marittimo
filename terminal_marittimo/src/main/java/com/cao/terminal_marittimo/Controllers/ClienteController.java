package com.cao.terminal_marittimo.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.ClienteDao;
import com.cao.terminal_marittimo.Dao.PolizzaDao;
import com.cao.terminal_marittimo.Dao.UtenteDao;
import com.cao.terminal_marittimo.Models.Polizza;

@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/cliente")  
public class ClienteController {
    ClienteDao daoCLiente = new ClienteDao();
    UtenteDao daoUtente = new UtenteDao();  
    PolizzaDao daoPolizza = new PolizzaDao();

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
        daoCLiente.richiediBuono(polizza, peso, id_cliente);
        return ResponseEntity.ok(Map.of("message", "Richiesta inviata con successo"));
    }
}
