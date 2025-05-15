package com.cao.terminal_marittimo.Controllers;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.BuonoDao;
import com.cao.terminal_marittimo.Dao.FornitoreDao;
import com.cao.terminal_marittimo.Dao.MerceDao;
import com.cao.terminal_marittimo.Dao.NaveDao;
import com.cao.terminal_marittimo.Dao.PolizzaDao;
import com.cao.terminal_marittimo.Dao.PortoDao;
import com.cao.terminal_marittimo.Dao.ViaggioDao;
import com.cao.terminal_marittimo.Models.Buono_consegna;
import com.cao.terminal_marittimo.Models.Fornitore;
import com.cao.terminal_marittimo.Models.Merce;
import com.cao.terminal_marittimo.Models.Nave;
import com.cao.terminal_marittimo.Models.Porto;
import com.cao.terminal_marittimo.Models.Viaggio;



@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/admin")      
public class AdminController {
    
    //creo un oggetto che mi permette di comunicare con il DB 
    private final NaveDao daoNave = new NaveDao();
    private final PortoDao daoPorto = new PortoDao();
    private final FornitoreDao daoFornitore = new FornitoreDao();
    private final ViaggioDao daoViaggio = new ViaggioDao();
    private final MerceDao daoMerce = new MerceDao();
    private final PolizzaDao daoPolizza = new PolizzaDao();
    private final BuonoDao daoBuono = new BuonoDao();
     //http://localhost:8080/admin/getAllNavi
    @GetMapping("/getAllNavi")
    public List<Nave> getAllNavi(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return List.of();
        }
        List<Nave> navi = daoNave.getTutteLeNavi();
        return navi;
    }
    @GetMapping("/getAllPorti")
    public List<Porto> getAllPorti(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return List.of();
        }
        List<Porto> porto = daoPorto.getAllPorti();
        return porto;
    }
    @GetMapping("/getAllFornitori")
    public List<Fornitore> getAllFornitori(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return List.of();
        }
        List<Fornitore> fornitori = daoFornitore.getAllFornitori();
        return fornitori;
    }
    @GetMapping("/getAllViaggi")
    public List<Viaggio> getAllViaggi(@RequestParam(name = "token", required = true) String token) {
        if (!UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin")) {
            return List.of();
        }
        return daoViaggio.getAllViaggi();
    }
    @GetMapping("/getAllMerce")
    public List<Merce> getAllMerce(@RequestParam(name = "token", required = true) String token) {
        if (!UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin")) {
            return List.of();
        }
        return daoMerce.getAllMerce();
    }
    @GetMapping("/registraViaggio")
    public ResponseEntity<Map<String, String>> registraViaggio(@RequestParam(name = "token", required = true) String token, 
                                                  @RequestParam(name = "nave", required = true) int nave, 
                                                  @RequestParam(name = "data_partenza", required = true) String data, 
                                                  @RequestParam(name = "porto", required = true) int porto) {
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }

        int result = daoViaggio.registra(nave, data, porto);
        if (result != -1) {
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo", "insertId", String.valueOf(result)));
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Errore durante la registrazione del viaggio"));
        }
    }
    @GetMapping("/registraArrivo")
    public ResponseEntity<Map<String, String>> registraArrivo(@RequestParam(name = "token", required = true) String token, 
                                                  @RequestParam(name = "viaggio", required = true) int viaggio, 
                                                  @RequestParam(name = "data_arrivo", required = true) String data, 
                                                  @RequestParam(name = "porto", required = true) int porto) {
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }

        boolean result = daoViaggio.updateArrivo(viaggio, data, porto);
        if (result) {
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo"));
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Errore durante la registrazione del viaggio"));
        }
    }
    @GetMapping("/registraPolizza")
    public ResponseEntity<Map<String, String>> registraPolizza(@RequestParam(name = "token", required = true) String token, 
                                                  @RequestParam(name = "viaggio", required = true) int viaggio, 
                                                  @RequestParam(name = "fornitore", required = true) int fornitore, 
                                                  @RequestParam(name = "peso", required = true) String peso, 
                                                  @RequestParam(name = "merce", required = true) int merce,
                                                  @RequestParam(name = "durata_franchigia", required = true) int durata_franchigia,
                                                  @RequestParam(name = "costo_franchigia", required = true) int costo_franchigia){
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }

        boolean success = daoPolizza.registra(viaggio, fornitore, peso, merce, durata_franchigia, costo_franchigia);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo"));
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Errore durante la registrazione della polizza"));
        }
    }
    @GetMapping("/getAllNotApprovedBuoni")
    public List<Buono_consegna> getAllNotApprovedBuoni(@RequestParam(name = "token", required = true) String token){
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false) {
            return List.of();
        }
        return daoBuono.getAllNotApprovedBuoni();
    }
    @GetMapping("/approvaBuono")
    public ResponseEntity<Map<String, String>> approvaBuono(@RequestParam(name = "token", required = true) String token,
                                            @RequestParam(name = "approva", required = true) boolean approva,
                                            @RequestParam(name = "id", required = true) int id){
        if (UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false) {
            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized"));
        }

        if (approva) {
            boolean success = daoBuono.approvaBuono(id);
            if(success){
                return ResponseEntity.ok(Map.of("message","buono approvato"));
            }
            
        } else {
            boolean success = daoBuono.deleteBuono(id);
            if(success){
                return ResponseEntity.ok(Map.of("message","buono cancellato"));
            }
        }
        return ResponseEntity.status(500).body(Map.of("error", "Errore durante l'operazione sul buono"));
    }
}