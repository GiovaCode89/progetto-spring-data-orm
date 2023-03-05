package org.example.progettospringdataorm.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    private int id_categoria_padre;

    public Categoria() {
    }

    public Categoria(int id, String nome, String descrizione, int id_categoria_padre) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.id_categoria_padre = id_categoria_padre;
    }

    public Categoria(String nome, String descrizione, int id_categoria_padre) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.id_categoria_padre = id_categoria_padre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId_categoria_padre() {
        return id_categoria_padre;
    }

    public void setId_categoria_padre(int id_categoria_padre) {
        this.id_categoria_padre = id_categoria_padre;
    }
}
