package com.cao.terminal_marittimo.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.BuonoDao;
import com.cao.terminal_marittimo.Dao.GuidaDao;
import com.cao.terminal_marittimo.Dao.RitiroDao;
import com.cao.terminal_marittimo.Dao.UtenteDao;
import com.cao.terminal_marittimo.Models.Buono_consegna;
@RestController
@RequestMapping("/autista")   
public class AutistaController {
    private final BuonoDao daoBuono = new BuonoDao();
    private final UtenteDao daoUtente = new UtenteDao();
    private final RitiroDao daoRitiro = new RitiroDao();
    private final GuidaDao daoGuida = new GuidaDao();
    @GetMapping("/getBuoni")
    public List<Buono_consegna> getBuoni(@RequestParam(name = "token", required = true) String token){
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("autista") == false) {
            return List.of();
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_autista = daoUtente.getAutistaId(id_utente);
        return daoBuono.getBuoniAutista(id_autista);
    }
    @GetMapping("/richiediRitiro")
    public ResponseEntity<Map<String, String>> richiediRitiro(
        @RequestParam(name = "token", required = true) String token,
        @RequestParam(name = "id_buono", required = true) int id_buono,
        @RequestParam(name = "peso", required = true) double peso,
        @RequestParam(name = "targa", required = true) String targa
    ){
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("autista") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }
        int id_utente = UsersKeySingleton.getUserIdFromToken(token);
        int id_autista = daoUtente.getAutistaId(id_utente);

        int id_guida = daoGuida.addGuida(targa, id_autista);
        if(id_guida == -1){
            return ResponseEntity.status(500).body(Map.of("error", "Guida non trovata"));
        }
        
        if (daoRitiro.richiediRitiro(id_buono, id_guida)) {
            return ResponseEntity.ok(Map.of("success", "Ritiro richiesto con successo"));
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Errore durante la richiesta di ritiro"));
        }
    }
}
