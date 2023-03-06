package org.example.progettospringdataorm.db.dao.impl;

import org.example.progettospringdataorm.db.dao.inteface.simple.CategoriaDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.entity.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;

public class CategoriaDaoImpl implements GeneralDao<Categoria>, CategoriaDao {


    @PersistenceContext()
    private EntityManager manager;

    @Transactional
    @Override
    public void add(Categoria object) {
        manager.persist(object);
    }

    @Transactional
    @Override
    public void update(Categoria object) {
        manager.merge(object);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Categoria c = selectById(id);
        manager.remove(c);
    }

    @Override
    public Categoria selectById(int id) {
        return manager.find(Categoria.class, id);
    }

    @Override
    public List<Categoria> retrieveAll() {
        List<Categoria> categorie = manager.createQuery("select x from Categoria x", Categoria.class).getResultList();
        return categorie;
    }

    public List<Categoria> selectByNome(String nome) {
        List<Categoria> categorie = manager.createQuery("select x from Categoria x where x.nome =:n", Categoria.class).
                setParameter("n", nome).
                getResultList();

        return categorie;
    }

    @Override
    public String nomeById(int id) {
        return null;
    }
}
