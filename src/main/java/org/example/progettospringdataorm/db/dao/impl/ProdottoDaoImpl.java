package org.example.progettospringdataorm.db.dao.impl;

import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.ProdottoDao;
import org.example.progettospringdataorm.db.entity.Prodotto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class ProdottoDaoImpl implements GeneralDao<Prodotto>, ProdottoDao {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void add(Prodotto object) {
        manager.persist(object);
    }


    @Transactional
    @Override
    public void update(Prodotto object) {
        manager.merge(object);
    }


    @Transactional
    @Override
    public void deleteById(int id) {
        Prodotto p= selectById(id);
        manager.remove(p);
    }

    @Override
    public Prodotto selectById(int id) {
        return manager.find(Prodotto.class,id);
    }

    @Override
    public List<Prodotto> retrieveAll() {
        List <Prodotto> prodotti = manager.createQuery("select x from Prodotto x", Prodotto.class).getResultList();
        if (prodotti!=null)
            return prodotti;

        return null;
    }

    @Override
    public List<Prodotto> selectByNomeAndPrezzo(String nome, int prezzo) {
        String sql="select x from Prodotto x where x.nome =:n and x.prezzoUnitario =:p";

        List <Prodotto> prodotti = manager.createQuery(sql, Prodotto.class).setParameter("n",nome)
                .setParameter("p",prezzo).
                getResultList();

       return prodotti;
    }
}
