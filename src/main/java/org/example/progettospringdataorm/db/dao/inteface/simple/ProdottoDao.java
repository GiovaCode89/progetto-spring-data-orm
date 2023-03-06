package org.example.progettospringdataorm.db.dao.inteface.simple;

import org.example.progettospringdataorm.db.entity.Prodotto;

import java.util.List;

public interface ProdottoDao {
        public List<Prodotto> selectByNomeAndPrezzo(String nome, int prezzo);
}
