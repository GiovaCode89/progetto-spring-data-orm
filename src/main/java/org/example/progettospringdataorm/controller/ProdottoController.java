package org.example.progettospringdataorm.controller;

import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.ProdottoDao;
import org.example.progettospringdataorm.db.entity.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private GeneralDao<ProdottoDao> generalDao;
    @Autowired
    private ProdottoDao prodottoDao;

    @GetMapping("/selectByNomeAndPrezzo")
    public String primoMetodo(){
        String nome="xbox";
        int prezzo = 500;
        List<Prodotto> prodotti=prodottoDao.selectByNomeAndPrezzo(nome,prezzo);
        if (prodotti.size()>0) {
            System.out.println("Lista prodotti :");
            prodotti.forEach((p) -> System.out.println("- nome : " + p.getNome()));
        }else{
            System.out.println("La query non ha prodotto un ResultSet!");
        }
        return "welcome";
    }
}
