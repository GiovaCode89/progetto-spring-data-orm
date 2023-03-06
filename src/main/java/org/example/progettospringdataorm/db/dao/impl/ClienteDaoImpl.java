package org.example.progettospringdataorm.db.dao.impl;

import org.example.progettospringdataorm.db.dao.inteface.simple.ClienteDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.entity.Categoria;
import org.example.progettospringdataorm.db.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


//Questa classe per i comandi di insert, delete e update è impostata in modo tale che fà uso delle transazioni
public class ClienteDaoImpl implements GeneralDao<Cliente>, ClienteDao {
    @PersistenceContext()
    private EntityManager manager;

    //@Transactional = permette al metodo di contenere transazioni
    @Transactional
    @Override
    public void add(Cliente c) {
        manager.persist(c);
    }

    @Transactional
    @Override
    public void update(Cliente c) {
        manager.merge(c);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Cliente c= selectById(id);
        manager.remove(c);
    }

    @Override
    public Cliente selectById(int id) {
        return manager.find(Cliente.class,id);
    }

    @Override
    public List<Cliente> retrieveAll() {

        List<Cliente> clienti = manager.createQuery("select x from Cliente x", Cliente.class).getResultList();
        return clienti;
    }

    @Override
    public String getTelefonoByNomeAndCognome(String nome, String cognome) {
        return null;
    }
}
