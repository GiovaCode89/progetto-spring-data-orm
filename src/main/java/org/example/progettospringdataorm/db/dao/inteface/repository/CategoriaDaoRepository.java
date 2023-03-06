package org.example.progettospringdataorm.db.dao.inteface.repository;

import org.example.progettospringdataorm.db.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//Il secondo tipo generico di CrudRepository (Integer) deve essere del tipo dell'id dell'Entity 'Categoria'
public interface CategoriaDaoRepository extends CrudRepository <Categoria,Integer> {


        //Miei metodi. I loro nomi rispettano la sintassi di Spring data repository
        public Categoria findByNomeAndDescrizione(String nome, String desc);
        public Integer countByNome(String nome);
        public List<Categoria> findByIdGreaterThan(int id);
        public List<Categoria> findByNomeLike(String nome);
}
