package com.cao.terminal_marittimo.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.Dao.UtenteDao;
import com.cao.terminal_marittimo.Entities.Utente;

import java.util.List;

@RestController

// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/utenti")      
public class UtenteController {

    //creo un oggetto che mi permette di comunicare con il DB 
    private final UtenteDao dao = new UtenteDao();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam String nome, @RequestParam String email) {
        dao.inserisci(nome, email);
        return "OK";
    }

    @GetMapping("/tutti")
    //è possibile ritornare direttamente una lista di oggetti
    //automaticamente verrà convertita in JSON 
    //( fare attenzione che l'oggetto Utente implementi i metodi GET ) 
    public List<Utente> trovaTutti() {
        return dao.trovaTutti();
    }

    @GetMapping("/aggiorna")
    public String aggiorna(@RequestParam int id, @RequestParam String email) {
        dao.aggiornaEmail(id, email);
        return "OK";
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.elimina(id);
        return "OK";
    }
}