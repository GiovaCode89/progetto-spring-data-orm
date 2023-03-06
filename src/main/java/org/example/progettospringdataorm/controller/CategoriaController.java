package org.example.progettospringdataorm.controller;


import org.example.progettospringdataorm.db.dao.inteface.repository.CategoriaDaoRepository;
import org.example.progettospringdataorm.db.dao.inteface.simple.CategoriaDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaDaoRepository categoriaDaoRepository;
    @Autowired
    private CategoriaDao categoriaDao;
    @Autowired
    private GeneralDao<Categoria> generalDao;


    @GetMapping("/addWithNome&Descr")
    public String primoMetodo(@RequestParam("name") String nome, @RequestParam("desc") String desc) {
        Categoria c = new Categoria(nome, desc);
        //save() = metodo dell'interfaccia CrudRepository

        categoriaDaoRepository.save(c);
        return "welcome";
    }

    @GetMapping("/selectAll")
    public String secondoMetodo() {
        Iterable<Categoria> categorie = categoriaDaoRepository.findAll();
        categorie.forEach((c) ->
                System.out.println("id : " + c.getId() + " nome : " + c.getNome()
                )
        );
        return "welcome";
    }

    @ResponseBody
    @GetMapping("/selectById")
    public String terzoMetodo(@RequestParam("id") int id) {
        Optional<Categoria> c = categoriaDaoRepository.findById(id);
        Categoria categoria = c.get();
        return "Il nome di questo categoria è " + categoria.getNome();
    }

    @ResponseBody
    @GetMapping("/selectByNomeAndDesc")
    public String terzoMetodo(@RequestParam("nome") String nome, @RequestParam("desc") String desc) {

        Categoria categoria = categoriaDaoRepository.findByNomeAndDescrizione(nome, desc);
        return "nome : " + categoria.getNome() + " descrizione : " + desc;
    }

    @ResponseBody
    @GetMapping("/countByName")
    public String quartoMetodo(@RequestParam("nome") String nome) {

        Integer numNomi = categoriaDaoRepository.countByNome(nome);
        return "Nella tabella categoria ci sono " + numNomi + " righe che ha come attributo 'nome' "+nome+"!";
    }


    @GetMapping("/findByIdGreaterThan")
    public String quintoMetodo(@RequestParam("id") int id) {
        List<Categoria> categoriaList = categoriaDaoRepository.findByIdGreaterThan(id);
        if (categoriaList != null) {
            System.out.println("ResultSet della query: ");
            categoriaList.forEach((c) ->
                    System.out.println("- nome : " + c.getNome() + " -descrizione : " + c.getDescrizione()));
        }
        return "welcome";
    }

    @GetMapping("/findByNomeLike")
    public String sestoMetodo() {
        List<Categoria> categoriaList = categoriaDaoRepository.findByNomeLike("%le%");
        if (categoriaList != null) {
            System.out.println("ResultSet della query: ");
            categoriaList.forEach((c) ->
                    System.out.println("- nome : " + c.getNome() + " -descrizione : " + c.getDescrizione()));
        }
        return "welcome";
    }

    //Utilizza il metodo dell'interfaccia GeneralDao
    @GetMapping("/secondSelectAll")
    public String settimoMetodo() {
        List<Categoria> categoriaList = generalDao.retrieveAll();
        if (categoriaList.size()>0) {
            System.out.println("ResultSet della query: ");
            categoriaList.forEach((c) ->
                    System.out.println("- nome : " + c.getNome() + " -descrizione : " + c.getDescrizione()));
        }else{
            System.out.println("Spiacente, la query non ha creato un resultSet");
        }
        return "welcome";
    }

    //Utilizza il metodo dell'interfaccia CategoriaDao
    @GetMapping("/secondSelectByNome")
    public String settimoMetodo(@RequestParam ("nome") String nome) {
        List<Categoria> categoriaList = categoriaDao.selectByNome(nome);
        if (categoriaList.size()>0) {
            System.out.println("ResultSet della query: ");
            categoriaList.forEach((c) ->
                    System.out.println("- nome : " + c.getNome()));
        }else{
            System.out.println("Spiacente, la query non ha creato un resultSet");
        }
        return "welcome";
    }

}



