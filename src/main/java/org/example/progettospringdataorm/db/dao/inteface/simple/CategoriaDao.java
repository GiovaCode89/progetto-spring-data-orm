package org.example.progettospringdataorm.db.dao.inteface.simple;

import org.example.progettospringdataorm.db.entity.Categoria;

import java.util.List;

public interface CategoriaDao {

    public String nomeById(int id);
    public List<Categoria> selectByNome(String nome);
}
