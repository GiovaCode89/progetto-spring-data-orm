package org.example.progettospringdataorm.db.dao.inteface.repository;

import org.example.progettospringdataorm.db.entity.Categoria;
import org.springframework.data.repository.CrudRepository;


//Il secondo tipo generico di CrudRepository (Integer) deve essere del tipo dell'id dell'Entity 'Categoria'
public interface CategoriaDaoRepository extends CrudRepository <Categoria,Integer> {
}
