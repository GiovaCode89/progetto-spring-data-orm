package org.example.progettospringdataorm.controller;


import org.example.progettospringdataorm.db.dao.inteface.repository.CategoriaDaoRepository;
import org.example.progettospringdataorm.db.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaDaoRepository categoriaDaoRepository;
    @GetMapping("/addWithNome&Descr")
    public String primoMetodo(@RequestParam("name") String nome, @RequestParam("desc") String desc){
        Categoria c = new Categoria(nome,desc);
        //save() = metodo dell'interfaccia CrudRepository
        categoriaDaoRepository.save(c);
        return "welcome";
    }
}
