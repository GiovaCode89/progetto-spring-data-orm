package org.example.progettospringdataorm.db.dao.impl;

import org.example.progettospringdataorm.db.dao.inteface.GeneralDao;
import org.example.progettospringdataorm.db.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;

public class ClienteDaoImpl implements GeneralDao<Cliente> {
    @PersistenceContext()
    private EntityManager manager;

    //@Transactional = permette al metodo di contenere transazioni
    @Transactional
    @Override
    public void add(Cliente c) {
        manager.persist(c);
    }

    @Override
    public void update(Cliente object) {

    }

    @Override
    public void deleteForId(int id) {

    }

    @Override
    public Cliente selectById(int id) {
        return manager.find(Cliente.class,id);
    }

    @Override
    public List<Cliente> retrieveAll() {
        return null;
    }
}
