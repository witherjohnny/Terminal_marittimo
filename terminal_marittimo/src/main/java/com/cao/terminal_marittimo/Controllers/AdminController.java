package com.cao.terminal_marittimo.Controllers;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.FornitoreDao;
import com.cao.terminal_marittimo.Dao.NaveDao;
import com.cao.terminal_marittimo.Dao.PortoDao;
import com.cao.terminal_marittimo.Models.Fornitore;
import com.cao.terminal_marittimo.Models.Nave;
import com.cao.terminal_marittimo.Models.Porto;



@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/admin")      
public class AdminController {
    
    //creo un oggetto che mi permette di comunicare con il DB 
    private final NaveDao daoNave = new NaveDao();
    private final PortoDao daoPorto = new PortoDao();
    private final FornitoreDao daoFornitore = new FornitoreDao();
     //http://localhost:8080/admin/getAllNavi
    @GetMapping("/getAllNavi")
    public List<Nave> getAllNavi(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return null;
        }
        List<Nave> navi = daoNave.getTutteLeNavi();
        return navi;
    }
    @GetMapping("/getAllPorti")
    public List<Porto> getAllPorti(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return null;
        }
        List<Porto> porto = daoPorto.getAllPorti();
        return porto;
    }
    @GetMapping("/getAllFornitori")
    public List<Fornitore> getAllFornitori(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("admin") == false){
            return null;
        }
        List<Fornitore> fornitori = daoFornitore.getAllFornitori();
        return fornitori;
    }
    @GetMapping("/registraViaggio")
    public ResponseEntity<String> registraViaggio(@RequestParam(name = "nave") String nave, @RequestParam(name = "data") String data) {
        // Implementa la logica per registrare un viaggio
        return ResponseEntity.ok("Viaggio registrato con successo");
    }

}