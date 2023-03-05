package org.example.progettospringdataorm.controller;


import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private GeneralDao<Cliente> generalDao;


    @GetMapping("/selectById")
    @ResponseBody
    public String primoMetodo(@RequestParam("id") int id){
        Cliente cliente=generalDao.selectById(id);
        return cliente.getNome()+" "+cliente.getCognome();
    }

    @GetMapping("/insertRowWithName&Sur")
    public String secondoMetodo(@RequestParam("name") String nome, @RequestParam("surname") String cognome){
        generalDao.add(new Cliente(nome,cognome));
        return "welcome";
    }

    @GetMapping("/updateForId")
    public String quartoMetodo(@RequestParam("id") int id){
        Cliente c=generalDao.selectById(id);
        c.setNome("Carlo");
        c.setCognome("Bianchi");
        //aggiorna un riga con uno specifico id
        generalDao.update(c);
        return "welcome";
    }

    @GetMapping("/deleteById")
    public String quintoMetodo(@RequestParam("id") int id){
        generalDao.deleteById(id);
        return "welcome";
    }

}
