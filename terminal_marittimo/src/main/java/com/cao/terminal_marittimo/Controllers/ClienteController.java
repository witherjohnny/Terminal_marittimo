package com.cao.terminal_marittimo.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cao.terminal_marittimo.UsersKeySingleton;
import com.cao.terminal_marittimo.Dao.PolizzaDao;
import com.cao.terminal_marittimo.Models.Polizza;

@RestController
// imposto che tutti i mapping di questa classe saranno preceduti da "/utenti"
@RequestMapping("/cliente")  
public class ClienteController {

    PolizzaDao daoPolizza = new PolizzaDao();
    @GetMapping("/getAllPolizze")
    public List<Polizza> getAllPolizze(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return List.of();
        }
        return daoPolizza.getAllPolizze();
    }
    @GetMapping("/richiediBuono")
    public List<Polizza> richiediBuono(@RequestParam(name = "token" ,required=true) String token) {
        if(UsersKeySingleton.getInstance().checkAuthorization(token).equals("cliente") == false){
            return List.of();
        }
        return List.of();
    }
}
